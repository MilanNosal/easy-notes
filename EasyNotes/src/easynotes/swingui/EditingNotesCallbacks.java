package easynotes.swingui;

import easynotes.concerns.NoteAdding;
import easynotes.concerns.NoteEditing;
import easynotes.concerns.NoteEventHandling;
import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
@NoteEventHandling(type = NoteEventHandling.Type.SOURCE)
public interface EditingNotesCallbacks {

    @NoteAdding
    public void newNoteCreated(Note newNote);

    @NoteEditing
    public void noteEdited(Note note);
}
