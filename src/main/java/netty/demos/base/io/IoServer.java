package netty.demos.base.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author payno
 * @date 2019/10/31 11:32
 * @description
 */
public class IoServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(8000);
        new Thread(()->{
            while(true){
                try{
                    Socket socket=serverSocket.accept();
                    new Thread(()->{
                        byte[] data=new byte[1024];
                        try(InputStream inputStream=socket.getInputStream()){
                            while(true){
                                int len;
                                while ((len=inputStream.read(data))!=-1){
                                    System.out.println(new String(data,0,len));
                                }
                            }
                        }catch (IOException var0){
                            var0.printStackTrace();
                        }
                    }).start();
                }catch (IOException var1){
                    var1.printStackTrace();
                }
            }
        }).start();
    }
}
