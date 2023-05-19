package com.Applicatoin_Blog_demo.Controller;

import com.Applicatoin_Blog_demo.Entity.Category;
import com.Applicatoin_Blog_demo.Payroads.ApiResponse;
import com.Applicatoin_Blog_demo.Payroads.CategoryDTO;
import com.Applicatoin_Blog_demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Post

    @PostMapping("/Category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO categoryDTO1= this.categoryService.createCategory(categoryDTO);

        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/Category/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Long categoryId)
    {
        CategoryDTO categoryDTO1= this.categoryService.updateCategory(categoryDTO,categoryId);

        return new ResponseEntity<>(categoryDTO1, HttpStatus.OK);
    }


    //Delete

    @DeleteMapping("/Category/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Long categoryId)
    {
        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true), HttpStatus.OK);
    }


    //get

    @GetMapping("/Category")
    public List<CategoryDTO> getAllCategory()
    {
        List<CategoryDTO> categoryDTOS = this.categoryService.getAllCategory();

        return categoryDTOS;
    }


    //getAll
    @GetMapping("/Category/{id}")
    public ResponseEntity<CategoryDTO> getAllCategoryById(@PathVariable("id") Long categoryId)
    {
        CategoryDTO categoryDTO= this.categoryService.getAllCategoryById(categoryId);

        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

}

