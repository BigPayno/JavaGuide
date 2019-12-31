package idea;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author payno
 * @date 2019/12/31 09:06
 * @description
 */
public class IdeaTemplate {
    public void foreach(){
        List<String> words=new ArrayList<>();
        /**
         * words.for+tab
         */
        for (String word : words) {

        }
    }

    @Data
    public class User{
        Date date;
    }

    public void var(){
        User user=new User();
        /**



     ``    * user.getDate().var+tab
         */
        Date date = user.getDate();
    }
}
