package pattern.patterns.security;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/21 16:52
 * @description
 */
@Setter
public class FilterChainBuilder implements Builder<Filter>{

    List<Filter> filters;

    @Override
    public Filter build() throws Exception {
        return new FilterChain(filters);
    }

    @AllArgsConstructor
    public static class FilterChain implements Filter{
        List<Filter> filters;

        public void init(List<Filter> filters){
            Collections.sort(filters);
        }

        @Override
        public int compareTo(Filter o) {
            return 0;
        }
    }
}
