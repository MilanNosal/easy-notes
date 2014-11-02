package easynotes.model.filters.concrete;

import easynotes.model.abstractModel.Note;
import easynotes.model.filters.AbstractSimpleNotesFilter;

/**
 *
 * @author Milan
 */
public abstract class SimpleAttributeFilter extends AbstractSimpleNotesFilter{

    public SimpleAttributeFilter(String id) {
        super(id);
    }

    protected abstract String getAttributeOfInterest(Note note);
    
    @Override
    public boolean filterNote(Note note) {
        String criterion = getCriterion();
        if(criterion == null || criterion.equals("")) {
            return true;
        }
        if(getAttributeOfInterest(note).toLowerCase().contains(criterion)) {
            return true;
        }
        return false;
    }
}
