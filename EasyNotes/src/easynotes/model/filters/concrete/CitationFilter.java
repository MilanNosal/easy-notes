package easynotes.model.filters.concrete;

import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
public class CitationFilter extends SimpleAttributeFilter {

    public CitationFilter() {
        super("citations");
    }

    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getCitation();
    }
}
