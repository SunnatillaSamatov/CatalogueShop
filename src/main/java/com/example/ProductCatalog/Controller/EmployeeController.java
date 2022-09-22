package com.example.ProductCatalog.Controller;

import com.example.ProductCatalog.Payload.ReqEmployee;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Service.EmployeeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/saveUpdate")
    public HttpEntity<?> saveUpdateEmployee(@RequestBody ReqEmployee reqEmployee) {
        Response response = employeeService.saveUpdateEmployee(reqEmployee);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public HttpEntity<?> saveUpdateEmployee(@RequestParam Integer id) {
        Response response = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getalldata")
    public HttpEntity<?> getEmployee() {
        Response response = employeeService.getAllEmployee();
        return ResponseEntity.ok(response);
    }


}
