package com.test.trainee.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.trainee.entity.Book;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {

    private int id;
    private String name;
    private String gender;
    private Date born;
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    public AuthorDTO() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
