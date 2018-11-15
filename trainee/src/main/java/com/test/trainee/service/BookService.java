package com.test.trainee.service;

import com.test.trainee.domain.BookDTO;

import java.util.List;

public interface BookService {

    void saveBook(BookDTO dto);

    BookDTO findBookById(int id);

    List<BookDTO> findAllBooks();

    void deleteBook(int id);

    List<BookDTO> getBooksByGenre(String genre);

    List<Integer> findBooksByAuthors();
}
