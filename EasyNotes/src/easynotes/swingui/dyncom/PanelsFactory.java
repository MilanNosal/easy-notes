package easynotes.swingui.dyncom;

import easynotes.concerns.VariableSubpanels;

@VariableSubpanels(VariableSubpanels.Role.FACTORY)
public interface PanelsFactory<T extends IsJComponent> {

    public T getNewInstance();
}
