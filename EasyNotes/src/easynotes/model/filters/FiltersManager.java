package easynotes.model.filters;

import easynotes.model.abstractModel.Note;
import easynotes.model.filters.concrete.CitationFilter;
import easynotes.model.filters.concrete.LinksFilter;
import easynotes.model.filters.concrete.PublicationIDFilter;
import easynotes.model.filters.concrete.TagsFilter;
import easynotes.model.filters.concrete.TextFilter;
import easynotes.model.filters.concrete.TitleFilter;
import easynotes.model.filters.concrete.UsedFilter;
import java.util.ArrayList;
import java.util.List;

public final class FiltersManager {
    
    private final List<AbstractNotesFilter> availableFilters = new ArrayList<>();

    public FiltersManager() {
        addNotesFilter(new TagsFilter());
        addNotesFilter(new TitleFilter());
        addNotesFilter(new TextFilter());
        addNotesFilter(new CitationFilter());
        addNotesFilter(new LinksFilter());
        addNotesFilter(new PublicationIDFilter());
        addNotesFilter(new UsedFilter());
    }
    
    public String[] getFilterIdentifiers() {
        String[] retVal = new String[availableFilters.size()];
        int i = 0;
        for(AbstractNotesFilter filter : availableFilters) {
            retVal[i++] = filter.getID();
        }
        return retVal;
    }
    
    public void addNotesFilter(AbstractNotesFilter filter) {
        if(!this.availableFilters.contains(filter)) {
            this.availableFilters.add(filter);
        }
    }

    public void removeNotesFilter(AbstractNotesFilter filter) {
        this.availableFilters.remove(filter);
    }

    public AbstractNotesFilter getFilter(String filterIdentifier) {
        for(AbstractNotesFilter filter : availableFilters) {
            if(filter.getID().equalsIgnoreCase(filterIdentifier)) {
                return filter;
            }
        }
        return null;
    }

    public void activateFilter(String filterIdentifier) {
        AbstractNotesFilter filter = getFilter(filterIdentifier);
        if(filter != null) {
            filter.activate();
        }
    }
    
    public void deactivateFilter(String filterIdentifier) {
        AbstractNotesFilter filter = getFilter(filterIdentifier);
        if(filter != null) {
            filter.deactivate();
        }
    }
    
    public void deactivateFilters() {
        for(AbstractNotesFilter notesFilter : this.availableFilters) {
            notesFilter.deactivate();
        }
    }
    
    public boolean notePasses(Note note) {
        for(AbstractNotesFilter notesFilter : this.availableFilters) {
            if(notesFilter.isActive() && !notesFilter.filterNote(note)) {
                return false;
            }
        }
        return true;
    }
}
