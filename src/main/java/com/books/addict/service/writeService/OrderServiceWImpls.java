package com.books.addict.service.writeService;

import com.books.addict.model.Book;
import com.books.addict.model.Observer;
import com.books.addict.model.Order;
import com.books.addict.model.OrderRepository;
import com.books.addict.service.readService.BookServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceWImpls extends Observer implements OrderServiceW {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookServiceW bookServiceW;

    @Autowired
    private BookServiceR bookServiceR;



    @Override
    public void addorder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }



    @Override
    public void validateOrder(Order order) {
        if(order.getState().equalsIgnoreCase("delivering")){
            order.setState("paid");
            order.register(this);
            this.updateOrder(order);
        }
    }

    @Override
    public void update(Order order) {
        Optional<Book> book = bookServiceR.getBookById(order.getIdBook());
        System.out.println("The payment the reader "+ order.getReader() +" for the book "+ book.get().getName() );
    }
}
