package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

public class DerbyDatabase implements DatabaseInterface {

    @Override
    public Book getBook(int bookId) {
        // Connection conn = null;
        // try {
        //     String url = "jdbc:derby://localhost:1527/db";
        //     conn = DriverManager.getConnection(url);
        //     PreparedStatement ps = conn.prepareStatement("SELECT * FROM books where id = ?");
        //     ps.setInt(1, bookId);
        //     Book book = null;
        //     ResultSet rs = ps.executeQuery();
        //     if (rs.next()) {
        //         // book = new Book(bookId, rs.getString("name"));
        //     }
        //     rs.close();
        //     ps.close();
        //     return book;
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // } finally {
        //     try {
        //         conn.close();
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //     }
        // }
        return null;
    }

    @Override
    public List<Book> getBookList(String keyword) {
        Connection conn = null;
        List<Book> bookList = new ArrayList<Book>();
        try {
            String url = "jdbc:derby://localhost:1527/db";
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = null;
            if(keyword.isEmpty()) {
                ps = conn.prepareStatement("SELECT * FROM book");
            } else if(NumberUtils.isCreatable(keyword)) {
                ps = conn.prepareStatement("SELECT * FROM book WHERE isbn = ?");
                ps.setString(1, keyword);
            } else {
                ps = conn.prepareStatement("SELECT * FROM book WHERE title = ?");
                ps.setString(1, keyword);
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Book tempBook = new Book(rs.getInt(1),rs.getString("title"), rs.getString("author"));
                bookList.add(tempBook);
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
        return bookList;
    }

    @Override
    public List<Person> getPersonList(String keyword) {
        Connection conn = null;
        List<Person> personList = new ArrayList<Person>();
        try {
            String url = "jdbc:derby://localhost:1527/db";
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = null;
            if (keyword.isEmpty()) {
                ps = conn.prepareStatement("SELECT * FROM person");
            } else if (NumberUtils.isCreatable(keyword)) {
                ps = conn.prepareStatement("SELECT * FROM person WHERE id = ?");
                ps.setString(1, keyword);
            } else {
                ps = conn.prepareStatement("SELECT * FROM person WHERE firstname = ?");
                ps.setString(1, keyword);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person tempPerson = new Person(rs.getInt(1), rs.getString("firstname"), rs.getString("lastname"));
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
