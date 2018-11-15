package com.test.trainee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private Date born;

    @ManyToMany(cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id" )})
    @JsonBackReference
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name, String gender, Date born, List<Book> books) {
        this.name = name;
        this.gender = gender;
        this.born = born;
        this.books = books;
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

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", born=" + born +
                ", books=" + books +
                '}';
    }
}

