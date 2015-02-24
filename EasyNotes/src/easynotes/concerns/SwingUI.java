package easynotes.concerns;

/**
 * Swing UI of the application.
 * @author Milan
 */
public @interface SwingUI {
    public enum Role {
        /**
         * System hooks in UI.
         */
        SYSTEM_HOOK,
        /**
         * UI operations, handlers.
         */
        OPERATION,
        /**
         * Start up code.
         */
        START_UP
    }
    
    public Role value();
}
