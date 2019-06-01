package com.books.addict.service;

import com.books.addict.model.Admin;
import com.books.addict.model.Order;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.List;

@Service
public interface AdminService {
    List<Admin> getAllAdmins();
    void addAdmin(Admin admin);
    void updateAdmin(Admin admin);
    Admin findByUsername(String username);
   // void validateOrder(Order order);
}
