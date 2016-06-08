package com.netshop.text;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.netshop.dao.ItemsDao;
import com.netshop.dao.implement.ItemsDaoImpl;
import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;

public class ItemsDaoImplText2 {

	ItemsDao ItemsDao=new ItemsDaoImpl();
	@Test
	public void testFuzzyquery() throws SQLException {
		CriteriaItems CriteriaItems=new CriteriaItems("s");
		List<Items> cr=ItemsDao.Fuzzyquery(CriteriaItems);
		for (Items item:cr) {
			System.out.println(item.getItem_name()+"   "+item.getItem_price()+"   "+item.getItem_id());
		}
	}
}
