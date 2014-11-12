package easynotes.concerns;

public @interface NotesPresentation {
    public enum Task {
        LIST_PRESENTATION,
        NOTE_PRESENTATION,
        NOTE_EDITING,
        FILTERING,
        LINKS
    }
    
    public Task[] task();
}
