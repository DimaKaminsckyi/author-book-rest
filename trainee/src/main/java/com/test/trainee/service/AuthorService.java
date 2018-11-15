package com.test.trainee.service;

import com.test.trainee.domain.AuthorDTO;

import java.util.List;

public interface AuthorService {

    void saveAuthor(AuthorDTO author);

    void deleteAuthor(int authorId);

    AuthorDTO findAuthorById(int id);

    List<AuthorDTO> findAllAuthors();

    int findAuthorMostBooks();

    List<AuthorDTO> findOlderAuthorsByAge(int age);

}
