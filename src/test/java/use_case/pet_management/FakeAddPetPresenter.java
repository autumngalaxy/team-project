package use_case.pet_management;

/**
 * Fake presenter for Pet management use cases.
 * Stores whatever data the interactor passes in so tests can assert on it.
 */
public class FakeAddPetPresenter implements PetManagementOutputBoundary {

    public AddPetOutputData lastAddOutput;
    public UpdatePetOutputData lastUpdateOutput;
    public DeletePetOutputData lastDeleteOutput;

    @Override
    public void presentAdd(AddPetOutputData data) {
        this.lastAddOutput = data;
    }

    @Override
    public void presentUpdate(UpdatePetOutputData data) {
        this.lastUpdateOutput = data;
    }

    @Override
    public void presentDelete(DeletePetOutputData data) {
        this.lastDeleteOutput = data;
    }
}
