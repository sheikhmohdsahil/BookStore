package com.poc.BookStore.controller;

import com.poc.BookStore.Dto.*;
import com.poc.BookStore.entity.Book;
import com.poc.BookStore.entity.Categories;
import com.poc.BookStore.entity.Orders;
import com.poc.BookStore.entity.User;
import com.poc.BookStore.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private Service service;

    @PostMapping("/add/books")
    public String addBooks(@RequestBody  BookDto bookDto){
        service.addBooks(bookDto);
        return "books added successfully ";
    }

    @PostMapping("/add/books/categories")
    public String addCatBooks(@RequestBody BookCatDto bookCatDto){
        service.addCategoriesBooks(bookCatDto);
        return "books with categories added successfully ";
    }

    @PostMapping("/add/books/categories/xref")
    public String addCatBooks(@RequestBody BookCatXrefDto bookCatXrefDto) throws Exception {
        service.addingBooksCatXref(bookCatXrefDto);
        return "books xref categories added successfully ";
    }


    @PostMapping("/add/users")
    public String addUsers(@RequestBody UserDto userDto) throws Exception {
        service.addUsers(userDto);
        return "user added successfully ";
    }

    @PostMapping("/add/users/books")
    public String addingUserBook(@RequestBody UserBookxrefDto userBookxrefDto) throws Exception {
        service.addingUserBooks(userBookxrefDto);
        return "user xref added successfully ";
    }

    @GetMapping("/get/all/userinfo")
    public List<User> getAllUser(@RequestParam String bookName){
       return service.getAllUsers(bookName);
    }

    @GetMapping("/get/all/books")
    public List<Book> getAllBooks(@RequestParam String category,@RequestParam int pageNumber,@RequestParam int pageSize){
        return service.getAllBooks(category,pageNumber,pageSize);
    }



    @PostMapping("/add/orders")
    public String addOrders(@RequestBody OrderDto orderDto) throws Exception {
        service.addOrders(orderDto);
        return "order added successfully";
    }

    @GetMapping("get/all/info")
    public List<Orders> getAllInfo(@RequestParam String dateFrom){
        return service.findByDate(dateFrom);
    }

    @PostMapping("/add/to/cart")
    public ResponseEntity<Object> addToCart(@RequestBody CartDto cartDto) throws Exception {
        service.addingToCart(cartDto);
        return new ResponseEntity<>("added to cart successfully",HttpStatus.OK);
    }
}
