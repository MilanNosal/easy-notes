package easynotes.swingui.dyncom;

public interface PanelsFactory<T extends Component> {
    public T getNewInstance();
}
