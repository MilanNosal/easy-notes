package easynotes.persistence;

import easynotes.model.abstractModel.Notes;

/**
 *
 * @author Milan
 */
public interface ExportProvider {
    public void exportTo(Notes notes);
}
