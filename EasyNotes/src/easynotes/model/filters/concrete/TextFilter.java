package easynotes.model.filters.concrete;

import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;

@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
public class TextFilter extends SimpleAttributeFilter {

    public TextFilter() {
        super("text");
    }
    
    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getText();
    }
}
