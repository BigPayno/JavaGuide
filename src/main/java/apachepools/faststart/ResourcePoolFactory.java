package apachepools.faststart;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author payno
 * @date 2019/10/11 09:48
 * @description
 */
public final class ResourcePoolFactory extends BasePooledObjectFactory<Resource> {
    public ResourcePoolFactory() {
        super();
    }

    @Override
    public Resource create() throws Exception {
        return new Resource();
    }

    @Override
    public PooledObject<Resource> wrap(Resource resource) {
        return new DefaultPooledObject<Resource>(resource);
    }
}
