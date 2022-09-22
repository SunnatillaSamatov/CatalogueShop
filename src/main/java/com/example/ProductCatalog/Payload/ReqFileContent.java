package com.example.ProductCatalog.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReqFileContent {

    private Integer id;

    @NotBlank
    private String postFileId;

    private Byte[] bytes;



}
