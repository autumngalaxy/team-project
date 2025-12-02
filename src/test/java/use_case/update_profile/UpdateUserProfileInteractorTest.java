package use_case.update_profile;

import dataAccess.FileUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import service.Backend;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserProfileInteractorTest {

    private FileUserDataAccessObject mockDao;
    private Backend mockBackend;
    private UpdateUserProfileOutputBoundary mockPresenter;

    private UpdateUserProfileInteractor interactor;
    private User sampleUser;

    @BeforeEach
    void setup() {
        mockDao = mock(FileUserDataAccessObject.class);
        mockBackend = mock(Backend.class);
        mockPresenter = mock(UpdateUserProfileOutputBoundary.class);

        interactor = new UpdateUserProfileInteractor(mockDao, mockBackend, mockPresenter);

        sampleUser = new User(
                1,
                "Old Name",
                "Old Address",
                User.idType.PASSPORT,
                123,
                "old@mail.com",
                "user1",
                "pass",
                "admin"
        );

        when(mockDao.getCurrentUser()).thenReturn(sampleUser);
    }

    @Test
    void testOpenEditPage() {
        interactor.openEditPage();
        verify(mockPresenter, times(1)).showEditProfile();
    }

    @Test
    void testSaveProfile() {
        UpdateUserProfileInputData data = new UpdateUserProfileInputData(
                "NewName",
                "NewAddress",
                "new@mail.com",
                999
        );

        interactor.saveProfile(data);

        // verify presenter called success
        verify(mockPresenter, times(1)).showProfileUpdateSuccess();

        // verify userDAO.save was called
        verify(mockDao, times(1)).save(any(User.class));

        // verify backend updated
        verify(mockBackend, times(1)).setCurrentUser(any(User.class));
    }

    @Test
    void testSave() {
        interactor.save("NameX", "mailX@mail.com", "AddrX", 88);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        verify(mockDao).updateUser(userCaptor.capture());
        verify(mockBackend).setCurrentUser(userCaptor.capture());

        User saved = userCaptor.getAllValues().get(0);

        assertEquals("NameX", saved.getName());
        assertEquals("mailX@mail.com", saved.getEmail());
        assertEquals("AddrX", saved.getAddress());
        assertEquals(88, saved.getPhoneNumber());

        verify(mockPresenter).showProfileUpdateSuccess();
    }
}
