package xml;

/**
 * @author payno
 * @date 2020/3/23 17:19
 * @description
 *      FWK005 parse may called while parsing
 *      在解析时，多个线程调用DocumentBuilder导致的快速失败
 *      要么使用同步锁sync，要不使用ThreadLocal使每个线程持有一个DocumentBuilder
 */
public class DocumentBuilderException {
}
