package xml.jaxb;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

/**
 * @author payno
 * @date 2019/9/2 15:15
 * @description
 */
@Slf4j
public class Xmls {
    public static <T> T toJavaObject(CharSource charSource, Class<T> type){
        T t=null;
        try(Reader reader=charSource.openStream()){
            Object object=JAXBContext.newInstance(type).createUnmarshaller().unmarshal(reader);
            t=type.cast(object);
        }catch (IOException| JAXBException e){
            e.printStackTrace();
        }
        return t;
    }
    public static CharSource toXml(Object obj){
        CharSource charSource=null;
        try(StringWriter writer=new StringWriter()){
            Marshaller marshaller=JAXBContext.newInstance(obj.getClass()).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,false);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            marshaller.marshal(obj,writer);
            charSource=CharSource.wrap(writer.toString());
        }catch (IOException| JAXBException e){
            e.printStackTrace();
        }
        return charSource;
    }
}
