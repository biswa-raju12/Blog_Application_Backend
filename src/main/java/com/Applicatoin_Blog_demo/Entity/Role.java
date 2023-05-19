package com.Applicatoin_Blog_demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name",nullable = false,length = 100)
    private  String name;



}
