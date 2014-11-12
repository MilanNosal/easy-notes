package easynotes.swingui;

import easynotes.concerns.NoteEditing;
import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
@NoteEditing
public interface EditingNotesCallbacks {
    public void newNoteCreated(Note newNote);
    
    public void noteEdited(Note note);
}
