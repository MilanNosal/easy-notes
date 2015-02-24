package easynotes.concerns;

/**
 * Code implementing presentation of notes. View component of the MVC pattern.
 * @author Milan
 */
public @interface NotesPresentation {
    public enum Task {
        /**
         * Presentation of all notes in a list.
         */
        LIST_PRESENTATION,
        /**
         * Presentation of a single note.
         */
        NOTE_PRESENTATION,
        /**
         * Editable presentatation of a single note.
         */
        NOTE_EDITING,
        /**
         * UI dealing with filtering.
         */
        FILTERING,
        /**
         * Components implementing management of links between notes and resources.
         */
        LINKS
    }
    
    public Task[] task();
}
