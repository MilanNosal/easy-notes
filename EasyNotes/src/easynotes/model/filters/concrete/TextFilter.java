package easynotes.model.filters.concrete;

import easynotes.model.abstractModel.Note;

/**
 *
 * @author Milan
 */
public class TextFilter extends SimpleAttributeFilter {

    public TextFilter() {
        super("text");
    }
    
    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getText();
    }
}
