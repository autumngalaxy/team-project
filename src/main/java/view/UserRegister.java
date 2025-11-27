package view;

//import repository.UserRepository;

import javax.swing.*;
import java.awt.*;

public class UserRegister extends JFrame {

    public UserRegister() {
        setTitle("用户注册");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton registerBtn = new JButton("注册");
        JButton cancelBtn = new JButton("取消");

        add(new JLabel("用户名："));
        add(usernameField);
        add(new JLabel("密码："));
        add(passwordField);

        add(registerBtn);
        add(cancelBtn);

        registerBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

//            boolean success = UserRepository.register(user, pass);
//
//            if (success) {
//                JOptionPane.showMessageDialog(this, "注册成功！");
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "用户名已存在！");
//            }
        });

        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
}
