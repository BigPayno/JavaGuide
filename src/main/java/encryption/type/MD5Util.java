package encryption.type;

import java.security.MessageDigest;


/**
 * Created by smart on 2019/1/16.
 */
public class MD5Util {
    /**
     * 通用MD5处理工具类
     * 
     * @param text
     * @return
     */
    public static String bit32(String text) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes("UTF-8"));
            byte[] b = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
        } catch (Exception e) {

        }
        return sb.toString().toUpperCase();
    }
}
