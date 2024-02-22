package com.ecs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ecs.entity.Products;
@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>,PagingAndSortingRepository<Products,Integer> {

}
