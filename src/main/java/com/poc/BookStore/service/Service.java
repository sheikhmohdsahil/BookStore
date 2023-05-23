package com.poc.BookStore.service;

import com.poc.BookStore.Dto.*;
import com.poc.BookStore.entity.Book;
import com.poc.BookStore.entity.Orders;
import com.poc.BookStore.entity.User;

import java.util.Date;
import java.util.List;

public interface Service {

    public void addBooks(BookDto bookDto);

    void addCategoriesBooks(BookCatDto bookCatDto);

    void addingBooksCatXref(BookCatXrefDto bookCatXrefDto) throws Exception;

    void addUsers(UserDto userDto);

    void addingUserBooks(UserBookxrefDto userBookxrefDto) throws Exception;

    List<User> getAllUsers(String bookName);

    List<Book> getAllBooks(String category,int pageNumber,int pageSize);

    List<Orders> findByDate(String dateFrom);

    void addOrders(OrderDto orderDto) throws Exception;

    void addingToCart(CartDto cartDto) throws Exception;
}
