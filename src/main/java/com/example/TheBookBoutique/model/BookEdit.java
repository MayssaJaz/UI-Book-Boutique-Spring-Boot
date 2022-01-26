package com.example.TheBookBoutique.model;


import java.util.Objects;

public class BookEdit {


    private String title;
    private String author;
    private int quantity;
    private String genre;
    public BookEdit(){

    }
    public BookEdit(String title, String author, int quantity, String genre) {
        super();
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.genre = genre;
    }


    public String getTitle() {
        return title;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}