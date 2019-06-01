package com.books.addict.service;

import com.books.addict.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AdminServiceImpl  implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();

    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

  /*  @Override
    public void validateOrder(Order order) {
        if(order.getState().equalsIgnoreCase("delivering")){
            order.register(this);
            order.setState("paid");
            orderRepository.save(order);
        }
    }

    @Override
    public void update(Order order) {
    }*/
}
