package com.example.ProductCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class ProductCatalog {
    public static void main(String[] args) {
        SpringApplication.run(ProductCatalog.class, args);
    }

}
