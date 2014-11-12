package easynotes.persistence;

import easynotes.concerns.NotesLifecycle;
import easynotes.model.abstractModel.Notes;

/**
 *
 * @author Milan
 */
@NotesLifecycle(phase = NotesLifecycle.Phase.PERSISTENCE)
public interface ExportProvider {
    public void exportTo(Notes notes);
}
