package com.poc.BookStore.repository;

import com.poc.BookStore.entity.Book;
import com.poc.BookStore.entity.BooksCategoriesXref;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCatXrefRepository extends JpaRepository<BooksCategoriesXref ,Integer> {

   @Query("select o.book from  BooksCategoriesXref o where o.categories.catName=:category")
    List<Book> findAllBookers(Pageable pageable,String category);
}
