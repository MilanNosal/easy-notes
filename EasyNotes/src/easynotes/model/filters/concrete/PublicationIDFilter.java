package easynotes.model.filters.concrete;

import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
public class PublicationIDFilter extends SimpleAttributeFilter {

    public PublicationIDFilter() {
        super("publication ID");
    }

    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getPublicationID();
    }
}