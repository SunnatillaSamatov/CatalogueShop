package com.example.ProductCatalog.Repository;

import com.example.ProductCatalog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PostRepository extends JpaRepository<Post, Integer> {
}
