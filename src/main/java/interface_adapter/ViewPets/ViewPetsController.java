package interface_adapter.ViewPets;

import use_case.view_pets.ViewPetsInputBoundary;
import use_case.view_pets.ViewPetsInputData;

public class ViewPetsController {
    final ViewPetsInputBoundary interactor;

    public ViewPetsController(ViewPetsInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void onViewPets() {
        interactor.execute(new ViewPetsInputData());
    }
}
