package easynotes.concerns;

public @interface SwingUI {
    public enum Role {
        SYSTEM_HOOK,
        OPERATION,
        START_UP
    }
    
    public Role value();
}
