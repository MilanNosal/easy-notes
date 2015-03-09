package easynotes.concerns;

/**
 * Annotated target program elements implement filtering of the notes.
 *
 * @author Milan
 */
public @interface Filtering {

    public enum Role {
        FILTER_IMPLEMENTATION,
        FILTER_MANAGEMENT,
        FILTERING
    }

    /**
     * Parameter role with FILTER_IMPLEMENTATION value indicates that
     * the annotated target element implements a specific filter. FILTER_MANAGEMENT
     * value indicates that the target element manages filter implementations. FILTERING
     * indicates other filtering related options, such as filtering algorithms, etc.
     */
    public Role role();
}
