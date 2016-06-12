package com.netshop.text;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.netshop.model.Category;
import com.netshop.service.CategoryService;
import com.netshop.service.implement.CategoryServiceImpl;

public class JsonTest {
	private CategoryService categoryService=new CategoryServiceImpl();
	
	@Test
	public void ajaxFindChildren()throws IOException {
		/*
		 * 1. 获取pid
		 * 2. 通过pid查询出所有2级分类
		 * 3. 把List<Category>转换成json，输出给客户端
		 */
		String pid = "3";
		List<Category> children = categoryService.findChildren(pid);
		String json = toJson(children);
		System.out.println(json);
		System.out.println(json.length());
		
	};
	
	/**
	 * toJson方法
	 * @param category
	 * @return
	 */
	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"ca_id\"").append(":").append("\"").append(category.getCa_id()).append("\"");
		sb.append(",");
		sb.append("\"ca_name\"").append(":").append("\"").append(category.getCa_name()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	private String toJson(List<Category> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if(i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}


}
