package com.example.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.util.List;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;


import com.example.model.Book;
import com.example.model.Person;

public class AdminPanel extends JPanel implements ActionListener {
    private JPanel infoPanel, usersPanel, booksPanel, updatePanel;
    private JButton logoutButton, exitButton;

    private JButton searchUserButton;
    private JTextField searchUserField;
    private JScrollPane searchUserPane;
    private JTable searchUserTable;
    private JComboBox<String> searchUserComboBox;

    private JButton addEntryButton, deleteEntryButton;
    private JTextField addEntryField, deleteEntryField;

    private JButton searchBookButton;
    private JTextField searchBookField;
    private JScrollPane searchBookPane;
    private JTable searchBookTable;
    private JComboBox<String> searchBookComboBox;

    public AdminPanel() {
        this.setLayout(new GridLayout(2, 2));
        this.setBorder(BorderFactory.createTitledBorder("Admin Panel"));
        setInfoPanel();
        setUsersPanel();
        setUpdatePanel();
        setBookPanel();
    }


    private JTextField fName, lName, id;
    private JTable infoTable;
    private JScrollPane infoPane;

    private void setInfoPanel() {

        infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Info"));
        logoutButton = new JButton("Logout");
        logoutButton.setName("logout");
        exitButton = new JButton("Exit");
        exitButton.setName("exit");
        fName = new JTextField(10);
        lName = new JTextField(10);
        id = new JTextField(10);
        fName.setEditable(false);
        lName.setEditable(false);
        id.setEditable(false);

        GridBagConstraints c = new GridBagConstraints();
    
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        infoPanel.add(logoutButton,c);

        c.weightx = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        infoPanel.add(exitButton,c);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        infoPanel.add(new JLabel("ID"),c);

        
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        infoPanel.add(id,c);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        infoPanel.add(new JLabel("First Name"),c);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        infoPanel.add(fName,c);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        infoPanel.add(new JLabel("Last Name"),c);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        infoPanel.add(lName,c);

        infoTable = new JTable(new BooksInfoTableModel());
        infoTable.setFillsViewportHeight(true);
        infoPane = new JScrollPane(infoTable);
        Dimension d = infoTable.getPreferredSize();
        infoPane.setPreferredSize(new Dimension(d.width, infoTable.getRowHeight() * 20 + 1));
        infoTable.getTableHeader().setReorderingAllowed(false);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 4;        
        infoPanel.add(infoPane,c);

        this.add(infoPanel);
    }

