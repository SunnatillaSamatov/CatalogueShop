package com.example.ProductCatalog.Repository;

import com.example.ProductCatalog.entity.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUserRepository extends JpaRepository<ProductUser,Integer> {

}
