package spring.beans;

import com.google.common.collect.MapMaker;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Map;

/**
 * @author payno
 * @date 2020/4/23 20:24
 * @description
 *      SimpleContext   不考虑异常状况和Alias
 */
public class SimpleContext implements BeanDefinitionRegistry {

    Map<String,BeanDefinition> beanDefinitionMap = new MapMaker().makeMap();

    @Override
    public void registerBeanDefinition(String s, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        beanDefinitionMap.put(s,beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String s) throws NoSuchBeanDefinitionException {
        beanDefinitionMap.remove(s);
    }

    @Override
    public BeanDefinition getBeanDefinition(String s) throws NoSuchBeanDefinitionException {
        return beanDefinitionMap.get(s);
    }

    @Override
    public boolean containsBeanDefinition(String s) {
        return beanDefinitionMap.keySet().contains(s);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.keySet().size();
    }

    @Override
    public boolean isBeanNameInUse(String s) {
        return beanDefinitionMap.keySet().contains(s);
    }

    @Override
    public void registerAlias(String s, String s1) {

    }

    @Override
    public void removeAlias(String s) {

    }

    @Override
    public boolean isAlias(String s) {
        return false;
    }

    @Override
    public String[] getAliases(String s) {
        return new String[0];
    }
}
