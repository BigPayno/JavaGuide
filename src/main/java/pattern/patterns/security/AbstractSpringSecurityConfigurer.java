package pattern.patterns.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author payno
 * @date 2020/5/21 16:50
 * @description
 *      这个抽象类具有获取Context的能力了
 */
public abstract class AbstractSpringSecurityConfigurer<T extends Builder<Filter>> implements SecurityConfigurer<T>, ApplicationContextAware {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
