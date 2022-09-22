package com.example.ProductCatalog.Controller;

import com.example.ProductCatalog.Payload.ReqRole;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Service.RoleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/saveUpdate")
    public HttpEntity<?> saveUpdateRole(@RequestBody ReqRole reqRole) {
        Response response = roleService.saveUpdateRole(reqRole);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public HttpEntity<?> deleteRole(@RequestBody ReqRole reqRole) {
        Response response = roleService.deleteRole(reqRole);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public HttpEntity<?> getRole(@RequestBody ReqRole reqRole) {
        Response response = roleService.getRoleAllOrById(reqRole);
        return ResponseEntity.ok(response);
    }

}
