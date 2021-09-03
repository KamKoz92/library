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
        // String url = "jdbc:derby://localhost:1527/db";
        // conn = DriverManager.getConnection(url);
        // PreparedStatement ps = conn.prepareStatement("SELECT * FROM books where id =
        // ?");
        // ps.setInt(1, bookId);
        // Book book = null;
        // ResultSet rs = ps.executeQuery();
        // if (rs.next()) {
        // // book = new Book(bookId, rs.getString("name"));
        // }
        // rs.close();
        // ps.close();
        // return book;
        // } catch (Exception e) {
        // throw new RuntimeException(e);
        // } finally {
        // try {
        // conn.close();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // }
        return null;
    }

    @Override
    public List<Book> getBookList(String keyword, int searchType) {
        Connection conn = null;
        List<Book> bookList = new ArrayList<Book>();
        try {
            String url = "jdbc:derby://localhost:1527/db";
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = null;

            if (keyword.isEmpty()) {
                switch (searchType) {
                    case 0:
                        ps = conn.prepareStatement("SELECT * FROM book ORDER BY isbn ASC");
                        break;
                    case 1:
                        ps = conn.prepareStatement("SELECT * FROM book ORDER BY title ASC");
                        break;
                    case 2:
                        ps = conn.prepareStatement("SELECT * FROM book ORDER BY author ASC");
                        break;
                    default:
                        System.out.println("Unknown book search type for wildcard");
                        break;
                }
            } else {
                switch (searchType) {
                    case 0:
                        ps = conn.prepareStatement("SELECT * FROM book WHERE isbn = ?");
                        break;
                    case 1:
                        ps = conn.prepareStatement("SELECT * FROM book WHERE title = ?");
                        /// possible upgrade to search by first word of title rather than full title;
                    case 2:
                        ps = conn.prepareStatement("SELECT * FROM book WHERE author = ?");
                    default:
                        System.out.println("Unknown book search type for specific search");
                        break;
                }
                ps.setString(1, keyword); // possible nullexception if default case;
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book tempBook = new Book(rs.getInt(1), rs.getString("title"), rs.getString("author"));
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
    public List<Person> getPersonList(String keyword, int searchType) {
        Connection conn = null;
        List<Person> personList = new ArrayList<Person>();
        try {
            String url = "jdbc:derby://localhost:1527/db";
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = null;

            if (keyword.isEmpty()) {
                switch (searchType) {
                    case 0:
                        ps = conn.prepareStatement("SELECT * FROM person ORDER BY id ASC");
                        break;
                    case 1:
                        ps = conn.prepareStatement("SELECT * FROM person ORDER BY firstname ASC");
                        break;
                    case 2:
                        ps = conn.prepareStatement("SELECT * FROM person ORDER BY lastname ASC");
                        break;
                    default:
                        System.out.println("Unknown person search type for wildcard");
                        break;
                }
            } else {
                switch (searchType) {
                    case 0:
                        ps = conn.prepareStatement("SELECT * FROM person WHERE id = ?");
                        break;
                    case 1:
                        ps = conn.prepareStatement("SELECT * FROM person WHERE firstname = ?");
                        break;
                    case 2:
                        ps = conn.prepareStatement("SELECT * FROM person WHERE lastname = ?");
                        break;
                    default:
                        System.out.println("Unknown person search type for specific search");
                        break;
                }
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
