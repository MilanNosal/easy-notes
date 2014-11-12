package easynotes.model.filters.concrete;

import easynotes.concerns.Filtering;
import easynotes.concerns.Links;
import easynotes.model.abstractModel.Note;
import easynotes.model.filters.AbstractSimpleNotesFilter;

/**
 *
 * @author Milan
 */
@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
@Links
public class LinksFilter extends AbstractSimpleNotesFilter {

    public LinksFilter() {
        super("links");
    }

    @Override
    public boolean filterNote(Note note) {
        String criterion = getCriterion();
        if(criterion == null || criterion.equals("")) {
            return true;
        }
        for (String link : note.getLinks()) {
            if (link.toLowerCase().contains(criterion)) {
                return true;
            }
        }
        return false;
    }
}
