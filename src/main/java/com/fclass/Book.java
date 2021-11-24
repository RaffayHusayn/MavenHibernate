package com.fclass;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    int id;
    String name;
    @Embedded
    Author author;
    @ManyToOne(cascade = CascadeType.ALL)
    Publisher publisher;
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    List<Library> libraryList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Library> getLibraryList() {
        return libraryList;
    }

    public void setLibraryList(List<Library> libraryList) {
        this.libraryList = libraryList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", libraryList=" + libraryList +
                '}';
    }

}
