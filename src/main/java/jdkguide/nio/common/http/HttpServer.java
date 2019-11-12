package jdkguide.nio.common.http;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author payno
 * @date 2019/11/1 08:55
 * @description
 */
public class HttpServer {
    private static final String HOST="localhost";
    private static final int PORT=8000;
    public static void main(String[] args) throws Exception{
        //初始化ServerSocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(HOST,PORT));
        serverSocketChannel.configureBlocking(false);

        //注册并绑定事件
        Selector selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while(true){
            //轮询监控选择器
            int readyNum=selector.select();
            if(readyNum==0){
                continue;
            }
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            Iterator<SelectionKey> keyIterator=selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey=keyIterator.next();
                keyIterator.remove();
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    HttpHandler.request(selectionKey);
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                }else if (selectionKey.isWritable()){
                    HttpHandler.response(selectionKey);
                }
            }
        }
    }
}
