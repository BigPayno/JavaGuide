package utils.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author payno
 * @date 2019/7/31 09:41
 * @description 问我我也不知道
 * https://stackoverflow.com/questions/2145431/https-using-jersey-client
 */
public class InsecureHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession sslSession) {
        return true;
    }
}
