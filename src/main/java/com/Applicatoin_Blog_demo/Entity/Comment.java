package com.Applicatoin_Blog_demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String content;

    @ManyToOne
    @JoinColumn(name = "post")
    private  Post post;


}
