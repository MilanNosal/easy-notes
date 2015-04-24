package easynotes.model.filters.concrete;

import easynotes.model.abstractModel.Note;

public class TitleFilter extends SimpleAttributeFilter {

    public TitleFilter() {
        super("title");
    }

    @Override
    protected String getAttributeOfInterest(Note note) {
        return note.getTitle();
    }
}
