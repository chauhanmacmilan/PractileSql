package com.example.practilesql;

public class BookModel {
    public String id;
    public String book;
    public String auther;

    public BookModel(String id, String book, String auther) {
        this.id = id;
        this.book = book;
        this.auther = auther;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }
}
