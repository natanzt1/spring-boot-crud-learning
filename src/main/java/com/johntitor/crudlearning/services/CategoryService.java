package com.johntitor.crudlearning.services;

import com.johntitor.crudlearning.models.entities.Category;
import com.johntitor.crudlearning.models.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category){
        return categoryRepo.save(category);
    }

    public Category findByID(Integer id){
        return categoryRepo.findById(id).get();
    }

    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }

    public void deleteById(Integer id){
        categoryRepo.deleteById(id);
    }
}
