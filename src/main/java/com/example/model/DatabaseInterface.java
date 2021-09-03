package com.example.model;

import java.util.List;

public interface DatabaseInterface {
    public List<Book> getBookList(String keyword, int searchType);
    public List<Person> getPersonList(String keyword, int searchType);
    public Book getBook(int circleId);/////??????

}
