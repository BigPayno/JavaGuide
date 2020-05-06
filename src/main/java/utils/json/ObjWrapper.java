package utils.json;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author payno
 * @date 2020/5/6 15:09
 * @description
 */
@Slf4j
public class ObjWrapper {
    Object object;

    public static ObjWrapper wrap(Object object){
        ObjWrapper wrapper = new ObjWrapper();
        wrapper.object = object;
        return wrapper;
    }

    public <T> T get(Class<T> tClass){
        if(object!=null){
            if(object.getClass()==tClass){
                return (T)object;
            }else{
                log.info("[{}] with class[{}] can not get with class[{}] and return null",object,object.getClass(),tClass);
                return null;
            }
        }else {
            return null;
        }
    }

    public <T> Optional<T> getOpt(Class<T> tClass){
        if(object != null){
            if(object.getClass()==tClass){
                return Optional.of((T)object);
            }else{
                log.info("[{}] with class[{}] can not get with class[{}] and return null",object,object.getClass(),tClass);
                return Optional.ofNullable(null);
            }
        }else{
            return Optional.ofNullable(null);
        }
    }

    public ObjWrapper tryMap(Predicate<Object> predicate,Function<Object,?> map){
        if(object != null&&predicate.test(object)){
            this.object = map.apply(object);
        }
        return this;
    }

    public ObjWrapper tryMap(Function<String,?> map){
        if(object instanceof String&&object != null){
            this.object = map.apply((String) object);
        }
        return this;
    }
}
