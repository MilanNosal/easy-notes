package easynotes.swingui.dyncom;

import easynotes.concerns.UI;

@UI
public interface PanelsFactory<T extends IsJComponent> {

    public T getNewInstance();
}
