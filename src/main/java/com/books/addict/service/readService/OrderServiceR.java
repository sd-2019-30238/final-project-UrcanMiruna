package com.books.addict.service.readService;

import com.books.addict.model.Book;
import com.books.addict.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderServiceR {
    List<Order> getAllOrders();
    Float getTotalPrice(String username);
    Optional<Order> getOrderById(Integer id);
    List<Order> getOrderByReader(String username);
    List<Book> getOrdersByAuthors(String username);
}
