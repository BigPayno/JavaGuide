package utils;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * @author payno
 * @date 2020/4/28 09:42
 * @description
 */
@Slf4j
public final class Resources {
    static final String SYS_FILE_FORMATTER = "file:%s";
    static final String CLASS_PATH_FORMATTER = "classpath:%s";
    static final String CONFIG_DIR ="config/";
    static final String CONFIG_ANT_PATTERN = "*.*";
    static final AntPathMatcher antPathMatcher = new AntPathMatcher();
    static final PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();

    //  读取Resource并返回String  默认UTF_8
    public static String read(Resource resource) throws IOException {
        return new String(
                ByteStreams.toByteArray(
                        resource.getInputStream()
                ), Charsets.UTF_8
        );
    }

    //  加载路径得到Resource
    public static Resource load(String path){
        return resourceLoader.getResource(path);
    }

    public static Resource loadConfig(String path){
        if(!antPathMatcher.match(CONFIG_ANT_PATTERN,path)){
            throw new IllegalStateException("当前路径并非config路径");
        }else{
            Resource resource;
            String realPath = String.format(SYS_FILE_FORMATTER,CONFIG_DIR+path);
            log.debug("尝试从文件系统读取配置资源[path=({})]",realPath);
            resource = load(realPath);
            if(!resource.exists()){
                realPath = String.format(CLASS_PATH_FORMATTER,path);
                log.debug("文件系统读取配置文件失败，尝试从CLASSPATH读取资源[path=({})]",realPath);
                resource = load(realPath);
            }
            return resource;
        }
    }

    public static String readResource(String path) throws IOException{
        Resource resource;
        String realPath = String.format(SYS_FILE_FORMATTER,path);
        log.debug("尝试从文件系统读取配置资源[path=({})]",realPath);
        resource = load(realPath);
        if(!resource.exists()){
            realPath = String.format(CLASS_PATH_FORMATTER,path);
            log.debug("文件系统读取配置文件失败，尝试从CLASSPATH读取资源[path=({})]",realPath);
            resource = load(realPath);
        }
        return read(resource);
    }
}
