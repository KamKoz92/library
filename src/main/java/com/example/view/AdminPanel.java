package com.example.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import com.example.model.Book;
import com.example.model.Person;

public class AdminPanel extends JPanel {
    private JPanel buttonsPanel, usersPanel, booksPanel, updatePanel;
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
        setButtonsPanel();
        setBookPanel();
        setUpdatePanel();
        setUsersPanel();
    }

    private void setButtonsPanel() {

        buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Control"));
        logoutButton = new JButton("Logout");
        logoutButton.setName("logout");
        exitButton = new JButton("Exit");
        exitButton.setName("exit");

        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(logoutButton);
        buttonsPanel.add(exitButton);

        this.add(buttonsPanel);
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
        return searchBookComboBox.getSelectedIndex();
    }

    public int getBookSearchType() {
        return searchBookComboBox.getSelectedIndex();
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

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }

    @Override
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        super.fireTableRowsInserted(firstRow, lastRow);
    }
}

class BookTableModel extends AbstractTableModel {
    private String[] columnNames = { "ISBN", "Title", "Author" };
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