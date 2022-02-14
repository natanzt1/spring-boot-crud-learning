package com.johntitor.crudlearning.models.repos;

import com.johntitor.crudlearning.models.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT * from product where product.price < :price",
            nativeQuery = true)
    List<Product> findProductUnder(@Param("price") double price);

//    @Query(value = "SELECT u FROM User u WHERE u.name IN :names")
//    List<User> findUserByNameList(@Param("names") Collection<String> names);

}
