package apachepools.faststart;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2019/10/11 10:16
 * @description
 */
@Configuration
public class PoolConfig {
    @Bean
    public GenericObjectPoolConfig config(){
        return new GenericObjectPoolConfig();
    }

    @Bean
    public ResourcePoolFactory factory(){
        return new ResourcePoolFactory();
    }

    @Bean
    public GenericObjectPool<Resource> pool(GenericObjectPoolConfig config,ResourcePoolFactory factory){
        return new GenericObjectPool<Resource>(factory,config);
    }
}
