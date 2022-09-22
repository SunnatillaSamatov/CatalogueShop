package com.example.ProductCatalog.Service;

import com.example.ProductCatalog.Payload.ReqFileContent;
import com.example.ProductCatalog.Payload.Response;
import org.springframework.web.multipart.MultipartFile;

public interface FileContentService {

    Response saveFileContent(MultipartFile file, Integer postFileId);
    Response deleteFileContent(Integer postFileId);
    Response getFileFromDb(Integer postFileId);

}
