package shiro.subject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author payno
 * @date 2019/9/18 17:19
 * @description
 */
public class Authz {
    SimpleAccountRealm realm;
    DefaultSecurityManager securityManager;
    @Before
    public void init(){
        realm=new SimpleAccountRealm();
        realm.addAccount("payno","666","admin","user");
        realm.addAccount("payno2","666","user");
        realm.addRole("admin");
        securityManager=new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void testSubject(){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("payno","666");
        subject.login(token);
        subject.checkRole("admin");
        subject.isPermitted("a");
    }
}
