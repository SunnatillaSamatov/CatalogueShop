package com.example.ProductCatalog.Payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReqPost {

    private Integer id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;

    @NotBlank
    private String text;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updatedDate;

    private Integer updatedEmployeeId;

    @NotBlank
    private Integer createdEmployeeId;

    private List<Integer> filePostId;

    private List<Integer> productUserSharesId;

    private List<Integer> productUserDownloadsId;


}
