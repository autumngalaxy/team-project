package view;

import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("宠物领养系统");
        setSize(300, 200);
        setLayout(null);

        JButton registerBtn = new JButton("创建新账户");
        JButton userLoginBtn = new JButton("用户登录");
        JButton adminLoginBtn = new JButton("管理员登录");

        registerBtn.setBounds(50, 30, 180, 30);
        userLoginBtn.setBounds(50, 70, 180, 30);
        adminLoginBtn.setBounds(50, 110, 180, 30);

        add(registerBtn);
        add(userLoginBtn);
        add(adminLoginBtn);

//        registerBtn.addActionListener(e -> new UserRegister());
//        userLoginBtn.addActionListener(e -> new UserLogin());
//        adminLoginBtn.addActionListener(e -> new AdminLogin());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
