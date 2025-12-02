package use_case.logout;

import org.junit.jupiter.api.Test;
import use_case.user_logout.UserLogoutInteractor;
import use_case.user_logout.UserLogoutOutputBoundary;
import use_case.user_logout.UserLogoutOutputData;
import use_case.user_logout.UserLogoutUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for UserLogoutInteractor – 100% coverage.
 */
public class LogoutInteractorTest {

    /**
     * Fake DAO for testing.
     */
    private static class FakeUserLogoutDAO implements UserLogoutUserDataAccessInterface {
        private String currentUsername = "testUser";

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }

        @Override
        public void setCurrentUsername(String name) {
            this.currentUsername = name;
        }
    }

    /**
     * Fake Presenter for testing.
     */
    private static class FakeLogoutPresenter implements UserLogoutOutputBoundary {
        private UserLogoutOutputData receivedData;
        private boolean successCalled = false;

        @Override
        public void prepareSuccessView(UserLogoutOutputData data) {
            this.receivedData = data;
            this.successCalled = true;
        }
    }

    @Test
    public void testExecute_LogoutSuccess() {
        // Arrange
        FakeUserLogoutDAO dao = new FakeUserLogoutDAO();
        FakeLogoutPresenter presenter = new FakeLogoutPresenter();

        UserLogoutInteractor interactor =
                new UserLogoutInteractor(dao, presenter);

        // Act
        interactor.execute();

        // Assert
        // 1. Presenter was called
        assertTrue(presenter.successCalled);

        // 2. Presenter received correct username
        assertEquals("testUser", presenter.receivedData.getUsername());

        // 3. Username in DAO should now be null
        assertNull(dao.getCurrentUsername());
    }

    @Test
    public void testConstructor_CreatesInstance() {
        FakeUserLogoutDAO dao = new FakeUserLogoutDAO();
        FakeLogoutPresenter presenter = new FakeLogoutPresenter();

        UserLogoutInteractor interactor = new UserLogoutInteractor(dao, presenter);

        assertNotNull(interactor); // covers constructor path
    }
}
