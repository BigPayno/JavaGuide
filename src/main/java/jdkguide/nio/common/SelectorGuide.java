package jdkguide.nio.common;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author payno
 * @date 2019/10/31 14:52
 * @description
 */
public class SelectorGuide {
    public static void main(String[] args) throws Exception{
        Selector selector=Selector.open();
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("www.baidu.com",80));
        socketChannel.configureBlocking(false);
        SelectionKey selectionKey=socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_CONNECT);
        selectionKey.attachment();
        selectionKey.channel();
        selectionKey.selector();
        selectionKey.interestOps();
        selectionKey.readyOps();
    }
}