    private void setUsersPanel() {
        usersPanel = new JPanel();
        usersPanel.setBorder(BorderFactory.createTitledBorder("Users"));

        searchUserField = new JTextField(15);
        searchUserButton = new JButton("Search");
        searchUserButton.setName("searchUserButton");
        String [] cb = {"ID","First Name","Last Name"};
        searchUserComboBox = new JComboBox<String>(cb);

        searchUserTable = new JTable(new PersonTableModel());
        searchUserPane = new JScrollPane(searchUserTable);
        searchUserTable.setFillsViewportHeight(true);

        Dimension d = searchUserTable.getPreferredSize();
        searchUserPane.setPreferredSize(new Dimension(d.width * 2, searchUserTable.getRowHeight() * 20 + 1));
        searchUserTable.getTableHeader().setReorderingAllowed(false);

        usersPanel.add(searchUserField);
        usersPanel.add(searchUserComboBox);
        usersPanel.add(searchUserButton);
        usersPanel.add(searchUserPane);
        searchUserTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                id.setText(searchUserTable.getValueAt(searchUserTable.getSelectedRow(), 0).toString());
                fName.setText(searchUserTable.getValueAt(searchUserTable.getSelectedRow(), 1).toString());
                lName.setText(searchUserTable.getValueAt(searchUserTable.getSelectedRow(), 2).toString());
            }
        });
        this.add(usersPanel);
    }

    private void setUpdatePanel() {

        updatePanel = new JPanel();
        updatePanel.setBorder(BorderFactory.createTitledBorder("Add/Delete Entry"));

        addEntryButton = new JButton("Add");
        addEntryField = new JTextField(15);
        addEntryButton.setName("addEntryButton");

        deleteEntryButton = new JButton("Delete");
        deleteEntryField = new JTextField(15);
        deleteEntryButton.setName("deleteEntryButton");

        updatePanel.add(addEntryField);
        updatePanel.add(addEntryButton);
        updatePanel.add(deleteEntryField);
        updatePanel.add(deleteEntryButton);

        this.add(updatePanel);
    }

    private void setBookPanel() {
        booksPanel = new JPanel();
        booksPanel.setBorder(BorderFactory.createTitledBorder("Books"));

        searchBookField = new JTextField(15);
        searchBookButton = new JButton("Search");
        searchBookButton.setName("searchBookButton");
        String [] cb = {"ISBN","Title","Author"};
        searchBookComboBox = new JComboBox<String>(cb);

        searchBookTable = new JTable(new BookTableModel());
        searchBookPane = new JScrollPane(searchBookTable);
        searchBookTable.setFillsViewportHeight(true);

        Dimension d = searchBookTable.getPreferredSize();
        searchBookPane.setPreferredSize(new Dimension(d.width * 2, searchBookTable.getRowHeight() * 20 + 1));
        searchBookTable.getTableHeader().setReorderingAllowed(false);

        booksPanel.add(searchBookField);
        booksPanel.add(searchBookComboBox);
        booksPanel.add(searchBookButton);
        booksPanel.add(searchBookPane);

        this.add(booksPanel);
    }

    public void setListeners(Window mainWindow) {
        logoutButton.addActionListener(mainWindow);
        exitButton.addActionListener(mainWindow);
        searchBookButton.addActionListener(mainWindow);
        searchUserButton.addActionListener(mainWindow);
        addEntryButton.addActionListener(mainWindow);
        deleteEntryButton.addActionListener(mainWindow);
    }

    public String getUserKeyword() {
        return searchUserField.getText();
    }

    public String getBookKeyword() {
        return searchBookField.getText();
    }

    public void setUserTable(List<Person> pList) {
        PersonTableModel model = (PersonTableModel) searchUserTable.getModel();
        model.setColumnNumber(pList.size());
        for (int i = 0; i < pList.size(); i++) {
            model.setValueAt(pList.get(i).getId(), i, 0);
            model.setValueAt(pList.get(i).getFirstName(), i, 1);
            model.setValueAt(pList.get(i).getLastName(), i, 2);
        }
        model.fireTableDataChanged();
    }

    public void setBookTable(List<Book> bList) {
        BookTableModel model = (BookTableModel) searchBookTable.getModel();
        model.setColumnNumber(bList.size());
        for (int i = 0; i < bList.size(); i++) {
            model.setValueAt(bList.get(i).getISBN(), i, 0);
            model.setValueAt(bList.get(i).getTitle(), i, 1);
            model.setValueAt(bList.get(i).getAuthor(), i, 2);
            ;
        }
        model.fireTableDataChanged();
    }

    public int getUserSearchType() {
        return searchUserComboBox.getSelectedIndex();
    }

    public int getBookSearchType() {
        return searchBookComboBox.getSelectedIndex();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}

class PersonTableModel extends AbstractTableModel {
    private String[] columnNames = { "ID", "First Name", "Last Name" };
    private Object[][] data = {};

    @Override
    public int getRowCount() {
        return data.length;
    }

    public void setColumnNumber(int size) {
        if (data.length != size) {
            data = new Object[size][3];
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

class BookTableModel extends AbstractTableModel {
    private String[] columnNames = { "ISBN", "Title", "Author"};
    private Object[][] data = {};

    @Override
    public int getRowCount() {
        return data.length;
    }

    public void setColumnNumber(int size) {
        if (data.length != size) {
            data = new Object[size][3];
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

class BooksInfoTableModel extends AbstractTableModel {
    private String[] columnNames = { "ISBN", "Title", "Author","Borrow Date","Return Date"};
    private Object[][] data = {};

    @Override
    public int getRowCount() {
        return data.length;
    }

    public void setColumnNumber(int size) {
        if (data.length != size) {
            data = new Object[size][5];
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}