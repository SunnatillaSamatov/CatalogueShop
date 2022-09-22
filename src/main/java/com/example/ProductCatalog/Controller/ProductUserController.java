package com.example.ProductCatalog.Controller;

import com.example.ProductCatalog.Payload.ReqProductUser;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Service.ProductUserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("productuser")
public class ProductUserController {

    private final ProductUserService productUserService;

    public ProductUserController(ProductUserService productUserService) {
        this.productUserService = productUserService;
    }

    @PostMapping("/saveupdate")
    public HttpEntity<?> saveUpdateProductUser(@RequestBody ReqProductUser reqProductUser) {
        Response response = productUserService.saveUpdateProductUser(reqProductUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public HttpEntity<?> deleteProductUser(@RequestBody ReqProductUser reqProductUser) {
        Response response = productUserService.deleteProductUser(reqProductUser);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getall")
    public HttpEntity<?> getProductUser() {
        Response response = productUserService.getAllData();
        return ResponseEntity.ok(response);
    }

}
