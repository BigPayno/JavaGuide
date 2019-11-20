package encryption.type;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author payno
 * @date 2019/11/20 15:37
 * @description
 *
 *      对称加密
 *      加密解密快，但是无法保证对方妥善保存公钥
 *      而且公钥在第一次发送可能被拦截，抓取
 */
public class Symmetry {
    public static Random random=new Random();
    public int randomInt(){
        return random.nextInt();
    }
    public String randomUUID(){
        return UUID.randomUUID().toString();
    }
    public int[] from(byte[] source){
        boolean flag=source.length%4==0;
        int length=flag?source.length/4:(source.length/4+1);
        int limit=source.length-4*length;
        int[] sink=new int[length];
        for (int i=0;i<length;i++){
            if((!flag)&&i==length-1){
                byte[] buffer=new byte[4];
                for(int j=0;j<4;j++){
                    buffer[j]=(j<limit)?source[4*length-4+j]:(byte)0;
                }
                sink[i]=Ints.fromByteArray(buffer);
            }else{
                sink[i]=Ints.fromBytes(source[i*4],source[i*4+1],source[i*4+2],source[i*4+3]);
            }
        }
        return sink;
    }
    @Test
    public void easyTry(){
        String base="hello liam";
        byte[] source=base.getBytes();
        int[] sink=from(source);
        int secret=randomInt()>>4;
        IntStream.of(sink).forEach(System.out::print);
        System.out.println();
        IntStream.of(sink).map(i->i^secret).forEach(System.out::print);
        System.out.println();
        IntStream.of(sink).map(i->i^secret^secret).forEach(System.out::print);
    }

    @Test
    public void funnyTry(){
        String context="hello payno";
        int random=new Random().nextInt()>>5;
        List<Integer> ints=Chars.asList(context.toCharArray()).stream()
                .map(character -> (Integer)(int)character.charValue())
                .collect(Collectors.toList());
        ints.forEach(System.out::print);
        System.out.println();
        ints.stream().map(num->num^random)
                .forEach(System.out::print);
        char[] chars=new char[ints.size()];
        for (int i = 0; i <ints.size() ; i++) {
            chars[i] =(char)ints.get(i).intValue();
        }
        System.out.println();
        System.out.println(new String(chars));
    }

    public void aes(){
        /**
         * apache.lang,codec
         */
    }
}
