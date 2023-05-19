package com.Applicatoin_Blog_demo.Service;

import com.Applicatoin_Blog_demo.Entity.Category;
import com.Applicatoin_Blog_demo.Exceptions.ResourceNotFoundException;
import com.Applicatoin_Blog_demo.Payroads.CategoryDTO;
import com.Applicatoin_Blog_demo.Repositiory.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = this.DTOtoCategory(categoryDTO);

        this.categoryRepository.save(category);

        CategoryDTO categoryDTO1= this.CategorytoDTO(category);


        return categoryDTO1;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryDescription(category.getCategoryDescription());

        CategoryDTO categoryDTO1= this.CategorytoDTO(category);

        return categoryDTO1;
    }

    @Override
    public void deleteCategory(Long categoryId) {

        categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {

        List<Category> categories= this.categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS= categories.stream().map(category-> this.CategorytoDTO(category)).collect(Collectors.toList());

        return categoryDTOS;
    }

    @Override
    public CategoryDTO getAllCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));



        return this.CategorytoDTO(category);
    }


    public Category DTOtoCategory(CategoryDTO categoryDTO)
    {
//        User user=new User();
//        user.setUserId(userDTO.getUserId());
//        user.setUserName(userDTO.getUserName());
//        user.setUserEmail(userDTO.getUserEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());

        Category category = this.modelMapper.map(categoryDTO, Category.class);

        return category;

    }

    public CategoryDTO CategorytoDTO(Category category)
    {
        CategoryDTO categoryDTO= this.modelMapper.map(category, CategoryDTO.class);

//        userDTO.setUserId(user.getUserId());
//        userDTO.setUserName(user.getUserName());
//        userDTO.setUserEmail(user.getUserEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());

        return categoryDTO;

    }
}
