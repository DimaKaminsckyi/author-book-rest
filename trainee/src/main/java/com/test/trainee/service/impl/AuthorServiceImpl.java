package com.test.trainee.service.impl;

import com.test.trainee.domain.AuthorDTO;
import com.test.trainee.entity.Author;
import com.test.trainee.repository.AuthorRepository;
import com.test.trainee.service.AuthorService;
import com.test.trainee.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    ObjectMapperUtils modelMapper;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void saveAuthor(AuthorDTO author) {
        Author author1 = modelMapper.map(author , Author.class);
        authorRepository.save(author1);
    }

    @Override
    public void deleteAuthor(int authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public AuthorDTO findAuthorById(int id) {
        Author entity = authorRepository.findById(id).get();
        AuthorDTO auhtorDTO = modelMapper.map(entity, AuthorDTO.class);
        return auhtorDTO;
    }

    @Override
    public List<AuthorDTO> findAllAuthors() {
        List<Author> authorEntities = authorRepository.findAll();
        List<AuthorDTO> authorDTOs = modelMapper.mapAll(authorEntities, AuthorDTO.class);
        return authorDTOs;    }

    @Override
    public int findAuthorMostBooks() {
        return authorRepository.findAuthorMostBooks();
    }

    @Override
    public List<AuthorDTO> findOlderAuthorsByAge(int age) {
        List<Author> authorList = authorRepository.findOlderAuthorsByAge(age);
        List<AuthorDTO> authorDTOs = modelMapper.mapAll(authorList , AuthorDTO.class);
        return authorDTOs;
    }
}
