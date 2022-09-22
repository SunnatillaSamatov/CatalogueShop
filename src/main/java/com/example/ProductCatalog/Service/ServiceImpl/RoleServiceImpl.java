package com.example.ProductCatalog.Service.ServiceImpl;

import com.example.ProductCatalog.Payload.ReqRole;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Payload.Status;
import com.example.ProductCatalog.Repository.EmployeeRepository;
import com.example.ProductCatalog.Repository.RoleRepository;
import com.example.ProductCatalog.Service.RoleService;
import com.example.ProductCatalog.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;

    public RoleServiceImpl(RoleRepository roleRepository, EmployeeRepository employeeRepository) {
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
    }

    public Response saveUpdateRole(ReqRole reqRole) {
        Response response = new Response();
        Role role = new Role();
        if (reqRole.getId() != null) {
            Optional<Role> byId = roleRepository.findById(reqRole.getId());
            if (byId.isPresent()) {
                role = byId.get();
            } else {
                response.setStatus(new Status(404, "Role is not found"));
                return response;
            }
        }
        role.setPosition(reqRole.getPosition());

        roleRepository.save(role);
        response.setStatus(new Status(0, "Success"));

        return response;
    }


    public Response deleteRole(ReqRole reqRole) {
        Response response = new Response();
        if (reqRole.getId() != null) {
            Optional<Role> byId = roleRepository.findById(reqRole.getId());
            if (byId.isPresent()) {
                try {
                    roleRepository.deleteById(reqRole.getId());
                    response.setStatus(new Status(0, "deleted"));
                } catch (Exception e) {
                    response.setStatus(new Status(404, "Item is not present with a given id"));
                }
            }
        } else {
            response.setStatus(new Status(404, "Role id is null"));
        }
        return response;
    }


    public Response getRoleAllOrById(ReqRole reqRole) {
        Response response = new Response();
        if(reqRole.getId()!=null){
            Optional<Role> byId = roleRepository.findById(reqRole.getId());
            if(byId.isPresent()){

                response.setData(byId.get());
                response.setStatus(new Status(0,"all data of given id"));
            }else{
                response.setStatus(new Status(0,"role is not present with this id"));
            }

        }else{
            List<Role> all = roleRepository.findAll();
            response.setData(all);
            response.setStatus(new Status(0,"all data"));
        }

        return response;
    }
}
