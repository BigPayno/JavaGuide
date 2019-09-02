package xml.xpath;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utils.charset.Charsets;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author payno
 * @date 2019/9/2 09:53
 * @description
 */
public class XPathGuide {
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
    public void test1(){
        System.out.println(document);
    }

    @Test
    public void test2(){
        List<Node> nodes = document.selectNodes("/bookstore");
        System.out.println(nodes);
    }

    @Test
    public void test3(){
        List<Node> nodes = document.selectNodes("//@lang");
        for(Node node:nodes){
            System.out.println(node.getText());
        }
        Node node = document.selectSingleNode("//title[@lang='cn']");
        System.out.println(node.getText());
    }

    @Test
    public void test4(){
        Element element= (Element)document.selectSingleNode("//title[@lang='cn']");
        element.attribute("lang").setValue("USA");
    }

    @After
    public void after(){
        try {
            FileOutputStream fos=new FileOutputStream(file);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf8");
            XMLWriter writer=new XMLWriter(fos,format);
            writer.write(document);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
