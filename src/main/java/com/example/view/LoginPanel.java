package com.example.view;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JButton loginButton;
    private JPasswordField loginPassword;
    private JTextField loginUserName;

    public LoginPanel() {
        loginPassword = new JPasswordField(10);
        loginUserName = new JTextField(10);
        loginButton = new JButton("Ok");
        loginButton.setName("login");
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 10);
        gc.fill = GridBagConstraints.NONE;

        add(new JLabel("Name: "), gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(100, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(loginUserName, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 10);
        gc.fill = GridBagConstraints.NONE;

        add(new JLabel("Password: "), gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.fill = GridBagConstraints.NONE;

        add(loginPassword, gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 100;
        gc.fill = GridBagConstraints.NONE;
        add(loginButton, gc);
    }

    public void setListeners(Window mainWindow) {
        loginButton.addActionListener(mainWindow);
    }

    public int checkUsernameAndPassword() {
        if (loginPassword.getText().equals("admin") && loginUserName.getText().equals("admin")) {
            return 1;
        } else if (loginPassword.getText().equals("user") && loginUserName.getText().equals("user")) {
            return 2;
        } else {
            return 0;
        }

    }

    public void clearFields() {
        this.loginPassword.setText("");
        this.loginUserName.setText("");
    }

}
