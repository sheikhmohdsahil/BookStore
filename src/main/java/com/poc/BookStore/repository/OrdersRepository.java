package com.poc.BookStore.repository;

import com.poc.BookStore.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "SELECT * FROM Orders WHERE order_date >= :dateFrom AND order_date < DATE_FORMAT(CURDATE(), '%y-%m-%d')", nativeQuery = true)
    List<Orders> findByDate(String dateFrom);

//    @Query(value="select * from Orders  where order_date =:dateFrom",nativeQuery=true)
//    List<Orders> findByDate(String dateFrom);

}
