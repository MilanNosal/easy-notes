package easynotes.model;

import easynotes.concerns.Configuration;
import easynotes.concerns.Filtering;
import easynotes.concerns.NoteEventHandling;
import easynotes.concerns.NotesController;
import easynotes.concerns.NotesDataModel;
import easynotes.concerns.Sorting;
import easynotes.concerns.UI;
import easynotes.model.abstractModel.Note;
import easynotes.model.abstractModel.Notes;
import easynotes.model.filters.FiltersManager;
import java.util.*;

@UI
@NotesDataModel
@NotesController
public abstract class NotesViewModel implements Observer {

    @NotesDataModel
    private final Notes notes;

    @Filtering(role = Filtering.Role.FILTERING)
    private final List<Note> notesToShow = new LinkedList<>();

    @Configuration
    private final String[] columnNames = new String[]{"Title", "Tags"}; //, "Text"};

    @Filtering(role = Filtering.Role.FILTER_MANAGEMENT)
    private final FiltersManager filterManager;

    @Filtering(role = Filtering.Role.FILTERING)
    public NotesViewModel(Notes notes, FiltersManager filtersManager) {
        this.filterManager = filtersManager;
        this.notes = notes;
        setObservables();
    }

    @Filtering(role = Filtering.Role.FILTER_MANAGEMENT)
    public FiltersManager getFiltersManager() {
        return this.filterManager;
    }

    public Note getNoteAt(int index) {
        return this.notesToShow.get(index);
    }

    public int getIndexOf(Note note) {
        return this.notesToShow.indexOf(note);
    }

    @NotesController
    @Filtering(role = Filtering.Role.FILTERING)
    @Sorting
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
            default:
                return null;
        }
    }

    @Filtering(role = Filtering.Role.FILTERING)
    @NoteEventHandling(type = NoteEventHandling.Type.HANDLING)
    @Override
    public void update(Observable o, Object arg) {
        if ((o instanceof Notes || o instanceof Note) && arg instanceof Note.ChangeEvent) {
            Note.ChangeEvent evt = (Note.ChangeEvent) arg;
            switch (evt.getChangeType()) {
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
                    int index = this.notesToShow.indexOf(((Note.ChangeEvent) arg).getObjectOfChange());
                    if (index != -1) {
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

    @NoteEventHandling(type = NoteEventHandling.Type.HANDLING)
    private void setObservables() {
        this.notes.addObserver(this);
        for (Note note : this.notes.getNotes()) {
            note.addObserver(this);
        }
    }

    public abstract void fireTableRowsUpdated(int firstRow, int lastRow);

    public abstract void fireTableDataChanged();
}
