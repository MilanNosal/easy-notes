package easynotes.concerns;

/**
 * Implementation of a note lifecycle.
 * @author Milan
 */
public @interface NotesLifecycle {
    public enum Phase {
        /**
         * Considers creation of a new note.
         */
        CREATION,
        /**
         * Considers removal of an existing note.
         */
        REMOVAL,
        /**
         * Persistence of the note (saving and loading).
         */
        PERSISTENCE
    }
    
    public Phase phase();
}
