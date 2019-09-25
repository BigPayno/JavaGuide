package spring.resources;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author payno
 * @date 2019/9/19 11:23
 * @description
 */
@AllArgsConstructor
public class ByteResource {
    private Resource resource;
    public ByteSource toByteSource() throws IOException {
        try(InputStream inputStream=resource.getInputStream()){
            return ByteSource.wrap(ByteStreams.toByteArray(inputStream));
        }
    }
}
