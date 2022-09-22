package com.example.ProductCatalog.Controller;

import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Service.PostFileService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("PostFile")
public class PostFileController {

    private final PostFileService postFileService;

    public PostFileController(PostFileService postFileService) {
        this.postFileService = postFileService;
    }

    @PostMapping("/save")
    public HttpEntity<?> savePost( @RequestParam MultipartFile multipartFile, @RequestParam String Type,@RequestParam Integer postId) {
        Response response = postFileService.savePostFile( multipartFile, Type, postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public HttpEntity<?> savePost( @RequestParam MultipartFile multipartFile, @RequestParam String Type,@RequestParam Integer postId, @RequestParam Integer postFileId) {
        Response response = postFileService.updatePostFile( multipartFile, Type, postId,postFileId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public HttpEntity<?> savePost(  @RequestParam Integer postFileId) {
        Response response = postFileService.deletePostFile( postFileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAllPostFile(){
        Response response = postFileService.getAllPostFile();
        return ResponseEntity.ok(response);
    }

}
