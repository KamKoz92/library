package com.example.view;

import com.example.controller.Controller;
import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private UserPanel userPanel;
    private AdminPanel adminPanel;
    private Controller controller;

    public Window(String name, Controller controller) {
        frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);
        this.controller = controller;

        mainPanel = new JPanel(new CardLayout());
        loginPanel = new LoginPanel();
        adminPanel = new AdminPanel();
        userPanel = new UserPanel();

        mainPanel.add(loginPanel, "loginPanel");
        mainPanel.add(adminPanel, "adminPanel");
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "adminPanel");
        setListeners();
        frame.getContentPane().add(mainPanel);

        frame.setVisible(true);
    }

    private void setListeners() {
        loginPanel.setListeners(this);
        adminPanel.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = ((JButton) e.getSource()).getName();
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        switch (buttonName) {
            case "login":
                if (loginPanel.checkUsernameAndPassword() == 1) {
                        cl.show(mainPanel, "adminPanel");
                        loginPanel.clearFields();
                } else if(loginPanel.checkUsernameAndPassword() == 2) {
                    cl.show(mainPanel, "userPanel");
                    loginPanel.clearFields();
                }
                break;

            case "logout":
                cl.show(mainPanel, "loginPanel");
                break;

            case "exit":
                System.exit(1);
                break;

            case "searchUserButton":
                String userKeyword = adminPanel.getUserKeyword();
                int userSearchType = adminPanel.getUserSearchType();
                System.out.println(userSearchType);
                adminPanel.setUserTable(controller.getUsers(userKeyword,userSearchType));
                break;

            case "searchBookButton":
                String bookKeyword = adminPanel.getBookKeyword();
                int bookSearchType = adminPanel.getBookSearchType();
                System.out.println(bookSearchType);
                adminPanel.setBookTable(controller.getBooks(bookKeyword,bookSearchType));
                break;

            default:
                System.out.println("Unknown button pressed");
                break;
        }
    }

}
