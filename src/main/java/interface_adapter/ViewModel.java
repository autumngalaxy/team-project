package interface_adapter;

public class ViewModel<T> {
    private final String viewName;

    private T state;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public T getState() {
        return this.state;
    }

    public void setState(T state) {
        this.state = state;
    }

}
