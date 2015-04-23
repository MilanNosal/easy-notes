package easynotes.model.filters.concrete;

import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;

@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
public class TitleFilter extends SimpleAttributeFilter {

    public TitleFilter() {
        super("title");
    }

    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getTitle();
    }
}
