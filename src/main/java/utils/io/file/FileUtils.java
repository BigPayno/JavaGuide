package utils.io.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author payno
 * @date 2019/8/30 11:59
 * @description
 */
@Slf4j
public class FileUtils {
    public static void deleteIfExists(File file) {
        if (file.exists()) {
            if (!file.delete()) {
                log.error("文件{}删除出现异常！！！", file);
            }
        }
    }

    public static void renameTo(File from, File to) {
        if (!from.renameTo(to)) {
            log.error("文件{}重命名{}出现异常！！！", from, to);
        }
    }
    
    public static void create(File target) {
    	try {
    		if (!target.createNewFile()) {
                log.error("文件{}创建{}出现异常！！！",target);
            }
    	}catch (Exception e) {
    		log.error("文件{}创建{}出现异常！！！",target);
		}
       
    }
}
