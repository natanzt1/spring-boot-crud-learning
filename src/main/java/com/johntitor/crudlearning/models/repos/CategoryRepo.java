package com.johntitor.crudlearning.models.repos;

import com.johntitor.crudlearning.models.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Integer> {

    @Override
    @Query(value = "Select * from Category ORDER BY id", nativeQuery = true)
    Iterable<Category> findAll();


}
