package xml.sax;

import com.google.common.base.Strings;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * @author payno
 * @date 2019/9/2 11:29
 * @description
 */
@Getter
public class BookStoreHandler extends DefaultHandler {
    String value = null;
    Book book = null;
    private ArrayList<Book> bookList = new ArrayList<Book>();
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
        if(qName.equals("book")){
            System.out.println("开始解析Book结点:");
            book=new Book();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("book")){
            bookList.add(book);
            book = null;
        }else if(qName.equals("title")){
            book.setTitle(value);
        }else if(qName.equals("author")){
            book.setAuthor(value);
        }else if(qName.equals("price")){
            book.setPrice(value);
        }else if(qName.equals("year")){
            book.setYear(value);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //获取节点值数组
        value = new String(ch, start, length);
        if(!StringUtils.isBlank(value)){
            System.out.println("节点值："+value);
        }
    }
}
