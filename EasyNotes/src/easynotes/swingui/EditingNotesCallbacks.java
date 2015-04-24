package easynotes.swingui;

import easynotes.model.abstractModel.Note;

public interface EditingNotesCallbacks {
    public void newNoteCreated(Note newNote);
    
    public void noteEdited(Note note);
}
