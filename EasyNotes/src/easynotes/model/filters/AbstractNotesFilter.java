package easynotes.model.filters;

import easynotes.concerns.Filtering;
import easynotes.concerns.TODO;
import easynotes.model.abstractModel.Note;
import java.util.Objects;

@Filtering(role = Filtering.Role.FILTER_IMPLEMENTATION)
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
        this.setCriterion("");
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public String getID() {
        return this.id;
    }
    
    @TODO("Important method. Maybe rename to setSearchString().")
    @Filtering(role = Filtering.Role.FILTERING)
    public abstract void setCriterion(String criterion);

    @TODO("Rename to 'accept'.")
    @Filtering(role = Filtering.Role.FILTERING)
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
