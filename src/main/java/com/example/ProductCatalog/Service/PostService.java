package com.example.ProductCatalog.Service;

import com.example.ProductCatalog.Payload.ReqPost;
import com.example.ProductCatalog.Payload.Response;



public interface PostService {
    Response saveUpdatePost(ReqPost reqPost);
    Response deletePost(ReqPost reqPost);
    Response getAllData();
    Response writePostDownload(Integer PostId, Integer ProductUserId);
    Response writePostShare(Integer PostId, Integer ProductUserId);

}
