package xml.sax;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author payno
 * @date 2019/9/2 15:48
 * @description 不支持嵌套，支持map类型
 */
@Getter
public class JsonHandler extends DefaultHandler {
    private boolean currentElement=false;
    private String rootElementName;
    private JSONObject json=null;
    private String value=null;
    private JsonHandler(String rootElementName){
        super();
        this.rootElementName=rootElementName;
    }
    public static JsonHandler of(String rootElementName){
        return new JsonHandler(rootElementName);
    }
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(rootElementName.equals(qName)){
            json=new JSONObject();
            currentElement=true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(rootElementName.equals(qName)){
            currentElement=false;
        }else if(currentElement){
            json.put(qName,value);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //获取节点值数组
        value = new String(ch, start, length);
    }
}
