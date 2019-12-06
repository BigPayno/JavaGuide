package netty.demos.base.nio;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;

/**
 * @author payno
 * @date 2019/11/1 16:47
 * @description
 */
public class ReactorDemo {
    public static void main(String[] args) throws IOException {

        Reactor.of(8000,(selector, socketChannel) -> {
            try{
                socketChannel.register(selector, SelectionKey.OP_WRITE|SelectionKey.OP_READ);
            }catch (ClosedChannelException var0){
                var0.printStackTrace();
            }
        },selectionKey -> {
            if(selectionKey.isReadable()){

            }else if(selectionKey.isWritable()){

            }
        }).run();
    }
}
