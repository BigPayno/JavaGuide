package javassist;

/**
 * @author payno
 * @date 2019/11/21 15:42
 * @description
 */
public class ClassPoolGuide {
    public static void main(String[] args) throws Exception{
        /**
         * 静态方法，获得类池
         */
        ClassPool classPool=ClassPool.getDefault();
        /**
         * 获得类加载器，默认为当前线程上下文的类加载器
         */
        ClassLoader classLoader= ClassPool.getContextClassLoader();
        /**
         * 竟然还在使用hashTable这种同步容器
         */
        classPool.getCached("");
        /**
         * 竟然是重新建立List
         */
        classPool.clearImportedPackages();
        /**
         * 通过 ClassPool.getDefault() 获取的 ClassPool 使用 JVM 的类搜索路径。
         * 如果程序运行在 JBoss 或者 Tomcat 等 Web 服务器上，
         * ClassPool 可能无法找到用户的类，因为 Web 服务器使用多个类加载器作为
         * 系统类加载器。在这种情况下，ClassPool 必须添加额外的类搜索路径。
         */
        classPool.getClassLoader().loadClass("java.util.Random");
        classPool.getImportedPackages().forEachRemaining(System.out::print);
        System.out.println();
        /**
         * 不懂为何没用
         */
        classPool.insertClassPath(new ClassClassPath(ClassPoolGuide.class));
        /**
         * 尝试用绝对路径
         */
        classPool.insertClassPath("/javassist");
        classPool.getImportedPackages().forEachRemaining(System.out::print);
    }
}
