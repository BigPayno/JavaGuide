package xml.sax;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;
import xml.sax.BookStoreHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * @author payno
 * @date 2019/9/2 11:24
 * @description
 */
public class Dom4jGuide {
    private Document document;
    private Node node;
    private static File file=null;
    static {
        file=new File("d://hello.xml");
    }
    @Before
    public void before(){
        try {
            document=new SAXReader().read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception{
        SAXParser saxParser=SAXParserFactory.newInstance().newSAXParser();
        BookStoreHandler handler=new BookStoreHandler();
        saxParser.parse(file,handler);
        handler.getBookList().forEach(System.out::println);
    }
}
