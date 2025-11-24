package interface_adapter.fill_application;

import interface_adapter.ViewModel;

public class FillApplicationViewModel extends ViewModel<FillApplicationState> {
    public static final String TITLE_LABEL = "Fill Application View";
    public static final String NAME_LABEL = "Enter your name";
    public static final String ID_LABEL = "Enter your ID Type";
    public static final String STREET_LABEL = "Street Address";
    public static final String CITY_LABEL = "City";
    public static final String PROVINCE_LABEL = "Province / State";
    public static final String POSTAL_LABEL = "Postal Code";
    public static final String COUNTRY_LABEL = "Country";
    public static final String PHONE_LABEL = "Enter phone number";
    public static final String EMAIL_LABEL = "Enter email";

    public static final String SUBMIT_BUTTON_LABEL = "Submit Application";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public FillApplicationViewModel(){
        super("fill application");
        setState(new FillApplicationState());
    }
}
