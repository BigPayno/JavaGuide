package guava.eventbus.fastbuild;

import com.google.common.base.MoreObjects;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.EventBus;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author payno
 * @date 2019/10/22 09:49
 * @description
 */
public final class Registry {
    private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<Object>> registers
            = Maps.newConcurrentMap();
    private EventBus eventBus;
    public Registry(EventBus eventBus){
        this.eventBus=checkNotNull(eventBus);
    }
    private Multimap<Class<?>, Object> findAllSubscribers(Object listener) {
        Multimap<Class<?>, Object> methodsInListener = HashMultimap.create();
        Class<?> clazz = listener.getClass();
       /* for (Method method : getAnnotatedMethods(clazz)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            methodsInListener.put(eventType, Subscriber.create(bus, listener, method));
        }*/
        return methodsInListener;
    }
}
