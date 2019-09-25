package utils.ftp.demo;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/8 10:25
 * @description
 */
public class FtpServerDemo {
    public static BaseUser getUser() {
        BaseUser user = new BaseUser();
        user.setName("payno");
        user.setPassword("9527");
        user.setHomeDirectory("d://parser");
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        return user;
    }

    public static void start() throws Exception {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(21);
        serverFactory.addListener("default", listenerFactory.createListener());
        serverFactory.getUserManager().save(getUser());
        FtpServer ftpServer = serverFactory.createServer();
        ftpServer.start();
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}
