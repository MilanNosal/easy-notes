package easynotes.concerns;

public @interface NotesLifecycle {
    public enum Phase {
        CREATION,
        REMOVAL,
        PERSISTENCE
    }
    
    public Phase phase();
}
