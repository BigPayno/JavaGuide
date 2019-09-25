package shiro.subject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 * @author payno
 * @date 2019/9/18 16:46
 * @description
 */
public class TokenGuide {
    SimpleAccountRealm realm;
    DefaultSecurityManager securityManager;
    RememberMeManager rememberMeManager;
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
    public void remeberOrAuthenticated(){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken remeberMe=new UsernamePasswordToken("payno","666");
        remeberMe.setRememberMe(true);
        UsernamePasswordToken authenticatedMe=new UsernamePasswordToken("payno2","666");
        authenticatedMe.setRememberMe(false);
        subject.login(remeberMe);
        System.out.println(subject.isRemembered());
        subject.logout();
        subject.login(authenticatedMe);
        System.out.println(subject.isAuthenticated());
        subject.logout();
    }
}
