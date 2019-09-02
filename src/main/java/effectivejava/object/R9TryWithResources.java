package effectivejava.object;

import com.google.common.io.ByteSink;
import com.google.common.io.Files;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author payno
 * @date 2019/8/28 21:27
 * @description 使用try-with-resource语句关闭必须关掉的资源
 * 1.防止finally语句中的异常覆盖try中的语句
 * 2.更简洁有效
 */
public class R9TryWithResources {
    private static byte[] createBuffer(){
        return new byte[10240];
    }
    private static void tranTo(File from,File to){
        try(InputStream inputStream= Files.asByteSource(from).openStream();
            OutputStream outputStream=Files.asByteSink(to).openStream()){
            byte[] buf=createBuffer();
            long total=0;
            while(true){
                int r=inputStream.read(buf);
                if(r==-1){
                    break;
                }
                outputStream.write(buf,0,r);
                total+=r;
            }
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
