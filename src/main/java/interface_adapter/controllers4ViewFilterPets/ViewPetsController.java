package interface_adapter.controllers4ViewFilterPets;

import use_case.view_pets.ViewPetsInputBoundary;
import use_case.view_pets.ViewPetsInputData;

public class ViewPetsController {
    final ViewPetsInputBoundary interactor;

    public ViewPetsController(ViewPetsInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void onViewPets() {
        ViewPetsInputData inputData = new ViewPetsInputData();
    }
}
