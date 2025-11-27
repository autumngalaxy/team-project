package view;

import javax.swing.*;

import interface_adapter.homepage.LoginChooseViewModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginChooseView extends JPanel implements ActionListener, PropertyChangeListener {
	
    private final String viewName = "loginChoose";

    // 使用你的架构的 ViewModel（不要再定义局部变量遮蔽）
    private final LoginChooseViewModel loginChooseViewModel;
//    private final UserLoginViewModel userLoginViewModel;

//    public LoginChooseView(LoginChooseView loginChooseView) {
    public LoginChooseView(LoginChooseViewModel loginChooseViewModel) {
        this.loginChooseViewModel  = loginChooseViewModel ;

        // 让这个 View 监听自己的 ViewModel
        this.loginChooseViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Pet Adoption System");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton userLoginBtn = new JButton("User Login");
        JButton staffLoginBtn = new JButton("Staff Login");
        JButton adminLoginBtn = new JButton("Admin Login");
        JButton createAccountBtn = new JButton("Create New Account");

        Dimension buttonSize = new Dimension(200, 40);
        userLoginBtn.setMaximumSize(buttonSize);
        staffLoginBtn.setMaximumSize(buttonSize);
        adminLoginBtn.setMaximumSize(buttonSize);
        createAccountBtn.setMaximumSize(buttonSize);

        userLoginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        staffLoginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLoginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(30));
        add(title);
        add(Box.createVerticalStrut(30));

        add(userLoginBtn);
        add(Box.createVerticalStrut(20));

        add(staffLoginBtn);
        add(Box.createVerticalStrut(20));

        add(adminLoginBtn);
        add(Box.createVerticalStrut(20));

        add(createAccountBtn);
        // 按钮事件 → 设置 state → 通知 viewManager 切换画面        
        // ★★★ 关键：用 “login” 属性名称 + userType 作为 value
        userLoginBtn.addActionListener(e ->
                loginChooseViewModel.firePropertyChange("login", "user"));

        staffLoginBtn.addActionListener(e ->
                loginChooseViewModel.firePropertyChange("login", "staff"));

        adminLoginBtn.addActionListener(e ->
                loginChooseViewModel.firePropertyChange("login", "admin"));

        createAccountBtn.addActionListener(e ->
                loginChooseViewModel.firePropertyChange("createAccount"));
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       
	}
  
}
