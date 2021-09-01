package com.example.model;

import java.util.List;

public interface DatabaseInterface {
    public List<Book> getBookList(String keyword);
    public List<Person> getPersonList(String keyword);
    public Book getBook(int circleId);/////??????

}
