package com.example.ProductCatalog.Service.ServiceImpl;

import com.example.ProductCatalog.Payload.ReqPost;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Payload.Status;
import com.example.ProductCatalog.Repository.EmployeeRepository;
import com.example.ProductCatalog.Repository.PostRepository;
import com.example.ProductCatalog.Repository.ProductUserRepository;
import com.example.ProductCatalog.Service.PostService;
import com.example.ProductCatalog.entity.Employee;
import com.example.ProductCatalog.entity.Post;
import com.example.ProductCatalog.entity.ProductUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final EmployeeRepository employeeRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ProductUserRepository productUserRepository;


    public PostServiceImpl(PostRepository postRepository, EmployeeRepository employeeRepository, JdbcTemplate jdbcTemplate, ProductUserRepository productUserRepository) {
        this.postRepository = postRepository;

        this.employeeRepository = employeeRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.productUserRepository = productUserRepository;
    }


    public Response saveUpdatePost(ReqPost reqPost) {
        Response response = new Response();
        Post post = new Post();
        if (reqPost.getCreatedEmployeeId() != null) {
            Optional<Employee> byId = employeeRepository.findById(reqPost.getCreatedEmployeeId());
            if (byId.isPresent()) {
                if (reqPost.getId() != null) {
                    Optional<Post> byId1 = postRepository.findById(reqPost.getId());
                    if (byId1.isPresent()) {
                        post = byId1.get();
                    } else {
                        response.setStatus(new Status(404, "post is not found with this id"));
                    }
                }
                post.setEmployee(byId.get());
                post.setText(reqPost.getText());
                post.setCreatedDate(reqPost.getCreatedDate());
                post.setUpdatedDate(reqPost.getUpdatedDate());
                post.setUpdatedEmployeeId(reqPost.getUpdatedEmployeeId());
                post.setCreatedDate(reqPost.getCreatedDate());
                List<ProductUser> allById1 = productUserRepository.findAllById(reqPost.getProductUserDownloadsId());
                post.setProductUserDownloads(allById1);
                List<ProductUser> allById2 = productUserRepository.findAllById(reqPost.getProductUserSharesId());
                post.setProductUserShares(allById2);
                postRepository.save(post);

                response.setStatus(new Status(0, "ok"));

            } else {
                response.setStatus(new Status(404, "employee is not found"));
                return response;
            }
        } else {
            response.setStatus(new Status(404, " created employee is null"));
            return response;
        }
        return response;
    }

    public Response deletePost(ReqPost reqPost) {
        Response response = new Response();
        Optional<Post> byId = postRepository.findById(reqPost.getId());
        if(byId.isPresent()){
            postRepository.deleteById(reqPost.getId());
            response.setStatus(new Status(0,"deleted"));
        }else{
            response.setStatus(new Status(1,"object is related with another object"));
        }
        return response;
    }

    public Response getAllData() {
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from post");
        response.setData(maps);
        response.setStatus(new Status(0,"success"));

        return response;
    }

    public Response writePostDownload(Integer PostId, Integer ProductUserId) {
        Response response = new Response();
        Optional<Post> byId = postRepository.findById(PostId);
        if(byId.isPresent()){
            Post post = byId.get();
            Optional<ProductUser> productUser = productUserRepository.findById(ProductUserId);
            if(productUser.isPresent()){
                ProductUser productUser1 = productUser.get();
                List<ProductUser> productUserDownloads = post.getProductUserDownloads();
                if(!productUserDownloads.contains(productUser1)){
                    productUserDownloads.add(productUser1);
                    post.setProductUserDownloads(productUserDownloads);
                    postRepository.save(post);
                    response.setStatus(new Status(0, "Success"));
                }else{
                    response.setStatus(new Status(1, "user allready downloaded"));
                }
            }else{
                response.setStatus(new Status(1, "user is not present"));
            }
        }else {
            response.setStatus(new Status(1, "post is not present"));
        }


        return response;
    }

    public Response writePostShare(Integer PostId, Integer ProductUserId) {
        Response response = new Response();
        Optional<Post> byId = postRepository.findById(PostId);
        Optional<ProductUser> productUser = productUserRepository.findById(ProductUserId);
        Post post = byId.get();
        ProductUser productUser1 = productUser.get();
        List<ProductUser> productUserShares = post.getProductUserShares();
        productUserShares.add(productUser1);
        post.setProductUserShares(productUserShares);
        postRepository.save(post);
        response.setStatus(new Status(0, "Success"));
        return response;
    }



}
