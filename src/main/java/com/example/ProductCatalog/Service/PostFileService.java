package com.example.ProductCatalog.Service;

import com.example.ProductCatalog.Payload.Response;
import org.springframework.web.multipart.MultipartFile;

public interface PostFileService {
    Response savePostFile(MultipartFile file, String Type, Integer postId);
    Response updatePostFile(MultipartFile file, String Type, Integer postId, Integer postFileId);
    Response deletePostFile(Integer postFileId);
    Response getAllPostFile();
}
