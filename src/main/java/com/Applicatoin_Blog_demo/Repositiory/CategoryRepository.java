package com.Applicatoin_Blog_demo.Repositiory;

import com.Applicatoin_Blog_demo.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
