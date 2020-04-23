package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.net.util.Base64;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author payno
 * @date 2019/12/31 10:15
 * @description
 */
public class JwtGuide {
    private static final long EXPIRE_TIME=60*60*1000;
    /**
     *  Emily,let the night take me!
     *  down down down the rabbit hole
     */
    public String createJwt(){
        /**
         * 构造私有声明
         */
        Map<String,Object> claims=new HashMap<>();
        claims.put("username","chad");
        claims.put("password", "payno");
        /**
         * 密钥
         */
        byte[] encodedKey= Base64.decodeBase64("Emily,let the night take me");
        SecretKey secretKey=new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
        /**
         * 构造时间与释放时间
         */
        long issueAt=System.currentTimeMillis();
        long expireAt=issueAt+EXPIRE_TIME;
        JwtBuilder builder=Jwts.builder()
                //私有声明
                .setClaims(claims)
                //jwtId
                .setId(UUID.randomUUID().toString())
                //签发jwt的时间
                .setIssuedAt(new Date(issueAt))
                //释放jwt时间
                .setExpiration(new Date(expireAt))
                //签发者
                .setIssuer("chad")
                //面向用户
                .setSubject("all")
                //签名
                .signWith(SignatureAlgorithm.HS256,secretKey);
        String token=builder.compact();
        return token;
    }

    @Test
    public void display(){
        System.out.println(createJwt());
    }

    @Test
    public void decode(){
        /**
         * 密钥
         */
        byte[] encodedKey= Base64.decodeBase64("Emily,let the night take me");
        SecretKey secretKey=new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
        Claims claims=Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(createJwt())
                .getBody();
        claims.forEach((key,val)->{
            System.out.printf("key[%s],val[%s]%n",key,val);
        });
    }
}
