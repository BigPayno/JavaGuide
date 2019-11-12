package utils.io.encode;

import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import utils.io.file.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author payno
 * @date 2019/8/27 11:15
 * @description
 */
@Slf4j
public class ByteEncoder {
    private static final String CACHE_FIX = ".encoding";

    /**
     * encodeFile
     *
     * @param from          原始文件
     * @param to            转换编码后文件
     * @param originCharset 原始编码
     * @param newCharset    新编码
     * @author: payno
     * @time: 2019/8/27 11:39
     * @description:
     * @return: void
     */
    public static void encodeFile(File from, File to, Charset originCharset, Charset newCharset) {
        ByteSource byteSource = Files.asByteSource(from).asCharSource(originCharset).asByteSource(newCharset);
        FileUtils.deleteIfExists(to);
        ByteSink byteSink = Files.asByteSink(to, FileWriteMode.APPEND);
        try (BufferedInputStream bufferedInputStream = (BufferedInputStream) byteSource.openBufferedStream()) {
            byteSink.writeFrom(bufferedInputStream);
        } catch (IOException e) {
            log.error("文件{}转码失败！！！", from);
        }
    }

    /**
     * encodeFile
     *
     * @param target 准备编码的文件
     * @param from   原始编码
     * @param to     新编码
     * @author: payno
     * @time: 2019/8/27 11:39
     * @description:
     * @return: void
     */
    public static void encodeFile(File target, Charset from, Charset to) {
        File cache = new File(target.getAbsolutePath() + CACHE_FIX);
        encodeFile(target, cache, from, to);
        FileUtils.deleteIfExists(target);
        FileUtils.renameTo(cache, target);
        log.info("文件[{}]转换编码完成！！！{}->{}", target, from.name(), to.name());
    }
}
