package com.example.ProductCatalog.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqProductUser {

    private Integer id;

    @NotBlank
    private String name;
    @NotBlank
    private String surname;


}
