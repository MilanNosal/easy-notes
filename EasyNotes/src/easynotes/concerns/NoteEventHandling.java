package easynotes.concerns;

public @interface NoteEventHandling {
    public enum Type {
        PROPAGATION,
        EVENT,
        SOURCE,
        HANDLING
    }
    
    public Type type();
}
