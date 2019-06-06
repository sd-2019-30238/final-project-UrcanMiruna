package com.books.addict.service.readService;

import com.books.addict.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AdminServiceRImpl implements AdminServiceR {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();

    }

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }


}
