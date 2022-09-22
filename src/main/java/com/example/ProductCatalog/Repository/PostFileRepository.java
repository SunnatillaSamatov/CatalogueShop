package com.example.ProductCatalog.Repository;

import com.example.ProductCatalog.entity.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFileRepository extends JpaRepository<PostFile,Integer> {
}
