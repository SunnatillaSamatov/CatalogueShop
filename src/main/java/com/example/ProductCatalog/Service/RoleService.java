package com.example.ProductCatalog.Service;

import com.example.ProductCatalog.Payload.ReqRole;
import com.example.ProductCatalog.Payload.Response;

public interface RoleService {
    Response saveUpdateRole(ReqRole reqRole);
    Response deleteRole(ReqRole reqRole);
    Response getRoleAllOrById(ReqRole reqRole);

}
