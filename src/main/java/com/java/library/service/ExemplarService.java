package com.java.library.service;

import com.java.library.DTO.BookDTO;
import com.java.library.DTO.BookSummaryDTO;
import com.java.library.DTO.ExemplarDTO;
import com.java.library.DTO.LoanDTO;
import com.java.library.entity.Exemplar;
import com.java.library.exceptions.custom.ExemplarNotAvailableException;
import com.java.library.mapper.dozer.ObjectMapper;
import com.java.library.repository.BookRepository;
import com.java.library.repository.ExemplarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ExemplarService {

    private final ExemplarRepository repository;
    private final BookRepository bookRepository;

    public ExemplarService(BookRepository bookRepository, ExemplarRepository repository) {
        this.bookRepository = bookRepository;
        this.repository = repository;
    }

    public HttpStatus create(ExemplarDTO dto) {
        var book = bookRepository.findById(dto.getBook().getId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found!"));

        Exemplar exemplar = new Exemplar();
        exemplar.setConservation(dto.getConservation());
        exemplar.setLocalization(dto.getLocalization());
        exemplar.setAvailable(dto.isAvailable());
        exemplar.setBook(book);

        repository.save(exemplar);

        return HttpStatus.CREATED;
    }

    public ExemplarDTO findById(Long id) {
        var exemplar = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Exemplar not found!"));

        return ObjectMapper.parseObject(exemplar, ExemplarDTO.class);
    }

    public List<ExemplarDTO> findAllByBook(String title) {
        var book = bookRepository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException("Book not found!"));

        return ObjectMapper.parseListObject(repository.findAllByBook(book), ExemplarDTO.class);
    }

    public ExemplarDTO update(ExemplarDTO dto) {
        var exemplar = repository.findById(dto.getItemNumber())
                .orElseThrow(() -> new EntityNotFoundException("Exemplar not found!"));

        exemplar.setLocalization(dto.getLocalization());
        exemplar.setAvailable(dto.isAvailable());
        exemplar.setConservation(dto.getConservation());

        return ObjectMapper.parseObject(exemplar, ExemplarDTO.class);
    }

    public HttpStatus delete(Long id) {
        repository.deleteById(id);

        return HttpStatus.NO_CONTENT;
    }

    public List<ExemplarDTO> findAll() {
        return ObjectMapper.parseListObject(repository.findAll(), ExemplarDTO.class);
    }

    public LoanDTO getLoan(Long id) {
        var exemplar = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exemplar not found!"));

        if (!exemplar.isAvailable())
            throw new ExemplarNotAvailableException("Exemplar not available to loan");

        exemplar.setAvailable(false);
        repository.save(exemplar);

        var loan = new LoanDTO();
        loan.setRental(LocalDate.now());
        loan.setLimit(loan.getRental().plusDays(7));
        loan.setItemNumber(exemplar.getItemNumber());
        loan.setBook(ObjectMapper
                .parseObject(exemplar.getBook(), BookSummaryDTO.class));

        return loan;
    }
}
