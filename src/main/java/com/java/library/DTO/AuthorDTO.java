package com.java.library.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.library.entity.Book;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AuthorDTO implements Serializable {

    private Long id;
    private Set<Book> books = new HashSet<>();
    private String name;
    private Date birthday;
    private String biography;
    private String nationality;


    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AuthorDTO author)) return false;
        return getId() == author.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
