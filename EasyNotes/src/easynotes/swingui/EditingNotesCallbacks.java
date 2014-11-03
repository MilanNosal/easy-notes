package easynotes.swingui;

import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
public interface EditingNotesCallbacks {
    public void newNoteCreated(Note newNote);
    
    public void noteEdited(Note note);
}
