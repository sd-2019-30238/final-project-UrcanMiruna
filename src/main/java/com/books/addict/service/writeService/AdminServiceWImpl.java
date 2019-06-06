package com.books.addict.service.writeService;

import com.books.addict.model.Admin;
import com.books.addict.model.AdminRepository;
import com.books.addict.model.Order;
import com.books.addict.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AdminServiceWImpl implements AdminServiceW {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private OrderRepository orderRepository;



    @Override
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }


}
