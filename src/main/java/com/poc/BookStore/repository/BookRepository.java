package com.poc.BookStore.repository;

import com.poc.BookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

   @Query("select o.title from Book o where o.title =:bookName")
    Optional<Book> findByName(String bookName);
}
