package easynotes.concerns;

/**
 * Handling of different note change events.
 * @author Milan
 */
public @interface NoteEventHandling {
    public enum Type {
        /**
         * Code handling event propagation.
         */
        PROPAGATION,
        /**
         * Code representing events.
         */
        EVENT,
        /**
         * Code that is a source of note events.
         */
        SOURCE,
        /** 
         * Note event handlers.
         */
        HANDLING
    }
    
    public Type type();
}
