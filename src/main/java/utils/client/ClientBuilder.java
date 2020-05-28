package utils.client;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Stream;

/**
 * @author payno
 * @date 2019/7/31 09:45
 * @description 我喜欢链式编程 语法糖真甜
 */
public abstract class ClientBuilder extends javax.ws.rs.client.ClientBuilder {
    private static final TrustManager[] TRUST_ALL_CERTS = {new InsecureTrustManager()};
    private static final HostnameVerifier ALL_HOSTS_VALID = new InsecureHostnameVerifier();
    public static Client newHttpsClient() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLSv1");
            System.setProperty("https.protocols", "TLSv1");
            sslContext.init(null, TRUST_ALL_CERTS, new java.security.SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
            Stream.of()
        }
        return newBuilder().sslContext(sslContext).hostnameVerifier(ALL_HOSTS_VALID)
                .build();
    }

    public static Client getClientByUrl(String url) {
        if (url.startsWith("https")) {
            return newHttpsClient();
        } else {
            return newClient();
        }
    }

    public static Response restRequest(String url, String request) {
        return ClientBuilder.getClientByUrl(url)
                .target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
    }
}
