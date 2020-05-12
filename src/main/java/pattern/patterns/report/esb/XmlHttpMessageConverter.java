package pattern.patterns.report.esb;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/*
 *	本HttpMessageConverter仅支持V2EsbReq.class,V2EsbRes.class
 *	如果需要拓展，需要按照以下几点
 *	1.如果XML类存在泛型，需要使用@XmlSeeAlso,或者在JAXBContextSource中load相关类
 *	
 *	本类可以在以下两个场景使用
 *	1.SpringMvc拓展HttpMessageConverter，自动将HttpBody转换成相关Java对象
 *		只需要使用@RequestBody与@ResponseBody
 *	2.Spring的RestTemplate中添加
 *		可以将请求和响应中的对象与HttpBody互相转换
 */
@Getter
@Setter
@Component
public class XmlHttpMessageConverter<T> extends AbstractXmlHttpMessageConverter<T>{
	
	@Autowired
	XmlContextHolder holder;
	
	@Override
	protected T readFromSource(Class<? extends T> clazz, HttpHeaders header, Source source) throws IOException {
		if(source instanceof StreamSource) {
			StreamSource streamSource=(StreamSource)source;
			try {
				Object object = holder.unmarshaller.unmarshal(streamSource,clazz).getValue();
				//思考使用另一种方式
				return clazz.cast(object);
			} catch (Exception e) {
				throw new HttpMessageConversionException(
					"xml http converter convert stream to java object failed!");
			}
		}else {
			throw new HttpMessageConversionException(
				"xml http converter not support this type of stream!");
		}
	}

	@Override
	protected void writeToResult(T t, HttpHeaders headers, Result result) throws IOException {
		if(result instanceof StreamResult) {
			StreamResult streamResult=(StreamResult)result;
			Writer writer=new OutputStreamWriter(streamResult.getOutputStream());
			writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			try {
				holder.marshaller.marshal(t, writer);
			} catch (JAXBException e) {
				throw new HttpMessageConversionException(
					"xml http converter convert java object to stream failed!");
			}
		}else {
			throw new HttpMessageConversionException(
					"xml http converter not support this type of stream!");
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return holder.contextSource.supports(clazz);
	}
}
