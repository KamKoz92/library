package com.example.model;

public class Book {

    private String title;
    private String author;
    private int isbn;
    
    public String getTitle() {
        return title;
    }
    public int getISBN() {
        return isbn;
    }
    public void setISBN(int isbn) {
        this.isbn = isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    } 

}
