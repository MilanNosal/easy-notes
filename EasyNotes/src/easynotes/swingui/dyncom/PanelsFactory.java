package easynotes.swingui.dyncom;

public interface PanelsFactory<T extends IsJComponent> {
    public T getNewInstance();
}
