package javassist;

/**
 * @author payno
 * @date 2019/11/21 15:50
 * @description
 */
public class ClassGuide {
    public static void main(String[] args) {
        ClassPool classPool=ClassPool.getDefault();
        classPool.getImportedPackages().forEachRemaining(System.out::print);
        /**
         * 定义新类
         */
        CtClass newClass=classPool.makeClass("Point");
        /**
         *将类冻结
         * 如果一个 CtClass 对象通过 writeFile(), toClass(), toBytecode()
         * 被转换成一个类文件，此 CtClass 对象会被冻结起来，不允许再修改。
         * 因为一个类只能被 JVM 加载一次。
         */
        try {
            newClass.writeFile();
            newClass.defrost();
            newClass.toBytecode();
        }catch (Exception e){ }

        /**
         * 如果 ClassPool.doPruning 被设置为 true，Javassist 在冻结 CtClass 时
         * ，会修剪 CtClass 的数据结构。为了减少内存的消耗，修剪操作会丢弃
         * CtClass 对象中不必要的属性。例如，Code_attribute 结构会被丢弃。
         * 一个 CtClass 对象被修改之后，方法的字节码是不可访问的，但是方法名称
         * 、方法签名、注解信息可以被访问。修剪过的 CtClass 对象不能再次被解冻。
         * ClassPool.doPruning 的默认值为 false。
         */
        newClass.stopPruning(true);
    }
}
