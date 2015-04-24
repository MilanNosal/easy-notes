package easynotes.persistence.filenotes;

import easynotes.model.abstractModel.Note;
import static easynotes.model.abstractModel.Note.ChangeEventType.*;
import easynotes.model.abstractModel.Notes;
import java.util.Observable;
import java.util.Observer;

public class FilesChangeObserver implements Observer {
    
    private boolean modified = false;
    
    private final Notes notes;
    
    public FilesChangeObserver(Notes notes) {
        this.notes = notes;
        setObservables();
    }

    @Override
    public void update(Observable o, Object arg) {
        if( (o instanceof Notes || o instanceof Note) && arg instanceof Note.ChangeEvent) {
            Note.ChangeEvent event = ((Note.ChangeEvent)arg);
            switch(event.getChangeType()) {
                case NOTES_CLEARED:
                    this.modified = false;
                    break;
                case NOTES_LOADED:
                    setObservables();
                    this.modified = false;
                    break;
                case NEW_NOTE:
                    event.getObjectOfChange().addObserver(this);
                    this.modified = true;
                    break;
                case NOTE_DELETED:
                    event.getObjectOfChange().deleteObserver(this);
                    this.modified = true;
                    break;
                case TAG_ADDED:
                case TAG_REMOVED:
                case TITLE_CHANGED:
                case CITATION_CHANGED:
                case TEXT_CHANGED:
                case LINK_CHANGED:
                case PUBLICATIONID_CHANGED:
                    this.modified = true;
                    break;
                default:
                    throw new RuntimeException("Did you forget about some event that might occur?");
            }
        }
    }
    
    public void resetModified() {
        this.modified = false;
    }
    
    private void setObservables() {
        notes.addObserver(this);
        for(Note note : notes.getNotes()) {
            note.addObserver(this);
        }
    }
    
    public boolean areNotesModified() {
        return this.modified;
    }
    
    public void close() {
        for(Note note : notes.getNotes()) {
            note.deleteObserver(this);
        }
        notes.deleteObserver(this);
    }
}
