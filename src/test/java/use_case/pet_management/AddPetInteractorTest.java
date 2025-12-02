package use_case.pet_management;

import entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for AddPetInteractor.
 */
public class AddPetInteractorTest {

    private AddPetInteractor interactor;
    private FakePetDAO petDAO;
    private FakeAddPetPresenter presenter;

    @BeforeEach
    void setUp() {
        petDAO = new FakePetDAO();
        presenter = new FakeAddPetPresenter();
        interactor = new AddPetInteractor(petDAO, presenter);
    }

    // === 1. pet already exists ===
    @Test
    void testPetAlreadyExists() {
        petDAO.existsByIdReturn = true;  // force DAO to say the ID exists

        AddPetInputData input = new AddPetInputData(
                1,
                "Milo",
                "BABY",
                "MALE",
                "Cat",
                "Tabby",
                "Orange",
                "SMALL",
                "Cute cat",
                "http://example.com/milo.jpg"
        );

        interactor.execute(input);

        assertNotNull(presenter.lastAddOutput);
        assertFalse(presenter.lastAddOutput.isSuccess());
        assertEquals("Pet ID already exists.", presenter.lastAddOutput.getMessage());
        assertNull(petDAO.savedPet);  // should NOT have been saved
    }

    // === 2. invalid enum value (e.g. wrong age) ===
    @Test
    void testInvalidEnumValue() {
        petDAO.existsByIdReturn = false;

        AddPetInputData input = new AddPetInputData(
                2,
                "Luna",
                "WRONG_AGE",         // invalid -> triggers IllegalArgumentException
                "FEMALE",
                "Cat",
                "Tabby",
                "Gray",
                "SMALL",
                "Nice cat",
                "http://example.com/luna.jpg"
        );

        interactor.execute(input);

        assertNotNull(presenter.lastAddOutput);
        assertFalse(presenter.lastAddOutput.isSuccess());
        assertTrue(
                presenter.lastAddOutput.getMessage().startsWith("Invalid pet data:"),
                "Error message should start with 'Invalid pet data:'"
        );
        assertNull(petDAO.savedPet);  // no save on invalid data
    }

    // === 3. successful add ===
    @Test
    void testSuccessAdd() {
        petDAO.existsByIdReturn = false;

        AddPetInputData input = new AddPetInputData(
                3,
                "Bella",
                "BABY",
                "FEMALE",
                "Dog",
                "Poodle",
                "White",
                "SMALL",
                "Playful dog",
                "http://example.com/bella.jpg"
        );

        interactor.execute(input);

        // presenter received success
        assertNotNull(presenter.lastAddOutput);
        assertTrue(presenter.lastAddOutput.isSuccess());
        assertEquals("Pet added successfully.", presenter.lastAddOutput.getMessage());

        // DAO actually saved a Pet with correct fields
        assertNotNull(petDAO.savedPet);
        assertEquals(3, petDAO.savedPet.getId());
        assertEquals("Bella", petDAO.savedPet.getName());
        assertEquals(Pet.Age.BABY, petDAO.savedPet.getAge());
        assertEquals(Pet.Gender.FEMALE, petDAO.savedPet.getGender());
    }
}
