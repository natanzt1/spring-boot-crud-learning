package com.johntitor.crudlearning.controllers;

import com.johntitor.crudlearning.models.entities.Product;
import com.johntitor.crudlearning.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/view/{price}")
    public List<Product> viewAllProductCheaperThan(@PathVariable("price") double price){
        return productService.findProductsCheaperThan(price);
    }

    @GetMapping("/id/{id}")
    public Product findById(@PathVariable("id") int id){
        return productService.findById(id);
    }
}
