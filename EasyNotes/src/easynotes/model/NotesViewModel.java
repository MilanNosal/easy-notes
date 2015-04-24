package easynotes.model;

import easynotes.model.abstractModel.Note;
import easynotes.model.abstractModel.Notes;
import easynotes.model.filters.FiltersManager;
import java.util.*;

public abstract class NotesViewModel implements Observer {

    private final Notes notes;
    private final List<Note> notesToShow = new LinkedList<>();
    private final String[] columnNames = new String[]{"Title", "Tags"};
    private final FiltersManager filterManager;

    public NotesViewModel(Notes notes, FiltersManager filtersManager) {
        this.filterManager = filtersManager;
        this.notes = notes;
        setObservables();
    }
    
    public FiltersManager getFiltersManager() {
        return this.filterManager;
    }

    public Note getNoteAt(int index) {
        return this.notesToShow.get(index);
    }
    
    public int getIndexOf(Note note) {
        return this.notesToShow.indexOf(note);
    }

    public void updateView() {
        this.notesToShow.clear();
        for (Note note : notes.getNotes()) {
            if (this.filterManager.notePasses(note)) {
                notesToShow.add(note);
            }
        }
        Collections.sort(notesToShow);
        fireTableDataChanged();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public int getRowCount() {
        return notesToShow.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return notesToShow.get(rowIndex).getTitle();
            case 1:
                StringBuilder sb = new StringBuilder();
                for (String tag : notesToShow.get(rowIndex).getTags()) {
                    sb.append(tag);
                    sb.append(Note.DELIM);
                }
                return sb.toString();
//            case 2:
//                return notesToShow.get(rowIndex).getText();
            default:
                return null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if((o instanceof Notes || o instanceof Note) && arg instanceof Note.ChangeEvent) {
            Note.ChangeEvent evt = (Note.ChangeEvent) arg;
            switch(evt.getChangeType()) {
                case NOTES_LOADED:
                    setObservables();
                case NOTES_CLEARED:
                    this.filterManager.deactivateFilters();
                    updateView();
                    break;
                case NEW_NOTE:
                    evt.getObjectOfChange().addObserver(this);
                    updateView();
                    break;
                case NOTE_DELETED:
                    evt.getObjectOfChange().deleteObserver(this);
                    updateView();
                    break;
                case TAG_ADDED:
                case TAG_REMOVED:
                case TITLE_CHANGED:
                    int index = this.notesToShow.indexOf(((Note.ChangeEvent)arg).getObjectOfChange());
                    if(index != -1) {
                        fireTableRowsUpdated(index, index);
                    }
                    break;
                case CITATION_CHANGED:
                case TEXT_CHANGED:
                case LINK_CHANGED:
                case PUBLICATIONID_CHANGED:
                    break;
                default:
                    throw new RuntimeException("Did you forget about some event that might occur?");
            }
        }
    }
    
    private void setObservables() {
        this.notes.addObserver(this);
        for(Note note : this.notes.getNotes()) {
            note.addObserver(this);
        }
    }
    
    public abstract void fireTableRowsUpdated(int firstRow, int lastRow);
    
    public abstract void fireTableDataChanged();
}
