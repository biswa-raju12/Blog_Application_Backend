package com.Applicatoin_Blog_demo.Repositiory;

import com.Applicatoin_Blog_demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    User findByUserName(String username);

}
