package com.test.trainee.controller;

import com.test.trainee.domain.AuthorDTO;
import com.test.trainee.domain.BookDTO;
import com.test.trainee.entity.Book;
import com.test.trainee.service.AuthorService;
import com.test.trainee.service.BookService;
import com.test.trainee.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ObjectMapperUtils modelMapper;


    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> bookDTOs = bookService.findAllBooks();
        return new ResponseEntity<List<BookDTO>>(bookDTOs ,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int id){
        BookDTO bookDTO = bookService.findBookById(id);

        if (bookDTO != null){
            bookService.deleteBook(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createBook(@RequestBody BookDTO bookDTO){
        bookService.saveBook(bookDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @PutMapping("/add-to-author/{authorId}")
    public ResponseEntity<Void> createBookToAuthor(@RequestBody BookDTO bookDTO,
                                                   @PathVariable("authorId") int id){

        AuthorDTO authorDTO = authorService.findAuthorById(id);
        Book book = modelMapper.map(bookDTO , Book.class);
        authorDTO.getBooks().add(book);

        authorService.saveAuthor(authorDTO);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }



    @PutMapping("/edit/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") int id,
                                           @RequestBody BookDTO bookDTO){
        BookDTO bookDTOFromDB = bookService.findBookById(id);

        if (bookDTOFromDB != null){
            bookDTO.setId(id);
            bookService.saveBook(bookDTO);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") int id){
        BookDTO bookDTO = bookService.findBookById(id);
        return new ResponseEntity<BookDTO>(bookDTO , HttpStatus.OK);
    }

    @GetMapping("/calculate-books-by{genre}")
    public ResponseEntity<Integer> calculateBookByGenre(@RequestParam(value = "genre" , required = false) String genre){
        List<BookDTO> bookDTOs = bookService.getBooksByGenre(genre);
        return new ResponseEntity<Integer>(bookDTOs.size() , HttpStatus.OK);
    }

    @GetMapping("/more-than-one-book-author")
    public ResponseEntity<List<BookDTO>> moreThanOneBookAuthor(){

        List<Integer> booksByAuthors = bookService.findBooksByAuthors();

        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Integer b : booksByAuthors){
            bookDTOs.add(bookService.findBookById(b));
        }

        return new ResponseEntity<List<BookDTO>>(bookDTOs, HttpStatus.OK);
    }
}
