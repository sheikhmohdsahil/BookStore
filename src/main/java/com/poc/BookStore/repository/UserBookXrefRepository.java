package com.poc.BookStore.repository;

import com.poc.BookStore.entity.User;
import com.poc.BookStore.entity.UserBooksXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBookXrefRepository extends JpaRepository<UserBooksXref,Integer> {


    @Query("select o.user from UserBooksXref o where o.bookName=:bookName")
    List<User> findAllUser(String bookName);
}
