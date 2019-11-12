package jdkguide.nio.common.http;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.sun.net.httpserver.Headers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author payno
 * @date 2019/11/1 09:22
 * @description
 */
public class HttpHandler {
    private static final int DEFAULT_BUFFER_CAPACITY=1<<10;
    private static final String CRLF = "\r\n";
    private static final Splitter SPLITTER=Splitter.on(" ");

    public static void request(SelectionKey selectionKey) throws IOException {
        if(selectionKey.channel() instanceof SocketChannel){
            SocketChannel socketChannel=(SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(DEFAULT_BUFFER_CAPACITY);
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            String headerStr=new String(byteBuffer.array());
            byteBuffer.clear();
            Headers headers=parseHeader(byteBuffer.toString());
            selectionKey.attach(headers);
        }
    }

    private static Headers parseHeader(String headerStr) {
        if (Objects.isNull(headerStr) || headerStr.isEmpty()) {
            throw new IllegalStateException("invalid header!");
        }

        // 解析请求头第一行
        int index = headerStr.indexOf(CRLF);
        if (index == -1) {
            throw new IllegalStateException("invalid header!");
        }

        Headers headers = new Headers();
        String firstLine = headerStr.substring(0, index);
        List<String> parts= SPLITTER.splitToList(firstLine);

        /*
         * 请求头的第一行必须由三部分构成，分别为 METHOD PATH VERSION
         * 比如：
         *     GET /index.html HTTP/1.1
         */
        if (parts.size()<3) {
            throw new IllegalStateException("invalid header!");
        }

        headers.set("method",parts.get(0));
        headers.set("path",parts.get(1));
        headers.set("version",parts.get(2));

        return headers;
    }

    public static void response(SelectionKey selectionKey) throws IOException{
        if(selectionKey.channel() instanceof SocketChannel){
            SocketChannel socketChannel=(SocketChannel) selectionKey.channel();
            if(selectionKey.attachment() instanceof Optional){
                Optional<?> optional=(Optional<?>) selectionKey.attachment();
                if(optional.isPresent()){
                    if(optional.get() instanceof Headers){
                        //doSomething
                    }
                    //Ok
                    socketChannel.close();
                    return;
                }else {
                    //Not Found
                    socketChannel.close();
                    return;
                }
            }
        }
    }
}
