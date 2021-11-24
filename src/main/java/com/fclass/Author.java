package com.fclass;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Author {
    String authorName;
    int authorAge;
    @ElementCollection
    List<String> books;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorAge() {
        return authorAge;
    }

    public void setAuthorAge(int authorAge) {
        this.authorAge = authorAge;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorName='" + authorName + '\'' +
                ", authorAge=" + authorAge +
                ", books=" + books +
                '}';
    }
}
