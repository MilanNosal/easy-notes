package easynotes.model.abstractModel;

import easynotes.concerns.NotesModel;
import easynotes.concerns.NoteEventHandling;
import easynotes.concerns.NotesLifecycle;
import java.util.*;

/**
 * @author Milan
 */
@NotesModel
public final class Notes extends Observable {

    private List<Note> notes;
    
    public Notes(List<Note> notes) {
        this.setNotes(notes);
    }
    
    @NotesLifecycle(phase = NotesLifecycle.Phase.CREATION)
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void addNote(Note note) {
        this.notes.add(note);
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NEW_NOTE, note));
    }
    
    @NotesLifecycle(phase = NotesLifecycle.Phase.REMOVAL)
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void removeNote(Note note) {
        note.deleteObservers();
        this.notes.remove(note);
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NOTE_DELETED, note));
    }
    
    public boolean isEmpty() {
        return this.notes.isEmpty();
    }
    
    public List<Note> getNotes() {
        return new ArrayList<>(this.notes);
    }
    
    @NotesLifecycle(phase = NotesLifecycle.Phase.CREATION)
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setNotes(List<Note> notes) {
        if (this.notes != null && !this.notes.isEmpty()) {
            clearNotes();
        }
        this.notes = notes;
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NOTES_LOADED, null));
    }
    
    @NotesLifecycle(phase = NotesLifecycle.Phase.REMOVAL)
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void clearNotes() {
        for(Note note : notes) {
            note.deleteObservers();
        }
        this.notes.clear();
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NOTES_CLEARED, null));
    }
    
    @NotesLifecycle(phase = NotesLifecycle.Phase.REMOVAL)
    public void close() {
        this.clearNotes();
        this.deleteObservers();
    }
}
