package com.test.trainee.repository;

import com.test.trainee.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author , Integer> {

    @Query(value = "SELECT author_id FROM test.book_author GROUP BY author_id ORDER BY count(author_id) DESC limit 1;" , nativeQuery = true)
    int findAuthorMostBooks();

    @Query(value = "SELECT * FROM test.authors WHERE DATEDIFF(NOW() , born)/365 > ?;" , nativeQuery = true)
    List<Author> findOlderAuthorsByAge(int age);
}
