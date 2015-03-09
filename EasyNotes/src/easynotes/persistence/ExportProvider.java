package easynotes.persistence;

import easynotes.concerns.Persistence;
import easynotes.concerns.Unused;
import easynotes.model.abstractModel.Notes;

/**
 *
 * @author Milan
 */
@Unused
@Persistence
public interface ExportProvider {
    public void exportTo(Notes notes);
}
