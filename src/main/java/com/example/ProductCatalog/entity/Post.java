package com.example.ProductCatalog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    private String uploadedFileType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate;

    private Integer updatedEmployeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_employee_id")
    private Employee employee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "share", joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "product_user_id"))
    private List<ProductUser> productUserShares;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "downloads", joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "product_user_id"))
    private List<ProductUser> productUserDownloads;
/*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PostFilePost", joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "post_file_id"))
    private List<PostFile> postFiles;
*/

}
