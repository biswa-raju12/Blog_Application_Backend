package com.Applicatoin_Blog_demo.Repositiory;

import com.Applicatoin_Blog_demo.Entity.Category;
import com.Applicatoin_Blog_demo.Entity.Post;
import com.Applicatoin_Blog_demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category user);

//    @Query("select p from Post p where p.title like :key")
//    List<Post> searchByTitle(@Param("key") String title);

    @Query("select p from Post p where lower(p.title) like concat('%', :keyword,'%')")
    public List<Post> searchPostHavingKeyword(@Param("keyword") String keyword);


}
