package easynotes.persistence;

import easynotes.concerns.Persistence;
import easynotes.concerns.Unused;
import easynotes.model.abstractModel.Notes;

@Unused
@Persistence
public interface ExportProvider {
    public void exportTo(Notes notes);
}
