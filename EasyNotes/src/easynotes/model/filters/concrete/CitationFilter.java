package easynotes.model.filters.concrete;

import easynotes.concerns.Citing;
import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;

@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
@Citing
public class CitationFilter extends SimpleAttributeFilter {

    public CitationFilter() {
        super("citations");
    }

    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getCitation();
    }
}
