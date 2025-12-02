package interface_adapter.fill_application;

import interface_adapter.ViewModel;

public class FillApplicationViewModel extends ViewModel<FillApplicationState> {
    public static final String TITLE_LABEL = "Fill Application View";


    public static final String SUBMIT_BUTTON_LABEL = "Submit Application";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";


    public FillApplicationViewModel(){
        super("fill application");
        setState(new FillApplicationState());
    }
}
