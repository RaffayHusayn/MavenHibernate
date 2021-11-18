package com.fclass;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    int iabn;
    String name;
    String author;

    public void setName(String name) {
        this.name = name;
    }

    public void setIabn(int iabn) {
        this.iabn = iabn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIabn(){
        return iabn;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }
}
