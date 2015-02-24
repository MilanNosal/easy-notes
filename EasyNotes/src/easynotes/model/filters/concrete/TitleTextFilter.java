/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easynotes.model.filters.concrete;

import easynotes.model.abstractModel.Note;
import easynotes.model.filters.AbstractSimpleNotesFilter;

/**
 *
 * @author Milan
 */
public class TitleTextFilter extends AbstractSimpleNotesFilter{

    public TitleTextFilter() {
        super("titleText");
    }
    
    @Override
    public boolean filterNote(Note note) {
        String criterion = getCriterion();
        if(criterion == null || criterion.equals("")) {
            return true;
        }
        return (note.getTitle().toLowerCase().contains(criterion) ||
                note.getText().toLowerCase().contains(criterion)); 
    }
    
}
