package com.example.ProductCatalog.Service.ServiceImpl;

import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Payload.Status;
import com.example.ProductCatalog.Repository.FileContentRepository;
import com.example.ProductCatalog.Repository.PostFileRepository;
import com.example.ProductCatalog.Service.FileContentService;
import com.example.ProductCatalog.entity.FileContent;
import com.example.ProductCatalog.entity.PostFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileContentServiceImpl implements FileContentService {
    private final FileContentRepository fileContentRepository;
    private final JdbcTemplate jdbcTemplate;
    private  final PostFileRepository postFileRepository;


    public FileContentServiceImpl(FileContentRepository fileContentRepository, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, PostFileRepository postFileRepository) {
        this.fileContentRepository = fileContentRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.postFileRepository = postFileRepository;
    }

    public Response saveFileContent(MultipartFile file, Integer postFileId) {
        Response response = new Response();
        Optional<PostFile> byId = postFileRepository.findById(postFileId);
        if(byId.isPresent()){
            try {
                FileContent fileContent = new FileContent(file.getBytes());
                fileContent.setPostFile(byId.get());
                fileContentRepository.save(fileContent);
                response.setStatus(new Status(0,"ok"));
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(new Status(1,"exception "));
            }

        }else{
            response.setStatus(new Status(404,"not found"));
        }
        return response;
    }

    public Response deleteFileContent(Integer postFileId) {
        Response response =new Response();

        if(postFileId!=null){

                /*
                String sql = "select id from file_content where post_file_id = '" + postFileId + "'";
                Integer fileContentId = jdbcTemplate.queryForObject(sql, Integer.class);
                fileContentRepository.deleteById(fileContentId);
                */
                String sql = "select id from file_content where post_id = ?";
                Integer fileContentId= jdbcTemplate.queryForObject(sql, new Object[]{postFileId}, Integer.class);
                fileContentRepository.deleteById(fileContentId);
                response.setStatus(new Status(0,"deleted"));
            }else{
                response.setStatus(new Status(404,"postfile is not found"));
            }


        return response;
    }


    public Response getFileFromDb(Integer postFileId) {
        Response response = new Response();
                String sql = "select id from file_content where post_id = ?";
                Integer fileContentId= jdbcTemplate.queryForObject(sql, new Object[]{postFileId}, Integer.class);
                Optional<FileContent> byId = fileContentRepository.findById(fileContentId);
                response.setData(byId);
                response.setStatus(new Status(0, "success"));


        return response;
    }


}
