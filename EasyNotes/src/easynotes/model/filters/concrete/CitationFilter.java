package easynotes.model.filters.concrete;

import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
public class CitationFilter extends SimpleAttributeFilter {

    public CitationFilter() {
        super("citations");
    }

    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getCitation();
    }
}
