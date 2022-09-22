package com.example.ProductCatalog.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqEmployee {

    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private List<Integer> role_id;

}
