package com.books.addict.service;

import com.books.addict.model.Book;
import com.books.addict.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    void addorder(Order  order);
    void deleteOrder(Order order);
    void updateOrder(Order order);
    Float getTotalPrice(String username);
    void validateOrder(Order order);
    Optional<Order> getOrderById(Integer id);
    List<Order> getOrderByReader(String username);
    List<Book> getOrdersByAuthors(String username);
}
