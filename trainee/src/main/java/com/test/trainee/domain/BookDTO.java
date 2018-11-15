package com.test.trainee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.trainee.entity.Author;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    private int id;
    private String name;
    private Date published;
    private String genre;
    private int rating;
    @JsonIgnore
    private List<Author> author = new ArrayList<>();

    public BookDTO() {
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

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }
}
