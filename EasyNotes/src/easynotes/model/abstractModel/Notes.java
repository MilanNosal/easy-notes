package easynotes.model.abstractModel;

import annotations.NonNull;
import java.util.*;

/**
 * Trieda Notes manazuje objekty triedy Note, stara sa aj o propagaciu 
 * udalosti z manazovanych poznamok. To znamena, ze staci sledovat udalosti
 * z tohto Observable.
 * @author Milan
 */
public final class Notes extends Observable {

    private List<Note> notes;
    
    public Notes(@NonNull List<Note> notes) {
        this.setNotes(notes);
    }
    
    public void addNote(Note note) {
        this.notes.add(note);
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NEW_NOTE, note));
    }
    
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
    
    public void setNotes(@NonNull List<Note> notes) {
        this.notes = notes;
        this.setChanged();
        this.notifyObservers(new Note.ChangeEvent(Note.ChangeEventType.NOTES_LOADED, null));
    }
    
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
