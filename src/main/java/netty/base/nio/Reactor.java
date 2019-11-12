package netty.base.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author payno
 * @date 2019/11/1 15:07
 * @description
 */
@Slf4j
public class Reactor implements Runnable{
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;
    final Consumer<SelectionKey> dispatcher;
    final BiConsumer<Selector,SocketChannel> handler;

    private Reactor(int port,BiConsumer<Selector,SocketChannel> handler,Consumer<SelectionKey> dispatcher) throws IOException {
        selector=Selector.open();
        serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT).attach(new Acceptor());
        this.dispatcher=dispatcher;
        this.handler=handler;
    }

    public static Reactor of(int port,BiConsumer<Selector,SocketChannel> handler,Consumer<SelectionKey> dispatcher) throws IOException{
        return new Reactor(port,handler,dispatcher);
    }

    public class Acceptor implements Runnable{
        @Override
        public void run(){
            try{
                SocketChannel socketChannel=serverSocketChannel.accept();
                if(socketChannel.isOpen()){
                    //对连接状态的Socket进行处理，监听SocketChannel的状态
                    handler.accept(selector,socketChannel);
                }
            }catch (IOException var0){
                var0.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        log.debug("the server is listening to the port [{}]!",serverSocketChannel.socket().getLocalPort());
        while(!Thread.interrupted()){
            try{
                log.debug("ready selection count: [{}]",selector.select());
                selector.selectedKeys().forEach(selectionKey -> {
                    dispatcher.accept(selectionKey);
                });
                selector.selectedKeys().clear();
            }catch (IOException var0){
                log.error("{}",var0);
            }
        }
    }
}
