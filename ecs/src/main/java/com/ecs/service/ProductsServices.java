package com.ecs.service;

import java.util.List;

import com.ecs.entity.Products;

public interface ProductsServices {
 public Products addProduct(Products product);
 public Products getProduct(Integer id);
 public List<Products> getProducts(String field,String direction,Integer pageno,Integer records);
 public Products removeById(Integer id);
 public Products updateProduct(double price,String title,String description ,Integer pid);
}
