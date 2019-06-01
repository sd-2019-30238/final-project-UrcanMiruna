package com.books.addict.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository  extends CrudRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
