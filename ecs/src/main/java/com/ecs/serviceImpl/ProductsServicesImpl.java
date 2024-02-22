package com.ecs.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecs.entity.Products;
import com.ecs.service.ProductsServices;

@Service
public class ProductsServicesImpl implements ProductsServices {

	@Override
	public Products addProduct(Products product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products getProduct(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Products> getProducts(String field, String direction, Integer pageno, Integer records) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products removeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products updateProduct(double price, String title, String description, Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
