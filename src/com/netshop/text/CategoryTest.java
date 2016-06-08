package com.netshop.text;

import java.util.List;

import org.junit.Test;

import com.netshop.model.Category;
import com.netshop.service.CategoryService;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.CategoryServiceImpl;
import com.netshop.service.implement.ItemsServiceImpl;

public class CategoryTest {
//	@Test
//	public void testloadall(){
//		CategoryService itemsService=new CategoryServiceImpl();
//		List<Category> x1=itemsService.findAll();
//		for(Category category:x1)
//		{
//			System.out.println(category.getCa_name());
//			System.out.println(category.getChildren());
//		}
//	}
	
//	@Test
//	public void testfindParents(){
//		CategoryService itemsService=new CategoryServiceImpl();
//		List<Category> x1=itemsService.findParents();
//		for(Category category:x1)
//		{
//			System.out.println(category.getCa_name());
//			
//		}
//	}
	
	@Test
	public void testfindChildren(){
		CategoryService itemsService=new CategoryServiceImpl();
//		Category Category=new Category();
		String pid="3";
		List<Category> x1=itemsService.findChildren(pid);
		for(Category category:x1)
		{
			System.out.println(category.getCa_name());
			
		}
	}
}
