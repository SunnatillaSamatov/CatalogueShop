package com.example.ProductCatalog.Controller;

import com.example.ProductCatalog.Payload.ReqFileContent;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Service.FileContentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("fileContent")
public class FileContentController {
    private final FileContentService fileContentService;

    public FileContentController(FileContentService fileContentService) {
        this.fileContentService = fileContentService;
    }

    @PostMapping("/save")
    public HttpEntity<?> saveFileContent(@RequestParam MultipartFile file, @RequestParam Integer postFileId) {

        Response response = fileContentService.saveFileContent(file,postFileId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public HttpEntity<?> deleteFileContent(@RequestParam Integer postFileId) {
        Response response = fileContentService.deleteFileContent(postFileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getFile")
    public HttpEntity<?> getFileFromDB(@RequestParam Integer postFileId){
        Response response = fileContentService.getFileFromDb(postFileId);
        return ResponseEntity.ok(response);
    }

}
