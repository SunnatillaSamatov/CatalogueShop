package com.example.ProductCatalog.Service.ServiceImpl;

import com.example.ProductCatalog.Payload.ReqProductUser;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Payload.Status;
import com.example.ProductCatalog.Repository.ProductUserRepository;
import com.example.ProductCatalog.Service.ProductUserService;
import com.example.ProductCatalog.entity.ProductUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductUserServiceImpl implements ProductUserService {
    private final ProductUserRepository productUserRepository;
    private final JdbcTemplate jdbcTemplate;

    public ProductUserServiceImpl(ProductUserRepository productUserRepository, JdbcTemplate jdbcTemplate) {
        this.productUserRepository = productUserRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Response saveUpdateProductUser(ReqProductUser reqProductUser) {
        Response response = new Response();
        ProductUser productUser = new ProductUser();
        if (reqProductUser.getId() != null) {
            Optional<ProductUser> byId = productUserRepository.findById(reqProductUser.getId());
            if (byId.isPresent()) {
                productUser = byId.get();
            } else {
                response.setStatus(new Status(404, "not found"));
                return response;
            }
        }
        productUser.setName(reqProductUser.getName());
        productUser.setSurname(reqProductUser.getSurname());
        productUserRepository.save(productUser);
        response.setStatus(new Status(0, "completed"));
        return response;
    }


    public Response deleteProductUser(ReqProductUser reqProductUser) {
        Response response = new Response();
        if (reqProductUser.getId() != null) {
            Optional<ProductUser> byId = productUserRepository.findById(reqProductUser.getId());
            if (byId.isPresent()) {
                productUserRepository.deleteById(reqProductUser.getId());
                response.setStatus(new Status(0, "deleted"));
            } else {
                response.setStatus(new Status(404, "id in not found"));
            }
        } else {
            response.setStatus(new Status(1, "id is null"));
        }

        return response;
    }


    public Response getAllData() {
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from product_user ");
        response.setData(maps);
        response.setStatus(new Status(0, "all data"));
        return response;
    }
}
