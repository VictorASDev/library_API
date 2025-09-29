package com.java.library.service;

import com.java.library.DTO.BookDTO;
import com.java.library.entity.Author;
import com.java.library.entity.Book;
import com.java.library.mapper.custom.BookMapper;
import com.java.library.mapper.dozer.ObjectMapper;
import com.java.library.repository.AuthorRepository;
import com.java.library.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServices {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServices(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public BookDTO findById(Long id) {
        var bookOnDb = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found on database!"));

        return ObjectMapper.parseObject(bookOnDb, BookDTO.class);
    }

    public List<BookDTO> findAll() {
        return ObjectMapper
                .parseListObject(bookRepository.findAll(), BookDTO.class);
    }

    public List<BookDTO> findAllByAuthor(String authorsName) {
        return ObjectMapper
                .parseListObject(bookRepository.findAllByAuthors_Name(authorsName), BookDTO.class);
    }

    public List<BookDTO> findAllByGenre(String genre) {
        return ObjectMapper
                .parseListObject(bookRepository.findAllByGenre(genre), BookDTO.class);
    }

    public HttpStatus create(BookDTO dto) {
        var book = BookMapper.dtoToBook(dto);

        bookRepository.save(book);

        return HttpStatus.CREATED;
    }

    public HttpStatus delete(Long id) {
        bookRepository.deleteById(id);

        return HttpStatus.NO_CONTENT;
    }

    public HttpStatus update(BookDTO bookDto) {
        Book bookOnDb = bookRepository.findById(bookDto.getId()).
                orElseThrow(() -> new EntityNotFoundException("Book dont exist on data base!"));

        bookOnDb.setId(bookDto.getId());
        bookOnDb.setTitle(bookDto.getTitle());
        bookOnDb.setSynopsis(bookDto.getSynopsis());
        bookOnDb.setPublisher(bookDto.getPublisher());
        bookOnDb.setGenre(bookDto.getGenre());

        Set<Author> authors = bookDto.getAuthors().stream()
                .map(dtoAuthor -> authorRepository.findByName(dtoAuthor.getName())
                        .orElseGet(() -> {
                            Author newAuthor = new Author();
                            newAuthor.setName(dtoAuthor.getName());
                            newAuthor.setBiography(dtoAuthor.getBiography());
                            newAuthor.setBirthday(dtoAuthor.getBirthday());
                            newAuthor.setNationality(dtoAuthor.getNationality());
                            return newAuthor;
                        }))
                .collect(Collectors.toSet());


        bookOnDb.setAuthors(authors);
        //Vai dar o mesmo erro que authors
        bookOnDb.setExamples(bookDto.getExamples());

        bookRepository.save(bookOnDb);

        return HttpStatus.NO_CONTENT;
    }
}
