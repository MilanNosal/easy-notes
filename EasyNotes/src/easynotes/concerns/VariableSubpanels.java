package easynotes.concerns;

public @interface VariableSubpanels {
    public enum Role {
        FACTORY,
        MANAGER,
        SUBPANEL
    }
    
    public Role value();
}
