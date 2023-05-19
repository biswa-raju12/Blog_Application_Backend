package com.Applicatoin_Blog_demo.Service;

import com.Applicatoin_Blog_demo.Payroads.ApiResponse;
import com.Applicatoin_Blog_demo.Payroads.PostDTO;
import com.Applicatoin_Blog_demo.Payroads.PostResponse;
import com.Applicatoin_Blog_demo.Payroads.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    //Create

    PostDTO createPost(PostDTO postDTO, Long userId,Long categoryId);

    //Update
    PostDTO updatePost(PostDTO postDTO,Long postId);

    //delete

    void deletePost(Long postId);

    //getall

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);

    //getbyId

    PostDTO getPostById(Long postId);

    //get posts by category
    List<PostDTO> getPostByCategory(Long categoryId);

    //get posts by users
    List<PostDTO> getPostByUser(Long userId);

    //search post

    List<PostDTO> searchPosts(String keyword);

}
