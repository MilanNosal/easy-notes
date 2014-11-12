package easynotes.concerns;

public @interface Filtering {
    public enum Role {
        FILTER_IMPLEMENTATION,
        FILTER_MANAGEMENT,
        
    }
    
    public Role role();
}
