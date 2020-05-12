package pattern.patterns.report.esb;

import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import pattern.patterns.report.spi.esb.context.IEsbContextSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class XmlContextHolder {
	
	@Autowired
	public IEsbContextSource contextSource;
	
	public Marshaller marshaller;
	public Unmarshaller unmarshaller;
	
	Map<Class<?>, Unmarshaller> unmarshallers =
		new ConcurrentHashMap<Class<?>, Unmarshaller>();
	
	@PostConstruct
	public void initConverter() {
		try {
			JAXBContext context=JAXBContext.newInstance(
					contextSource.getSupportClasses());
			marshaller=context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, Charsets.UTF_8.name());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			unmarshaller=context.createUnmarshaller();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized <T> T toJavaObject(String xml,Class<T> clazz) throws Exception{
		try {
			Unmarshaller classUnmarshaller=unmarshallers.get(clazz);
			if (classUnmarshaller==null) {
				classUnmarshaller=JAXBContext.newInstance(clazz).createUnmarshaller();
				unmarshallers.putIfAbsent(clazz, classUnmarshaller);
				log.info("XmlContextHolder add unmarshaller of [{}]",clazz);
			}
			Object object=classUnmarshaller.unmarshal(new StringReader(xml));
			return clazz.cast(object);
		}catch(Exception e) {
			log.error("打印异常栈！",e);
			return clazz.newInstance();
		}
	}
}
