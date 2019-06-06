package com.books.addict.service.readService;

import com.books.addict.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReaderServiceRImpl implements ReaderServiceR {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private OrderServiceR orderServiceR;
    @Autowired
    private BookServiceR bookServiceR;

    @Override
    public List<Reader> getAllReaders() {
        return (List<Reader>) readerRepository.findAll();
    }

    @Override
    public List<Reader> getAllReaderForBook(Book book) {
        List<Order> orders= orderServiceR.getAllOrders();
        orders=orders.stream().filter(order -> order.getIdBook().equals(book.getId())).collect(Collectors.toList());
        List<Reader> readers = null;
        for(Order order:orders){
            readers.add(readerRepository.findReaderByUsername(order.getReader()));
        }
        return readers;
    }

    @Override
    public Optional<Reader> getReaderbyId(Integer id) {
        return readerRepository.findById(id);
    }



    @Override
    public boolean checkValidity(String username, String password) {
        if(readerRepository.findReaderByUsername(username)!=null && readerRepository.findReaderByUsername(username).getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public Reader findByUsername(String username) {
        return readerRepository.findReaderByUsername(username);
    }
}
