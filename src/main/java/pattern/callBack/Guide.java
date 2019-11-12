package pattern.callBack;

/**
 * @author payno
 * @date 2019/10/21 17:20
 * @description
 */
public class Guide {
    public static void main(String[] args) {
        new Executor<String>("abc")
                .doIt(v->{
                    System.out.println(v); })
                .doIt(v->{
                    System.out.println(v.getClass());
                });
    }
}
