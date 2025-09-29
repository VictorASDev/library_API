package com.java.library.controller;

import com.java.library.DTO.BookDTO;
import com.java.library.service.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/api/library/book")
public class BookController {

    private final BookServices services;

    public BookController(BookServices services) {
        this.services = services;
    }

    @GetMapping(value = "/{id}")
    public BookDTO getById(@PathVariable("id") Long id) {
         return services.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> getAllBooks() {
        return services.findAll();
    }

    @GetMapping(value = "/author/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findAllByAuthor(@PathVariable("name") String name) {
        return services.findAllByAuthor(name);
    }

    @GetMapping(value = "/genre/{genre}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> findAllByGenre(@PathVariable("genre") String genre) {
        return services.findAllByGenre(genre);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus create(@RequestBody BookDTO book) {
        return services.create(book);
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delete(@PathVariable Long id) {
        return services.delete(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus update(@RequestBody BookDTO bookDto) {
        return services.update(bookDto);
    }
}
