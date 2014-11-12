package easynotes.model.filters.concrete;

import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;
import easynotes.model.filters.AbstractNotesFilter;

@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
public class UsedFilter extends AbstractNotesFilter {

    public UsedFilter() {
        super("not used");
    }

    @Override
    public void setCriterion(String criterion) {
    }

    @Override
    public boolean filterNote(Note note) {
        return !note.isUsed();
    }
    
}
