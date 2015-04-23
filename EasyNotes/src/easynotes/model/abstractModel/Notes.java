package easynotes.model.abstractModel;

import easynotes.concerns.DomainEntity;
import easynotes.concerns.NoteAdding;
import easynotes.concerns.NoteDeleting;
import easynotes.concerns.NotesDataModel;
import easynotes.concerns.NoteEventHandling;
import easynotes.concerns.NotesLoading;
import java.util.*;

@NotesDataModel
@DomainEntity
public final class Notes extends Observable {

    private List<Note> notes;
    
    public Notes(List<Note> notes) {
        this.setNotes(notes);
    }
    
    @NoteAdding
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void addNote(Note note) {
        this.notes.add(note);
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NEW_NOTE, note));
    }
    
    @NoteDeleting
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
    
    @NotesLoading
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void setNotes(List<Note> notes) {
        if (this.notes != null && !this.notes.isEmpty()) {
            clearNotes();
        }
        this.notes = notes;
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NOTES_LOADED, null));
    }
    
    @NoteEventHandling(type = NoteEventHandling.Type.PROPAGATION)
    public void clearNotes() {
        for(Note note : notes) {
            note.deleteObservers();
        }
        this.notes.clear();
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NOTES_CLEARED, null));
    }
    
    public void close() {
        this.clearNotes();
        this.deleteObservers();
    }
}
