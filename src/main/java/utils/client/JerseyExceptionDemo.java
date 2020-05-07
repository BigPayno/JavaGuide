package utils.client;

import com.alibaba.fastjson.JSONObject;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * @author payno
 * @date 2019/11/18 16:08
 * @description
 *      如果获取response对象并且读取entity数据，Jersey会自动关闭连接，这个时候再操作response entity数据则会跑出异常：
 *      Entity input stream has already been closed；如果将entity读入流中（InputStream）并不读取；
 *      或者不对response操作 那么需要手动关闭连接
 */
public class JerseyExceptionDemo {
    public static void main(String[] args) {
        FormDataMultiPart form = new FormDataMultiPart();
        form.bodyPart(new FileDataBodyPart("file", new File("d://jsonpath.json")));
        Entity<FormDataMultiPart> entity = Entity.entity(form, MediaType.MULTIPART_FORM_DATA_TYPE);
        Response response = ClientBuilder.newClient()
                .register(MultiPartFeature.class)
                .target("http://localhost:9090/upload")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(entity);
        response.readEntity(String.class);
    }

    public static class Test{
        public static void main(String[] args) {

        }
    }
}
