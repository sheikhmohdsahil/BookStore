package com.poc.BookStore.service;

import com.poc.BookStore.Dto.*;
import com.poc.BookStore.entity.*;
import com.poc.BookStore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{



    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserBookXrefRepository userBookXrefRepository;
    @Autowired
    private BookCatXrefRepository bookCatXrefRepository;
    @Autowired
    private  CategoriesRepository categoriesRepository;
    @Override
    public void addBooks(BookDto bookDto) {
    Book book= Book.builder()
            .title(bookDto.getTitle())
            .price(bookDto.getPrice())
            .authorName(bookDto.getAuthorName())
            .build();
        bookRepository.save(book);
    }

    @Override
    public void addCategoriesBooks(BookCatDto bookCatDto) {

        Book book= Book.builder()
                .title(bookCatDto.getTitle())
                .price(bookCatDto.getPrice())
                .authorName(bookCatDto.getAuthorName())
                .build();
        bookRepository.save(book);

        Categories categories=Categories.builder()
                        .catName(bookCatDto.getCatName()).build();

        categoriesRepository.save(categories);


    }

    @Override
    public void addingBooksCatXref(BookCatXrefDto bookCatXrefDto) throws Exception {


        BooksCategoriesXref booksCategoriesXref=new BooksCategoriesXref();

        // Retrieve the Employee
        Optional<Book> optionalBook = bookRepository.findById(bookCatXrefDto.getBookId());
        if (optionalBook.isPresent()) {
            booksCategoriesXref.setBook(optionalBook.get());
        } else {
            throw new Exception("Book not found for ID: " + bookCatXrefDto.getBookId());
        }

        // Retrieve the Company
        Optional<Categories> optionalCategories = categoriesRepository.findById(bookCatXrefDto.getCategoryId());
        if (optionalCategories.isPresent()) {
            booksCategoriesXref.setCategories(optionalCategories.get());
        } else {
            throw new Exception("category not found for ID: " + bookCatXrefDto.getCategoryId());
        }

        bookCatXrefRepository.save(booksCategoriesXref);
    }

    @Override
    public void addUsers(UserDto userDto) {
        User user= User.builder()
                .userName(userDto.getName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
        userRepository.save(user);
    }

    @Override
    public void addingUserBooks(UserBookxrefDto userBookxrefDto) throws Exception {
        UserBooksXref userBooksXref=new UserBooksXref();

        Optional<User> optionalUser = userRepository.findById(userBookxrefDto.getUserId());
        if (optionalUser.isPresent()) {
            userBooksXref.setUser(optionalUser.get());
        } else {
            throw new Exception("User not found for ID: " + userBookxrefDto.getUserId());
        }

        Optional<Book> optionalBook= bookRepository.findByName(userBookxrefDto.getBookName());
        if (optionalBook.isPresent()) {
            userBooksXref.setBookName(userBookxrefDto.getBookName());
        } else {
            throw new Exception("Book not found  " + userBookxrefDto.getBookName());
        }

        userBookXrefRepository.save(userBooksXref);

    }

    @Override
    public List<User> getAllUsers(String bookName) {
        List<User> users=userBookXrefRepository.findAllUser(bookName);
        return users;
    }

    @Override
    public List<Book> getAllBooks(String category,int pageNumber,int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return bookCatXrefRepository.findAllBookers(pageable,category);

    }

    @Override
    public List<Orders> findByDate(String dateFrom){
        List<Orders> orders =ordersRepository.findByDate(dateFrom);
        return orders;
    }

    @Override
    public void addOrders(OrderDto orderDto) throws Exception {
      Orders orders=new Orders();

        Optional<User> optionalUser = userRepository.findById(orderDto.getUserId());
        if (optionalUser.isPresent()) {
            orders.setUser(optionalUser.get());
        } else {
            throw new Exception("User not found for ID: " + orderDto.getUserId());
        }

        Optional<Book> optionalBook = bookRepository.findById(orderDto.getBookId());
        if (optionalBook.isPresent()) {
            orders.setBook(optionalBook.get());
        } else {
            throw new Exception("Book not found for ID: " + orderDto.getBookId());
        }
        orders.setBook(optionalBook.get());
        orders.setOrderDate(orderDto.getDate());
        ordersRepository.save(orders);

    }

    @Override
    public void addingToCart(CartDto cartDto) throws Exception {
        Optional<User> optionalUser = userRepository.findById(cartDto.getUserId());
        if (optionalUser.isPresent()) {

            Optional<Book> optionalBook = bookRepository.findById(cartDto.getBookId());
            if (optionalBook.isPresent()) {

                Cart cart=Cart.builder()
                        .user(optionalUser.get())
                        .book(optionalBook.get())
                        .quantity(cartDto.getQuantity())
                        .build();

                cartRepository.save(cart);
            } else {
                throw new Exception("Book not found for ID: " + cartDto.getBookId());
            }
        } else {
            throw new Exception("User not found for ID: " + cartDto.getUserId());
        }
    }
}

