package com.example.ProductCatalog.Repository;

import com.example.ProductCatalog.entity.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileContentRepository extends JpaRepository<FileContent,Integer> {
}
