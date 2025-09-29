package com.java.library.repository;

import com.java.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findAllByAuthors_Name(String name);

    public List<Book> findAllByGenre(String genre);

    public Optional<Book> findByTitle(String title);
}
