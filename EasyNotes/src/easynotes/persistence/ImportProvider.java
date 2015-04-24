package easynotes.persistence;

import easynotes.model.abstractModel.Notes;

public interface ImportProvider {
    public Notes importFrom();
}
