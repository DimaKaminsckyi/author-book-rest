package com.test.trainee.controller;


import com.test.trainee.domain.AuthorDTO;
import com.test.trainee.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
        List<AuthorDTO> authorDTOs = authorService.findAllAuthors();
        return new ResponseEntity<List<AuthorDTO>>(authorDTOs , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") int id){
        AuthorDTO authorDTO = authorService.findAuthorById(id);
        if (authorDTO != null){
            authorService.deleteAuthor(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createAuthor(@RequestBody AuthorDTO dto){
        authorService.saveAuthor(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{authorId}")
    public ResponseEntity<Void> updateAuthor(@PathVariable("authorId") int id,
                                             @RequestBody AuthorDTO authorDTO){

        AuthorDTO authorDTOFromDB = authorService.findAuthorById(id);

        if(authorDTOFromDB != null){
            authorDTO.setId(id);
            authorService.saveAuthor(authorDTO);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthorById(
            @PathVariable("authorId") int id) {

        AuthorDTO dto = authorService.findAuthorById(id);
        return new ResponseEntity<AuthorDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/most-books")
    public ResponseEntity<AuthorDTO> getAuthorMostBooks(){

        AuthorDTO dto = authorService.findAuthorById(authorService.findAuthorMostBooks());

        return new ResponseEntity<AuthorDTO>(dto , HttpStatus.OK);

    }

    @GetMapping("/older{age}")
    public ResponseEntity<List<AuthorDTO>> findOlderAuthorsByAge(@RequestParam(value = "age" , required = false) int age){
        List<AuthorDTO> authorDTOs = authorService.findOlderAuthorsByAge(age);
        Collections.sort(authorDTOs, new Comparator<AuthorDTO>() {
            @Override
            public int compare(AuthorDTO o1, AuthorDTO o2) {
                if (o1.getBorn() == null || o2.getBorn() == null)
                    return 0;
                return o1.getBorn().compareTo(o2.getBorn());
            }
        });

        return new ResponseEntity<List<AuthorDTO>>(authorDTOs , HttpStatus.OK);
    }
}
