package com.example.model;

import java.util.List;

public interface DatabaseInterface {
    public List<Book> getBooks();
    public List<Person> getPersonList();
    public Book getBook(int circleId);

}
