package easynotes.model.filters;

import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;

@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
public abstract class AbstractSimpleNotesFilter extends AbstractNotesFilter {
    private String criterion = "";

    public AbstractSimpleNotesFilter(String id) {
        super(id);
    }
    
    @Filtering(role = Filtering.Role.FILTERING)
    @Override
    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }
    
    @Filtering(role = Filtering.Role.FILTERING)
    protected String getCriterion() {
        return this.criterion.toLowerCase();
    }

    @Filtering(role = Filtering.Role.FILTERING)
    @Override
    public abstract boolean filterNote(Note note);
}
