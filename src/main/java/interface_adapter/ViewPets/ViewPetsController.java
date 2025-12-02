package interface_adapter.ViewPets;

import entity.Pet;
import interface_adapter.FilterPets.PetListViewModel;
import interface_adapter.fill_application.FillApplicationViewModel;
import use_case.view_pets.ViewPetsInputBoundary;
import use_case.view_pets.ViewPetsInputData;
import view.FillApplicationView;

/* TESTING */
import javax.swing.*;

public class ViewPetsController {
    final ViewPetsInputBoundary interactor;
    private final PetListViewModel viewModel;
    private final FillApplicationView fillAppView;

    public ViewPetsController(ViewPetsInputBoundary interactor,
            PetListViewModel viewModel, FillApplicationView fillAppView) {
        this.interactor = interactor;
        this.viewModel = viewModel;
        this.fillAppView = fillAppView;
    }
    
    public void onViewPets() {
        interactor.execute(new ViewPetsInputData());
    }
    
    public PetListViewModel getViewModel() {
        return viewModel;
    }

    public void newApplication(Pet pet) {
        /* start a new application with the current pet. */
        //new FillApplicationView(fillAppViewModel, pet);
        fillAppView.makeVisible(pet);
    }
}
