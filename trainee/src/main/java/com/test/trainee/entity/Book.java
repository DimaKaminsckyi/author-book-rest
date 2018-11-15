package com.test.trainee.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private Date published;

    @Column
    private String genre;

    @Column
    private int rating;

    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String name, Date published, String genre, int rating, List<Author> authors) {
        this.name = name;
        this.published = published;
        this.genre = genre;
        this.rating = rating;
        this.authors = authors;
    }

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

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", published=" + published +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", authors=" + authors +
                '}';
    }
}
