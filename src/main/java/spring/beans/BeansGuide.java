package spring.beans;

import org.junit.Test;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.AttributeAccessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author payno
 * @date 2020/4/23 20:04
 * @description
 *  AttributeAccessor <|---  AttributeAccessorSupport <>---> Map<String,Object> attributes
 *  AttributeAccessorSupport提供了基本的set、get、copy、getNames等功能，是个抽象类
 *
 *  BeanMetadataElement 定义了获取source的接口,BeanMetadataAttribute是一个BeanMetadataElement实现类
 *  BeanMetadataAttributeAccessor（AttributeAccessorSupport、BeanMetadataElement）
 *
 *  AbstractBeanDefinition 继承 BeanMetadataAttributeAccessor 是抽象类
 *  其定义了跟Bean依赖以及生命周期方法等相关的属性与方法
 *
 *  GenericBeanDefinition：一个综合性的标准BeanDefiniton，包含了BeanDefinition中的主要功能
 *  AnnotatedGenericBeanDefinition：一个注解式的标准BeanDefiniton，继承了GenericBeanDefinition，在其基础上增加了对类注解配置的支持，即可通过该类获取注解配置。
 *  ConfigurationClassBeanDefinition：如果spring中的配置是用类来表示的，则解析后生成的bean配置类为该类
 *
 *  BeanDefinitionHolder
 *  BeanDefinitionRegistry
 *  BeanDefinitionReader
 *  BeanWrapper  包含Bean
 *  ObjectFactory
 *  FactoryBean
 */
public class BeansGuide {

    @Test
    public void factory(){
        DefaultListableBeanFactory factory;
        MutablePropertyValues values=new MutablePropertyValues();
        values.add("name","my dog haha ");
        BeanDefinition definition=new RootBeanDefinition(Payno.class,null,values);
    }

    @Test
    public void accessor(){
        GenericApplicationContext ctx = new GenericApplicationContext();
        Resource res = new ClassPathResource("payno.properties");
        PropertiesBeanDefinitionReader propReader = new PropertiesBeanDefinitionReader(ctx);
        propReader.loadBeanDefinitions(res);
        BeanDefinition paynoDefinition = ctx.getBeanDefinition("payno");
        System.out.println(ctx.getBean("payno"));
    }
}
