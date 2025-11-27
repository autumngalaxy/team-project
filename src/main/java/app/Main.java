package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addLoginChooseView()
                .addUserLoginView()
                .addLoginChoosePresenter()
//                .addStaffLoginView()
//                .addAdminLoginView()
                .addCreateUserAccountView()
                .addHomepageView()
                .addUserLoginUseCase()
                .addUserLogoutUseCase()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
        application.setSize(400, 380);
    }
}
