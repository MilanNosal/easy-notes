package easynotes.model.filters.concrete;

import easynotes.model.abstractModel.Note;
import easynotes.model.filters.AbstractSimpleNotesFilter;

public class TagsFilter extends AbstractSimpleNotesFilter {

    public TagsFilter() {
        super("tags");
    }

    @Override
    public boolean filterNote(Note note) {
        String criterion = getCriterion();

        if(criterion == null || criterion.equals("")) {
            return true;
        }
        
        if (criterion.startsWith("!")) {
            String sub = criterion.substring(1);
            for (String tag : note.getTags()) {
                if (tag.toLowerCase().contains(sub)) {
                    return false;
                }
            }
            return true;
        } else {
            for (String tag : note.getTags()) {
                if (tag.toLowerCase().contains(criterion)) {
                    return true;
                }
            }
            return false;
        }
    }
}
