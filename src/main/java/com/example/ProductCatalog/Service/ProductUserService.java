package com.example.ProductCatalog.Service;

import com.example.ProductCatalog.Payload.ReqProductUser;
import com.example.ProductCatalog.Payload.Response;

public interface ProductUserService {

    Response saveUpdateProductUser(ReqProductUser reqProductUser);
    Response deleteProductUser(ReqProductUser reqProductUser);
    Response getAllData();
}
