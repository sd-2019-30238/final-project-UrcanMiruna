package com.books.addict;

import com.books.addict.model.Admin;
import com.books.addict.service.AdminService;
import com.books.addict.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AddictApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddictApplication.class, args);

	}

}
