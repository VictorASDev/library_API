package com.java.library.mapper.custom;

import com.java.library.DTO.BookDTO;
import com.java.library.entity.Book;
import org.springframework.stereotype.Service;

public class BookMapper {
    public static Book dtoToBook(BookDTO dto) {
        var book = new Book();

        book.setId(null);
        book.setAuthors(dto.getAuthors());
        book.setExamples(dto.getExamples());
        book.setGenre(dto.getGenre());
        book.setPublisher(dto.getPublisher());
        book.setSynopsis(dto.getSynopsis());
        book.setTitle(dto.getTitle());

        return book;
    }

    public static BookDTO bookToDTO(Book book) {
        var dto = new BookDTO();

        dto.setId(book.getId());
        dto.setAuthors(book.getAuthors());
        dto.setExamples(book.getExamples());
        dto.setGenre(book.getGenre());
        dto.setPublisher(book.getPublisher());
        dto.setSynopsis(book.getSynopsis());
        dto.setTitle(book.getTitle());

        return dto;
    }
}
