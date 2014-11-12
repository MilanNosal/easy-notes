package easynotes.model.filters.concrete;

import easynotes.concerns.Citing;
import easynotes.concerns.Filtering;
import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
@Citing
public class PublicationIDFilter extends SimpleAttributeFilter {

    public PublicationIDFilter() {
        super("publication ID");
    }

    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getPublicationID();
    }
}
