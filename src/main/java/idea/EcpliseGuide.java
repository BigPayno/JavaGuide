package idea;

/**
 * @author payno
 * @date 2019/12/30 16:20
 * @description
 */
public class EcpliseGuide {
    /**
     * 真的坑
     * Ecplise的Apt插件使用
     * 必须满足以下条件
     * 1.Compiler模块必须配置好jdk版本及compiler插件版本
     * 2.关闭自动build
     * 3.clean->install
     *
     * ex:必须更新maven.pom（maven/update），否则默认执行缓存配置（猜测，导致Q类始终无法使用，即使包和classes文件夹都已经生成）
     *      而且project/clean会清除class中的Q.class
     */

    /**
     * 吐了
     * 集成环境不需要配置环境变量
     * 但是ecplise.ini启动配置必须是jdk
     * 然后集成环境配置好其他配置即可
     */
}
