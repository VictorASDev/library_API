package com.java.library.controller;

import com.java.library.DTO.ExemplarDTO;
import com.java.library.DTO.LoanDTO;
import com.java.library.service.ExemplarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/exemplar")
public class ExemplarController {

    private final ExemplarService service;
    public ExemplarController(ExemplarService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ExemplarDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExemplarDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/book/{title}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExemplarDTO> findAllByBook(@PathVariable String title) {
        return service.findAllByBook(title);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus create(@RequestBody ExemplarDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ExemplarDTO update(@RequestBody ExemplarDTO dto) {
        return service.update(dto);
    }

    @GetMapping(value = "/loan/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public LoanDTO getLoan(@PathVariable Long id) {
        return service.getLoan(id);
    }
}
