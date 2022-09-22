package com.example.ProductCatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte[] bytes;

    @OneToOne
    private PostFile postFile;

    public FileContent(byte[] bytes){
        this.bytes=bytes;

    }


}
