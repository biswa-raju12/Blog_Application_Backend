package com.Applicatoin_Blog_demo.Controller;

import com.Applicatoin_Blog_demo.Entity.Post;
import com.Applicatoin_Blog_demo.Entity.User;
import com.Applicatoin_Blog_demo.Exceptions.ResourceNotFoundException;
import com.Applicatoin_Blog_demo.Payroads.ApiResponse;
import com.Applicatoin_Blog_demo.Payroads.PostDTO;
import com.Applicatoin_Blog_demo.Payroads.PostResponse;
import com.Applicatoin_Blog_demo.Payroads.UserDTO;
import com.Applicatoin_Blog_demo.Service.FIleService;
import com.Applicatoin_Blog_demo.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FIleService fIleService;

    @Value("${project.image}")
    private String path;

    //create

    @PostMapping("/user/{userId}/category/{categoryId}/Post")
    public ResponseEntity<PostDTO> createPost(
            @RequestBody PostDTO postDTO,
            @PathVariable("userId") Long userId,
            @PathVariable("categoryId") Long categoryId
    )
    {
//        Long userId = postDTO.getUser().getUserId();
//        Long categoryId= postDTO.getCategory().getCategoryId();

        PostDTO postDTO1= this.postService.createPost(postDTO,userId,categoryId);

        return new ResponseEntity<>(postDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/Post")
    public List<PostDTO> getPostByUser(@PathVariable("userId") Long userId)
    {
        List<PostDTO> postDTOS= this.postService.getPostByUser(userId);

        return postDTOS;

    }

    @GetMapping("/category/{categoryId}/Post")
    public  List<PostDTO> getPostByCategory(@PathVariable("categoryId") Long categoryId)
    {
        List<PostDTO> postDTOS= this.postService.getPostByCategory(categoryId);


        return postDTOS;
    }

    @GetMapping("/Post")
    public  PostResponse getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
             @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy)
    {

        PostResponse postDTOS= this.postService.getAllPosts(pageNumber,pageSize,sortBy);

        return postDTOS;

    }

    @GetMapping("/Post/{postId}")
    public  PostDTO getPostById(@PathVariable Long postId)
    {
        PostDTO postDTO= this.postService.getPostById(postId);

        return postDTO;

    }

    @DeleteMapping("/Post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Long postId) {

        this.postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully",true), HttpStatus.OK);

    }

    @PutMapping("/Post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable("postId") Long postId)
    {
        PostDTO postDTO1 = this.postService.updatePost(postDTO,postId);

        return new ResponseEntity<>(postDTO1,HttpStatus.OK);

    }

    @GetMapping("/Posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keywords") String keywords)
    {

        List<PostDTO> post = this.postService.searchPosts(keywords);

        return new ResponseEntity<>(post,HttpStatus.OK);
    }


    //post image upload

    @PostMapping("/Post/Image/Upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable("postId") Long postId
            ) throws IOException {

        PostDTO postDTO=this.postService.getPostById(postId);
        String fileName = this.fIleService.uploadImage(path,image);
        postDTO.setImageName(fileName);
        PostDTO postDTO1 = this.postService.updatePost(postDTO, postId);

        return new ResponseEntity<>(postDTO1,HttpStatus.OK);

    }
}
