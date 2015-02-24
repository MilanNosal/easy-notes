package easynotes.concerns;

/**
 * Handling of different note change events.
 * @author Milan
 */
public @interface NoteEventHandling {
    public enum Type {
        /**
         * Event propagation.
         */
        PROPAGATION,
        /**
         * Event concepts.
         */
        EVENT,
        /**
         * Source of note events.
         */
        SOURCE,
        /** 
         * Note event handlers.
         */
        HANDLING
    }
    
    public Type type();
}
