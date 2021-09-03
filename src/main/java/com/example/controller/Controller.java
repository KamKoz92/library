package com.example.controller;

import java.util.List;

import com.example.model.Book;
import com.example.model.DatabaseInterface;
import com.example.model.Person;

public class Controller {
    private DatabaseInterface db;


    public Controller(DatabaseInterface db) {
        this.db = db;
    }

    public List<Person> getUsers(String keyword, int searchType) {
        return db.getPersonList(keyword, searchType);
    }

    public List<Book> getBooks(String keyword, int searchType) {
        return db.getBookList(keyword, searchType);
    }
    
}
