package pattern.patterns.security;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author payno
 * @date 2020/5/21 17:15
 * @description
 *      多线程下安全的构建器，使用了模板方法
 */
public abstract class AbstractBuilder<O> implements Builder<O>{
    private AtomicBoolean building = new AtomicBoolean();

    private O object;


    @Override
    public final O build() throws Exception {
        if (this.building.compareAndSet(false, true)) {
            this.object = doBuild();
            return this.object;
        }
        throw new IllegalStateException("This object has already been built");
    }

    /**
     * Gets the object that was built. If it has not been built yet an Exception is
     * thrown.
     *
     * @return the Object that was built
     */
    public final O getObject() {
        if (!this.building.get()) {
            throw new IllegalStateException("This object has not been built");
        }
        return this.object;
    }

    /**
     * Subclasses should implement this to perform the build.
     *
     * @return the object that should be returned by {@link #build()}.
     *
     * @throws Exception if an error occurs
     */
    protected abstract O doBuild() throws Exception;
}
