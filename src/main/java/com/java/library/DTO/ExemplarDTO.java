package com.java.library.DTO;

import com.java.library.entity.Book;

import java.io.Serializable;

public class ExemplarDTO implements Serializable {
    private Long itemNumber;
    private String conservation;
    private String localization;
    private boolean available;
    private BookSummaryDTO book;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getConservation() {
        return conservation;
    }

    public void setConservation(String conservation) {
        this.conservation = conservation;
    }

    public long getItemNumber() {
        return itemNumber;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public BookSummaryDTO getBook() {
        return book;
    }

    public void setBook(BookSummaryDTO book) {
        this.book = book;
    }

    public void setItemNumber(Long itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExemplarDTO exemplar)) return false;
        return itemNumber != null && itemNumber.equals(exemplar.itemNumber);
    }

    @Override
    public int hashCode() {
        return itemNumber != null ? itemNumber.hashCode() : System.identityHashCode(this);
    }
}
