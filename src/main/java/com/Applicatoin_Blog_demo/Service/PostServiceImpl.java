package com.Applicatoin_Blog_demo.Service;

import com.Applicatoin_Blog_demo.Entity.Category;
import com.Applicatoin_Blog_demo.Entity.Post;
import com.Applicatoin_Blog_demo.Entity.User;
import com.Applicatoin_Blog_demo.Exceptions.ResourceNotFoundException;
import com.Applicatoin_Blog_demo.Payroads.*;
import com.Applicatoin_Blog_demo.Repositiory.CategoryRepository;
import com.Applicatoin_Blog_demo.Repositiory.PostRepository;
import com.Applicatoin_Blog_demo.Repositiory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public PostDTO createPost(PostDTO postDTO,Long userId,Long categoryId) {

        //get user information

        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));


        Post post = this.DTOtoPost(postDTO);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        this.postRepository.save(post);


        return this.PostTooDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {

        Post post1;
        post1 = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));


        post1.setTitle(postDTO.getTitle());
        post1.setContent(postDTO.getContent());

        this.postRepository.save(post1);

        PostDTO postDTO1 = this.PostTooDTO(post1);

        return postDTO1;
    }

    @Override
    public void deletePost(Long postId) {

        this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        this.postRepository.deleteById(postId);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {


        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page<Post> pagePost = this.postRepository.findAll(pageable);

        List<Post> posts = pagePost.getContent();

        List<PostDTO> postDTOS = posts.stream().map((post) -> this.PostTooDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();


        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());

        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));


        PostDTO postDTO = this.PostTooDTO(post);
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostByCategory(Long categoryId) {
        Category category= this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
        List<Post> posts = this.postRepository.findByCategory(category);

        List<PostDTO> postDTOS = posts.stream().map((post)-> this.PostTooDTO(post)).collect(Collectors.toList());


        return postDTOS;
    }

    @Override
    public List<PostDTO> getPostByUser(Long userId) {

        User user= this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserID",userId));
        List<Post> posts = this.postRepository.findByUser(user);

        List<PostDTO> postDTOS = posts.stream().map((post)-> this.PostTooDTO(post)).collect(Collectors.toList());


        return postDTOS;
    }

    @Override
    public List<PostDTO> searchPosts(String keywords) {
        List<Post> posts= postRepository.searchPostHavingKeyword("%" + keywords + "%");
        List<PostDTO> postDTOS = posts.stream().map((post) -> this.PostTooDTO(post)).collect(Collectors.toList());

        return postDTOS;
    }

    public Post DTOtoPost(PostDTO postDTO)
    {
        Post post = this.modelMapper.map(postDTO, Post.class);
        return post;

    }

    public PostDTO PostTooDTO(Post post)
    {
        PostDTO postDTO= this.modelMapper.map(post, PostDTO.class);
        return postDTO;

    }

}
