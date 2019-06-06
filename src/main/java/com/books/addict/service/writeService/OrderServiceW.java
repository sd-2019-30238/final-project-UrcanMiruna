package com.books.addict.service.writeService;

import com.books.addict.model.Order;

import java.util.Optional;

public interface OrderServiceW {
    void addorder(Order order);
    void deleteOrder(Order order);
    void updateOrder(Order order);
    void validateOrder(Order order);

}
