package easynotes.concerns;

public @interface Misc {
    public enum Type {
        CONFIGURATION,
        UTILITIES
    }
    
    public Type value();
}
