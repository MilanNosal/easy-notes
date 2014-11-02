package easynotes.model.filters;

import easynotes.model.abstractModel.Note;

public abstract class AbstractSimpleNotesFilter extends AbstractNotesFilter {
    private String criterion = "";

    public AbstractSimpleNotesFilter(String id) {
        super(id);
    }
    
    @Override
    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }
    
    protected String getCriterion() {
        return this.criterion.toLowerCase();
    }

    @Override
    public abstract boolean filterNote(Note note);
}
