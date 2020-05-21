package pattern.patterns.security;

/**
 * @author payno
 * @date 2020/5/21 16:37
 * @description
 */
public interface Configurer<O, B extends Builder<O>> {
    /**
     * Initialize the {@link Builder}. Here only shared state should be created
     * and modified, but not properties on the {@link Builder} used for building
     * the object. This ensures that the {@link #configure(Builder)} method uses
     * the correct shared objects when building. Configurers should be applied here.
     *
     * @param builder
     * @throws Exception
     */
    void init(B builder) throws Exception;

    /**
     * Configure the {@link Builder} by setting the necessary properties on the
     * {@link Builder}.
     *
     * @param builder
     * @throws Exception
     */
    void configure(B builder) throws Exception;
}
