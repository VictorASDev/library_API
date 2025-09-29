package com.java.library.mapper.custom;

import com.java.library.DTO.AuthorDTO;
import com.java.library.entity.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {
    public Author dtoToAuthor(AuthorDTO dto) {
        var author = new Author();

        author.setId(dto.getId());
        author.setBiography(dto.getBiography());
        author.setBirthday(dto.getBirthday());
        author.setName(dto.getName());
        author.setNationality(dto.getNationality());
        author.setBooks(dto.getBooks());

        return author;
    }

    public AuthorDTO authorToDTO(Author author) {
        var dto = new AuthorDTO();

        dto.setId(author.getId());
        dto.setBiography(author.getBiography());
        dto.setBirthday(author.getBirthday());
        dto.setName(author.getName());
        dto.setNationality(author.getNationality());
        dto.setBooks(author.getBooks());

        return dto;
    }
}
