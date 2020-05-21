package pattern.patterns.security;

/**
 * @author payno
 * @date 2020/5/21 17:03
 * @description
 */
public class Main {
    public static void main(String[] args) throws Exception{
        FilterChainBuilder builder = new FilterChainBuilder();
        FilterChainSpringSecurityConfigurer configurer=new FilterChainSpringSecurityConfigurer();
        configurer.init(builder);
        configurer.configure(builder);
        Filter chain = builder.build();
    }
}
