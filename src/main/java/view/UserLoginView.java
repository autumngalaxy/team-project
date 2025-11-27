package view;

import interface_adapter.user_login.UserLoginController;
import interface_adapter.user_login.UserLoginState;
import interface_adapter.user_login.UserLoginViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserLoginView extends JPanel implements ActionListener, PropertyChangeListener {

    // update start 20251126
    private final String viewName = "login";
//	private final String viewName = "log in";;
    // update end 20251126
	
    private final UserLoginViewModel UserloginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;
    private UserLoginController userLoginController = null;
    
    // add start 20251126
    private String userType = "user";  // ← ★新增：user / staff / admin  ★ 可动态改变（默认 user）
    private JLabel titleLabel; // ★ 用于 updateTitle() 修改标题
    // add end 20251126

    public UserLoginView(UserLoginViewModel userloginViewModel, String userType) {
        this.UserloginViewModel  = userloginViewModel ;
        this.UserloginViewModel.addPropertyChangeListener(this);
        // add start 20251126
        this.userType = userType;                    // ★ 保存调用时类型
//        this.viewName = userType + " login";          // ★ 修改 viewName

        // ★ 根据角色动态改变标题
//        final JLabel title = new JLabel(userType.substring(0,1).toUpperCase() 
//                                        + userType.substring(1) 
//                                        + " Login");
        // ★ 标题 label → 可动态改变
        this.titleLabel = new JLabel(formatTitle(userType));
        
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        // add end 20251126
        
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JPanel buttons = new JPanel();
        logIn = new JButton("log in");
        buttons.add(logIn);
        cancel = new JButton("cancel");
        buttons.add(cancel);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            final UserLoginState currentState = UserloginViewModel.getState();

                            // update start 20251126
                            // ★ 将 loginType 传给 Controller
                            userLoginController.execute(
                            		userType,
                                    currentState.getUsername(),
                                    currentState.getUserPassword()
                            );
                            // update end 20251126
                        }
                    }
                }
        );
//        cancel.addActionListener(
//                evt -> {
//                    if(evt.getSource().equals(cancel)){
//                        this.userLogoutController.execute();
//                    }
//                }
//        );

		cancel.addActionListener(e -> {
		    if (userLoginController != null) {
		        userLoginController.goBack();
		    }
		});
        		
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final UserLoginState currentState = userloginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                userloginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final UserLoginState currentState = userloginViewModel.getState();
                currentState.setUserPassword(new String(passwordInputField.getPassword()));
                userloginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // add start 20251126
        // ★ 增加上下空间让窗口变高
        this.add(Box.createVerticalStrut(20)); 
        // add end 20251126
        
//        this.add(title);
        this.add(titleLabel);   // ★ 添加正确的 titleLabel
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordErrorField);
        this.add(passwordInfo);
        this.add(buttons);

    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserLoginState state = (UserLoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(UserLoginState state) {
        usernameInputField.setText(state.getUsername());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(UserLoginController loginController) {
        this.userLoginController = loginController;
    }

    // ★★★★★ 新增：动态设置 userType
    public void setUserType(String userType) {
        this.userType = userType;
//        this.viewName = userType + " login";
        updateTitle();
    }

    // ★★★★★ 新增：更新标题
    public void updateTitle() {
        this.titleLabel.setText(formatTitle(userType));

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setTitle(getTitleText());
        }

        revalidate();
        repaint();
    }

    private String formatTitle(String type) {
        return type.substring(0, 1).toUpperCase() + type.substring(1) + " Login";
    }

    public String getTitleText() {
        return formatTitle(userType); // 返回 “User Login” / “Staff Login” / “Admin Login”
    }
}
