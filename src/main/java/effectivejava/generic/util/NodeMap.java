package effectivejava.generic.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author payno
 * @date 2019/8/28 21:54
 * @description
 */
public class NodeMap{
    private NodeMap(){ }
    private Map<String,Node> nodeMap=new HashMap<>();
    public <T> void put(String key,Class<T> type,Object instance){
        nodeMap.put(key,Node.of(type,instance));
    }
    public <T> T get(String key,Class<T> type){
        return nodeMap.get(key).get(type);
    }
    public static NodeMap instance(){
        return new NodeMap();
    }
}
