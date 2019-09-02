package xml.sax;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import javax.print.Doc;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author payno
 * @date 2019/9/2 11:24
 * @description
 */
public class Dom4jGuide {
    private Document document;
    private Node node;
    private SAXParser saxParser;
    private static File file=null;
    static {
        file=new File("d://hello.xml");
    }
    @Before
    public void before(){
        try {
            document=new SAXReader().read(file);
            saxParser=SAXParserFactory.newInstance().newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws Exception{
        BookStoreHandler handler=new BookStoreHandler();
        saxParser.parse(file,handler);
        handler.getBookList().forEach(System.out::println);
    }

    @Test
    public void test2() throws Exception{
        JsonHandler handler= JsonHandler.of("book");
        saxParser.parse(file,handler);
        Book book=handler.getJson().toJavaObject(Book.class);
        System.out.println(book);
    }

    private String valueOf(Element element,String qName){
        if(element.element(qName)==null){
            return null;
        }else{
            return element.element(qName).getStringValue();
        }
    }
    @Test
    public void test3() throws Exception{
        List<Book> books=new ArrayList<>();
        document.selectNodes("/bookstore/book").stream().forEach(node -> {
            try{
                Book book=new Book();
                Element element=(Element)node;
                List<Field> fieldList= ImmutableList.copyOf(Book.class.getDeclaredFields());
                for (Field field:fieldList) {
                    field.setAccessible(true);
                    field.set(book,valueOf(element,field.getName()));
                }
                books.add(book);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        books.forEach(System.out::println);
    }
}
