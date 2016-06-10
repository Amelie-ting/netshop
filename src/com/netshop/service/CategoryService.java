package com.netshop.service;

import java.util.List;

import com.netshop.model.Category;

public interface CategoryService {
	public List<Category> findAll();

	public void add(Category category);

	public List<Category> findParents();

	public List<Category> findChildren(String pid);
	
	public void edit(Category category);
	
	public Category load(String ca_id);

}
