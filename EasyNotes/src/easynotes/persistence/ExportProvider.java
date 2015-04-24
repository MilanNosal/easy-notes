package easynotes.persistence;

import easynotes.model.abstractModel.Notes;

public interface ExportProvider {
    public void exportTo(Notes notes);
}
