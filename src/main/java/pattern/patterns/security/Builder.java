package pattern.patterns.security;

/**
 * @author payno
 * @date 2020/5/21 16:38
 * @description
 */
public interface Builder<O> {
    /**
     * Builds the object and returns it or null.
     *
     * @return the Object to be built or null if the implementation allows it.
     * @throws Exception if an error occurred when building the Object
     */
    O build() throws Exception;
}
