package com.java.library.repository;

import com.java.library.DTO.BookDTO;
import com.java.library.entity.Book;
import com.java.library.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
    public List<Exemplar> findAllByBook(Book book);
}
