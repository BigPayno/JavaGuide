package guava.hash;

import com.google.common.hash.Hashing;

/**
 * @author payno
 * @date 2019/10/12 17:41
 * @description
 */
public class HashingGuide {
    public static void main(String[] args) {
        Hashing.sha256().hashBytes(new String("payno").getBytes()).toString();
        //Hashing.md5().hashBytes(new String("payno").getBytes()).toString();
        Hashing.hmacMd5("liam".getBytes()).hashBytes(new String("payno").getBytes()).toString();
    }
}
