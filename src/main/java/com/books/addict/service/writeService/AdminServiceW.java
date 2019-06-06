package com.books.addict.service.writeService;

import com.books.addict.model.Admin;
import com.books.addict.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServiceW {
    void addAdmin(Admin admin);
    void updateAdmin(Admin admin);
}
