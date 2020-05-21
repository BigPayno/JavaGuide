package pattern.patterns.security;

import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2020/5/21 16:56
 * @description
 */
public class FilterChainSpringSecurityConfigurer extends AbstractSpringSecurityConfigurer<FilterChainBuilder> {
    @Override
    public void init(FilterChainBuilder builder) throws Exception {
        if(builder==null){
            builder = new FilterChainBuilder();
        }
    }

    @Override
    public void configure(FilterChainBuilder builder) throws Exception {
        builder.setFilters(
                this.context.getBeansOfType(Filter.class)
                        .values().stream().collect(Collectors.toList())
        );
    }
}
