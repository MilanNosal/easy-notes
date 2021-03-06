package easynotes.swingui;

import easynotes.concerns.NotesController;
import easynotes.concerns.NotesPresentation;
import easynotes.concerns.UI;
import easynotes.model.NotesViewModel;
import easynotes.model.abstractModel.Notes;
import easynotes.model.filters.FiltersManager;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

@UI
@NotesController
@NotesPresentation(task = NotesPresentation.Task.LIST_PRESENTATION)
public class NotesTableModel extends NotesViewModel implements TableModel {

    public NotesTableModel(Notes notes, FiltersManager filtersManager) {
        super(notes, filtersManager);
    }
    
    protected EventListenerList listenerList = new EventListenerList();

    @Override
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
        fireTableChanged(new TableModelEvent(this, firstRow, lastRow,
                             TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }

    @Override
    public void fireTableDataChanged() {
        fireTableChanged(new TableModelEvent(this));
    }
    
    public void fireTableChanged(TableModelEvent e) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TableModelListener.class) {
                ((TableModelListener)listeners[i+1]).tableChanged(e);
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenerList.remove(TableModelListener.class, l);
    }
    
}
