package com.Applicatoin_Blog_demo.Service;

import com.Applicatoin_Blog_demo.Payroads.CategoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO,Long categoryId);

    void deleteCategory(Long categoryId);

    List<CategoryDTO> getAllCategory();

    CategoryDTO getAllCategoryById(Long categoryId);
}
