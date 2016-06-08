package com.netshop.text;
import java.util.List;

import org.junit.Test;

import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.ItemsServiceImpl;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
public class ItemsServiceTest {
		
	@Test
	public void testfuzzy(){
		ItemsService its=new ItemsServiceImpl();
		String item_name="yifu";
		CriteriaItems criteriaItems=new CriteriaItems(item_name);
		List<Items>  lis=its.Fuzzyquery(criteriaItems);
		
		
	}
}
