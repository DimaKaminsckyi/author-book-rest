package com.test.trainee.service.impl;

import com.test.trainee.service.BookService;
import org.springframework.stereotype.Service;


import com.test.trainee.domain.BookDTO;
import com.test.trainee.entity.Book;
import com.test.trainee.repository.BookRepository;
import com.test.trainee.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void saveBook(BookDTO dto) {
        Book book = modelMapper.map(dto , Book.class);
        bookRepository.save(book);
    }

    @Override
    public BookDTO findBookById(int id) {
        Book entity = bookRepository.findById(id).get();
        BookDTO bookDTO = modelMapper.map(entity, BookDTO.class);
        return bookDTO;
    }

    @Override
    public List<BookDTO> findAllBooks() {
        List<Book> bookEntities = bookRepository.findAll();
        List<BookDTO> bookDTOs = modelMapper.mapAll(bookEntities, BookDTO.class);
        return bookDTOs;
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> getBooksByGenre(String genre) {
        List<Book> bookList = bookRepository.getBooksByGenre(genre);
        List<BookDTO> bookDTOs = modelMapper.mapAll(bookList , BookDTO.class);
        return bookDTOs;
    }

    @Override
    public List<Integer> findBooksByAuthors() {
        List<Integer> books = bookRepository.findBooksByAuthors();
        return books;
    }
}
