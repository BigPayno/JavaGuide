package netty.base.io;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * @author payno
 * @date 2019/10/31 11:45
 * @description
 */
public class IoClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((LocalDateTime.now() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException var0) {
                var0.printStackTrace();
            }
        }).start();
    }
}
