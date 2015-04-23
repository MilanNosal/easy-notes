package easynotes.model.filters.concrete;

import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;
import easynotes.model.filters.AbstractSimpleNotesFilter;

@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
public abstract class SimpleAttributeFilter extends AbstractSimpleNotesFilter {

    public SimpleAttributeFilter(String id) {
        super(id);
    }

    protected abstract String getAttributeOfInterest(Note note);
    
    @Filtering(role = Filtering.Role.FILTERING)
    @Override
    public boolean filterNote(Note note) {
        String criterion = getCriterion();
        if(criterion == null || criterion.equals("")) {
            return true;
        }
        return (getAttributeOfInterest(note).toLowerCase().contains(criterion));
    }
}
