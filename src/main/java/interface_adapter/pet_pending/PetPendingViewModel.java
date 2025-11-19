package interface_adapter.pet_pending;

import interface_adapter.ViewModel;

public class PetPendingViewModel extends ViewModel<PetPendingState> {

    public PetPendingViewModel() {
        super("logged in");
        setState(new PetPendingState());
    }

}
