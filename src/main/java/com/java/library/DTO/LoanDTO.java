package com.java.library.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class LoanDTO implements Serializable {
    private Long itemNumber;
    private BookSummaryDTO book;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate rental;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate limit;

    public BookSummaryDTO getBook() {
        return book;
    }

    public void setBook(BookSummaryDTO book) {
        this.book = book;
    }

    public Long getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Long itemNumber) {
        this.itemNumber = itemNumber;
    }

    public LocalDate getLimit() {
        return limit;
    }

    public void setLimit(LocalDate limit) {
        this.limit = limit;
    }

    public LocalDate getRental() {
        return rental;
    }

    public void setRental(LocalDate rental) {
        this.rental = rental;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LoanDTO loanDTO)) return false;
        return Objects.equals(getItemNumber(), loanDTO.getItemNumber()) && Objects.equals(getBook(), loanDTO.getBook()) && Objects.equals(getRental(), loanDTO.getRental()) && Objects.equals(getLimit(), loanDTO.getLimit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemNumber(), getBook(), getRental(), getLimit());
    }
}