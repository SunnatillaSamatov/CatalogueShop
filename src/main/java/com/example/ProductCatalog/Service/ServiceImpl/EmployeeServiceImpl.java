package com.example.ProductCatalog.Service.ServiceImpl;

import com.example.ProductCatalog.Payload.ReqEmployee;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Payload.Status;
import com.example.ProductCatalog.Repository.EmployeeRepository;
import com.example.ProductCatalog.Repository.RoleRepository;
import com.example.ProductCatalog.Service.EmployeeService;
import com.example.ProductCatalog.entity.Employee;
import com.example.ProductCatalog.entity.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final JdbcTemplate jdbcTemplate;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository, JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Response saveUpdateEmployee(ReqEmployee reqEmployee) {
        Response response = new Response();
        Employee employee = new Employee();
        if (reqEmployee.getId() != null) {
            Optional<Employee> byId = employeeRepository.findById(reqEmployee.getId());
            if (byId.isPresent()) {
                employee = byId.get();
            } else {
                response.setStatus(new Status(404, "Employee is not found"));
                return response;
            }
        }
        employee.setName(reqEmployee.getName());
        employee.setSurname(reqEmployee.getSurname());
        List<Role> allById = roleRepository.findAllById(reqEmployee.getRole_id());
        employee.setRoles(allById);
        employeeRepository.save(employee);
        response.setStatus(new Status(0, "Success"));

        return response;
    }


    public Response deleteEmployee(Integer id) {
        Response response = new Response();
            Optional<Employee> byId = employeeRepository.findById(id);
            if (byId.isPresent()) {
                try {
                    employeeRepository.deleteById(id);
                    response.setStatus(new Status(0, "Progress Completed"));
                } catch (Exception e) {
                    response.setStatus(new Status(1, "This object any early related"));
                }
            }
            else {
            response.setStatus(new Status(404, "id is null"));
             }

        return response;
    }


    public Response getAllEmployee() {
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from employee");
        response.setData(maps);
        response.setStatus(new Status(0,"progress"));

        return response;
    }

}
