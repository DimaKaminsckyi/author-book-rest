package com.test.trainee.repository;

import com.test.trainee.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book , Integer> {

    List<Book> getBooksByGenre(String genre);

    @Query(nativeQuery = true ,
            value = "SELECT book_id FROM book_author WHERE author_id in (SELECT author_id FROM book_author GROUP BY author_id HAVING count(*) > 1) ;")
    List<Integer> findBooksByAuthors();
}
