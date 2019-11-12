package spring.resources;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.util.UUID;

/**
 * @author payno
 * @date 2019/9/20 10:53
 * @description
 */
public class ResourceLoaderGuide {
    public static void main(String[] args) {
        ResourceLoader loader=new DefaultResourceLoader();
    }
}
