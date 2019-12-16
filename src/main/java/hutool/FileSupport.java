package hutool;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;
import com.google.common.util.concurrent.MoreExecutors;
import jdkguide.thread.Threads;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/12/16 11:18
 * @description
 */
public class FileSupport {
    /**
     * 文件类型判断，基于字节
     */
    @Test
    public void type(){
        String path="d:/wordcloud.jp";
        File file=new File(path);
        System.out.println(FileTypeUtil.getTypeByPath(path));
        System.out.println(FileTypeUtil.getType(file));
    }
    /**
     * 文件监听，基于jdk1.7的WatchService
     */
    @Test
    public void watch(){
        WatchMonitor watchMonitor=new WatchMonitor(Paths.get("d://"),WatchMonitor.EVENTS_ALL);
        watchMonitor.setWatcher(new Watcher() {
            @Override
            public void onCreate(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                Console.log("创建：{}-> {}", path, obj);
            }

            @Override
            public void onModify(WatchEvent<?> watchEvent, Path path) {

            }

            @Override
            public void onDelete(WatchEvent<?> watchEvent, Path path) {

            }

            @Override
            public void onOverflow(WatchEvent<?> watchEvent, Path path) {

            }
        });
        watchMonitor.setMaxDepth(2);
        watchMonitor.start();
        Threads.sleep(50000);
    }

    @Test
    public void watch2(){
        Watcher watcher=new SimpleWatcher(){
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("创建：{}-> {}", currentPath, obj);
            }
        };
    }
}
