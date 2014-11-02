package easynotes.persistence;

import easynotes.model.abstractModel.Notes;

/**
 *
 * @author Milan
 */
public interface ImportProvider {
    public Notes importFrom();
}
