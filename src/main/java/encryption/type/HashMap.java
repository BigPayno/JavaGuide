package encryption.type;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;

import java.security.Key;

/**
 * @author payno
 * @date 2019/11/20 15:35
 * @description
 */
public class HashMap {
    public void hash() throws Exception{
        byte[] key="abc".getBytes();
        HashFunction hashFunction= Hashing.hmacSha512(key);
        ByteSource.wrap("mmm".getBytes()).hash(hashFunction).toString();
    }
}
