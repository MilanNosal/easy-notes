package easynotes.concerns;

/**
 * Code implementing presentation of notes in the UI. View component of the MVC pattern.
 * @author Milan
 */
public @interface NotesPresentation {
    public enum Task {
        /**
         * Presentation of all notes in a list. This would be master view in
         * master-detail UI.
         */
        LIST_PRESENTATION,
        /**
         * Presentation of a single note. Detail of a single note.
         */
        NOTE_PRESENTATION,
        /**
         * Editable detail presentation of a single note.
         */
        NOTE_EDITING,
        /**
         * UI parts dealing with filtering and searching.
         */
        FILTERING,
        /**
         * Components implementing management of links between notes and resources.
         * This deserved a specific parameter, since links have a dynamic UI.
         * For each link there is a separate UI component.
         */
        LINKS
    }
    
    public Task[] task();
}
