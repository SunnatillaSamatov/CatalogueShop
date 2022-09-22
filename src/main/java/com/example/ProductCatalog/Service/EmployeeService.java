package com.example.ProductCatalog.Service;

import com.example.ProductCatalog.Payload.ReqEmployee;
import com.example.ProductCatalog.Payload.Response;

public interface EmployeeService {
    Response saveUpdateEmployee(ReqEmployee reqEmployee);
    Response deleteEmployee(Integer id);
    Response getAllEmployee();

}
