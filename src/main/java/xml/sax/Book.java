package xml.sax;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2019/9/2 11:34
 * @description
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {
    private String title;
    private String author;
    private String year;
    private String price;
}
