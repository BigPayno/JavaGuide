package shiro.subject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author payno
 * @date 2019/9/18 16:26
 * @description
 */
public class Authc {
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

    private void login(Subject subject,AuthenticationToken token){
        try {
            subject.login(token);
            //没有抛异常则登录成功
            System.out.println(subject.getPrincipals()+"登陆该设备");
        } catch ( UnknownAccountException uae ) {
            System.out.println("用户名"+subject.getPrincipal()+"不存在");
        } catch ( IncorrectCredentialsException ice ) {
            System.out.println(subject.getPrincipal()+"密码错误");
        } catch ( LockedAccountException lae ) {
            System.out.println("用户"+subject.getPrincipal()+"被锁定，不能登录");
        } catch ( AuthenticationException ae ) {
            System.out.println("严重的错误");
        }
    }

    @Test
    public void testLogin(){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("payno0","666");
        UsernamePasswordToken token2=new UsernamePasswordToken("payno","665");
        UsernamePasswordToken token3=new UsernamePasswordToken("payno","666");
        UsernamePasswordToken token4=new UsernamePasswordToken("payno2","666");
        login(subject,token);
        login(subject,token2);
        login(subject,token3);
        login(subject,token4);
        subject.getPrincipals().asList().forEach(System.out::println);
    }

    @Test
    public void testSubject(){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token3=new UsernamePasswordToken("payno","666");
        UsernamePasswordToken token4=new UsernamePasswordToken("payno2","666");
        login(subject,token3);
        login(subject,token4);
        subject.getPrincipals().asList().forEach(System.out::println);
    }
}
