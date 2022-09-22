package com.example.ProductCatalog.Controller;

import com.example.ProductCatalog.Payload.ReqPost;
import com.example.ProductCatalog.Payload.Response;
import com.example.ProductCatalog.Service.FileContentService;
import com.example.ProductCatalog.Service.PostService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("post")
public class PostController {

    private final PostService postService;
    private final FileContentService fileContentService;

    public PostController(PostService postService, FileContentService fileContentService) {
        this.postService = postService;
        this.fileContentService = fileContentService;
    }

/*
    @PostMapping("/saveupdate")
    public HttpEntity<?> savePost( MultipartHttpServletRequest request) {
        String file = request.getParameter("file");
        request.getParameter("text");
        Response response = postService.saveUpdatePost();
        return ResponseEntity.ok(response);
    }

*/
    @PostMapping("/save")
    public HttpEntity<?> savePost(@RequestBody ReqPost reqPost, @RequestParam(required = false) MultipartFile multipartFile) {
        Response response = postService.deletePost(reqPost);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public HttpEntity<?> deletePost(@RequestBody ReqPost reqPost) {
        Response response = postService.deletePost(reqPost);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getall")
    public HttpEntity<?> getAllPost() {
        Response response = postService.getAllData();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/download")
    public HttpEntity<?> downloadPost(@RequestParam Integer PostId, @RequestParam Integer ProductUserId) {
        Response response = postService.writePostDownload(PostId,ProductUserId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/share")
    public HttpEntity<?> sharePost(@RequestParam Integer PostId, @RequestParam Integer ProductUserId) {
        Response response = postService.writePostShare(PostId,ProductUserId);
        return ResponseEntity.ok(response);
    }


}
