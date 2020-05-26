package pattern.context;

/**
 * @author payno
 * @date 2020/5/22 09:47
 * @description
 *      Context的最大作用就是屏蔽参数的内部细节
 *          1.可以把根据Context得到某些类型如T，而不需要关注T由哪些对象组成和其具体的组合逻辑
 *          2.Context作为参数时还可以在接口参数发生变化时，只需要改动Context而不是改接口
 *              @patterns.report就类似这样的设计
 *
 */
public interface Context<T> {
    T get();
}
