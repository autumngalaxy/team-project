package interface_adapter;


/**
 * The ViewModel for our CA implementation.
 * This class delegates work to a PropertyChangeSupport object for
 * managing the property change events.
 *
 * @param <T> The type of state object contained in the model.
 */

public class ViewModel<T> {
    private final String viewName;

    private T state;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public void setState(T state) {
        this.state = state;
    }

    public String getViewName() {
        return viewName;
    }

    public T getState() {
        return this.state;
    }



}
