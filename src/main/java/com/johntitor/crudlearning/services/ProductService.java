package com.johntitor.crudlearning.services;

import com.johntitor.crudlearning.models.entities.Product;
import com.johntitor.crudlearning.models.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product create(Product product){
        return productRepo.save(product);
    }

    public Product findById(Integer id){
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll(){
       return productRepo.findAll();
    }

    public List<Product> findProductsCheaperThan(double price){
        return productRepo.findProductUnder(price);
    }
}
