package easynotes.concerns;

/**
 * Components implementing panels of variable content size, allows
 * adding multiple subpanels dynamically.
 * @author Milan
 */
public @interface VariableSubpanels {
    public enum Role {
        /**
         * Factory for creating new content.
         */
        FACTORY,
        /**
         * Manager of the content.
         */
        MANAGER,
        /**
         * Subpanels that create content.
         */
        SUBPANEL
    }
    
    public Role value();
}
