package com.example.ProductCatalog.Service.ServiceImpl;

import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Payload.Status;
import com.example.ProductCatalog.Repository.PostFileRepository;
import com.example.ProductCatalog.Repository.PostRepository;
import com.example.ProductCatalog.Service.PostFileService;
import com.example.ProductCatalog.entity.Post;
import com.example.ProductCatalog.entity.PostFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostFileServiceImpl implements PostFileService {
    private final PostFileRepository postFileRepository;
    private final PostRepository postRepository;
    private final JdbcTemplate jdbcTemplate;

    public PostFileServiceImpl(PostFileRepository postFileRepository, PostRepository postRepository, JdbcTemplate jdbcTemplate) {
        this.postFileRepository = postFileRepository;
        this.postRepository = postRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Response savePostFile(MultipartFile file, String Type, Integer postId) {
        Response response = new Response();
        PostFile postFile = new PostFile();
        Optional<Post> byId = postRepository.findById(postId);
        if(byId.isPresent()){
            postFile.setFileType(Type);
            postFile.setFileSize(file.getSize());
            postFile.setPost(byId.get());
            postFileRepository.save(postFile);
            response.setStatus(new Status(0, "progress"));
        }else{
            response.setStatus(new Status(404, "not found"));
        }

        return response;
    }

    @Override
    public Response updatePostFile(MultipartFile file, String Type, Integer postId, Integer postFileId) {
        Response response = new Response();
        PostFile postFile = new PostFile();
        Optional<Post> byId1 = postRepository.findById(postId);
        if(byId1.isPresent()){
            Optional<PostFile> byId = postFileRepository.findById(postFileId);
            if(byId.isPresent()){
                postFile=byId.get();
                postFile.setFileType(Type);
                postFile.setFileSize(file.getSize());
                postFile.setPost(byId1.get());
                postFileRepository.save(postFile);
                response.setStatus(new Status(0, "Success"));
            }else {
                response.setStatus(new Status(404, "PostFile not found"));
            }
        } else{
            response.setStatus(new Status(404, "Post not found"));
        }

        return response;
    }

    public Response deletePostFile(Integer postFileId) {
        Response response = new Response();
            Optional<PostFile> byId = postFileRepository.findById(postFileId);
            if(byId.isPresent()){
                try{
                    postFileRepository.deleteById(postFileId);
                    response.setStatus(new Status(0,"success"));
                }catch (Exception e){
                    response.setStatus(new Status(1,"object is related with another object"));
                }

            }else{
                response.setStatus(new Status(404,"Object with this id is not present"));
            }

        return response;
    }

    @Override
    public Response getAllPostFile() {
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from post_file");
        response.setData(maps);
        response.setStatus(new Status(0, "success"));
        return response;

    }


}
