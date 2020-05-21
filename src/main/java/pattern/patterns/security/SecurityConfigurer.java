package pattern.patterns.security;

/**
 * @author payno
 * @date 2020/5/21 16:45
 * @description
 */
public interface SecurityConfigurer<T extends Builder<Filter>> extends Configurer<Filter,T>{
    /**
     *  继承该接口的类或者接口将具有以下性质，具体的Builder使用泛型
     *      能够使用init初始化构造器，并利用configure来配置config
     *      利用build最终会得到一个Filter
     *
     * @SpringSecurity中的Configurer最终能够创建出Filter
     */
}
