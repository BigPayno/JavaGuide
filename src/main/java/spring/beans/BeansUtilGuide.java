package spring.beans;

import org.springframework.beans.BeanUtils;

/**
 * @author payno
 * @date 2019/11/12 18:09
 * @description
 */
public class BeansUtilGuide {
    public static void main(String[] args) {
        BeanUtils.copyProperties(new Object(),new Object());
    }
}
