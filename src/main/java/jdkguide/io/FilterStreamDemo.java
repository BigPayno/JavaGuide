package jdkguide.io;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

/**
 * @author payno
 * @date 2019/12/18 15:33
 * @description
 */
public class FilterStreamDemo {
    public static void main(String[] args) throws IOException{
        ByteArrayInputStream bis=new ByteArrayInputStream("payno2789*".getBytes());
        FilterInputStream fis=new FilterInputStream(bis){
            @Override
            public int read() throws IOException {
                int read=super.read();
                if(read>='0'&&read<='9'){
                    return '*';
                }
                return read;
            }
        };
        int read,index=0;
        char[] chars=new char[1024];
        while ((read=fis.read())!=-1){
            chars[index]=(char) read;
            index++;
        }
        System.out.println(chars);
    }
}
