package xml.jaxb;

import com.google.common.base.Charsets;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayInputStream;

/**
 * @author payno
 * @date 2019/12/19 09:48
 * @description
 *      JAXBContext是我们在使用JAXB时的入口类，我们需要通过它的实例来建立XML和Java类之间的映射关系，
 *      需要通过它来创建用于转换Java对象到XML的Marshaller或是创建用于转换XML到Java对象的Unmarshaller。
 */
public class JaxbContextGuide {
    /**
     * 按照JAXB的规范，我们需要在对应的包中创建一个jaxb.index文件，
     * 然后在其中指定创建JAXBContext时需要用到的Class，每个Class名称占一行，
     * 只需要写Class名称即可
     */
    @Test
    public void init() throws Exception{
        JAXBContext jaxbContext=JAXBContext.newInstance("xml.jaxb");
        People people=new People();
        Marshaller marshaller=jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,false);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
        marshaller.marshal(people,System.out);
    }
}
