package encryption.type;

/**
 * @author payno
 * @date 2020/3/3 12:16
 * @description
 */
public class Main {
    public static void main(String[] args) {
        String user="query";
        String pass="Sugar@2015";
        System.out.println(MD5Util.bit32(pass)+Base64.encode(user));
        System.out.println(MD5Util.bit32(pass)+Base64.encode("gzQuery"));
        System.out.println(org.apache.shiro.codec.Base64.encodeToString("app1:112233".getBytes()));
    }
}
