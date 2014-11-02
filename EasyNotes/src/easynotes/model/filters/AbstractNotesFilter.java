package easynotes.model.filters;

import easynotes.model.abstractModel.Note;
import java.util.Objects;

public abstract class AbstractNotesFilter {
    
    private final String id;
    private boolean active = false;

    public AbstractNotesFilter(String id) {
        this.id = id;
    }
    
    public void activate() {
        this.active = true;
    }
    
    public void deactivate() {
        this.active = false;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public String getID() {
        return this.id;
    }
    
    public abstract void setCriterion(String criterion);
    
    /**
     * Ma vratit false, ak poznamka neprejde filtrom, true ak sa poznamka
     * ma zobrazit.
     * @param note
     * @return 
     */
    public abstract boolean filterNote(Note note);
    
    @Override
    public boolean equals(Object object) {
        return (object != null) 
                && (object instanceof AbstractNotesFilter) 
                && ((AbstractNotesFilter)object).getID().equalsIgnoreCase(this.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}