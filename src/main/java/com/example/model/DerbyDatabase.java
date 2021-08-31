package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DerbyDatabase implements DatabaseInterface {

    @Override
    public Book getBook(int bookId) {
        Connection conn = null;
        try {
            String url = "jdbc:derby://localhost:1527/db";
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM books where id = ?");
            ps.setInt(1, bookId);
            Book book = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // book = new Book(bookId, rs.getString("name"));
            }
            rs.close();
            ps.close();
            return book;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Book> getBooks() {

        return null;
    }

    @Override
    public List<Person> getPersonList() {
        Connection conn = null;
        List<Person> personList = new ArrayList<Person>();
        try {
            String url = "jdbc:derby://localhost:1527/db";
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM person");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Person tempPerson = new Person(rs.getInt(1),rs.getString("firstname"), rs.getString("lastname"));
                personList.add(tempPerson);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personList;
    }
}
