package com.Applicatoin_Blog_demo.Payroads;


import com.Applicatoin_Blog_demo.Entity.Category;
import com.Applicatoin_Blog_demo.Entity.Comment;
import com.Applicatoin_Blog_demo.Entity.User;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private String imageName="default.png";

    private Date addedDate;

    private CategoryDTO category;
    private UserDTO user;

    private Set<Comment> comments = new HashSet<>();
}
