package xml.jaxb;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.web.JsonPath;
import utils.charset.Charsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringReader;

/**
 * @author payno
 * @date 2019/9/2 14:39
 * @description
 */
public class JaxbGuide {
    private static Boy boy;
    private static People people;
    private static Request<Json> request;
    @Before
    public void before() throws Exception{
        boy=new Boy();
        people=new People();
        String jsonString= Files.asCharSource(new File("d://zero.json"), Charsets.UTF_8).read();
        request=new Request<Json>();
        Json json=new Json();
        json.setJsonString(jsonString);
        request.setRequest(json);
    }
    @Test
    public void test1() throws Exception{
        JAXBContext context=JAXBContext.newInstance(Boy.class);
        Marshaller marshaller=context.createMarshaller();
        marshaller.marshal(boy,System.out);
        //<?xml version="1.0" encoding="UTF-8" standalone="yes"?><boy><name>payno</name></boy>
        String xml="<boy><name>payno</name></boy>";
        Boy boy2=(Boy) context.createUnmarshaller().unmarshal(new StringReader(xml));
        System.out.println(boy2.getName());
    }

    @Test
    public void test2() throws Exception{
        JAXBContext.newInstance(People.class).createMarshaller().marshal(people,System.out);
        //<?xml version="1.0" encoding="UTF-8" standalone="yes"?><p><perName>payno</perName></p>
    }

    @Test
    public void test3() throws Exception{
        JAXBContext.newInstance(Request.class).createMarshaller().marshal(request,System.out);
    }

    @Test
    public void test4() throws Exception{
        String xml=Xmls.toXml(people).read();
        System.out.println(xml);
        People people=Xmls.toJavaObject(CharSource.wrap(xml),People.class);
        System.out.println(people);


        String userName="";
        JSONObject js=new JSONObject();
        String userId=(String) JSONPath.read(
                js.toJSONString(),String.format("$.userList[@name=%s].userId",userName));
    }
}
