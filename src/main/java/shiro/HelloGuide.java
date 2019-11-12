package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author payno
 * @date 2019/9/18 16:03
 * @description
 */
public class HelloGuide {
    SimpleAccountRealm realm;
    DefaultSecurityManager securityManager;
    @Before
    public void init(){
        realm=new SimpleAccountRealm();
        realm.addAccount("payno","666");
        realm.addAccount("payno2","666");
        securityManager=new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void test(){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("payno","666");
        subject.login(token);
    }
}
