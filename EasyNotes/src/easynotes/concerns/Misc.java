package easynotes.concerns;

/**
 * Something like a "custom" annotation type to cover concerns that cover only
 * a single target element. Used to reduce number of annotation types.
 * @author Milan
 */
public @interface Misc {
    public String value();
}
