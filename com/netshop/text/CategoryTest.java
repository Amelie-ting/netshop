package com.netshop.text;

import java.util.List;

import org.junit.Test;

import com.netshop.model.Category;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.ItemsServiceImpl;

public class CategoryTest {
	@Test
	public void testloadall(){
		ItemsService itemsService=new ItemsServiceImpl();
		List<Category> x1=itemsService.loadByType();
		for(Category category:x1)
		{
			System.out.println(category.getCa_name());
		}
	}
}
