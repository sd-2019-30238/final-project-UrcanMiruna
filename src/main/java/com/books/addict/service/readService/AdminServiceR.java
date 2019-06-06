package com.books.addict.service.readService;

import com.books.addict.model.Admin;
import com.books.addict.model.Order;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.List;

@Service
public interface AdminServiceR {
    List<Admin> getAllAdmins();
    Admin findByUsername(String username);
}
