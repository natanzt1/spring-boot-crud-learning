package com.johntitor.crudlearning.controllers;

import com.johntitor.crudlearning.models.entities.Category;
import com.johntitor.crudlearning.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category create(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PutMapping
    public Category update(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") Integer id){
        return categoryService.findByID(id);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @DeleteMapping
    public void deleteById(Integer id){
        categoryService.deleteById(id);
    }
}
