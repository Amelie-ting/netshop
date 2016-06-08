package com.netshop.service.implement;

import java.sql.SQLException;
import java.util.List;

import com.netshop.dao.ItemsDao;
import com.netshop.dao.implement.ItemsDaoImpl;

import com.netshop.model.CriteriaItems;
import com.netshop.model.Items;

import com.netshop.service.ItemsService;

/**
 * @ClassName: ItemsServiceImpl
 * @Description: 商品模块业务层
 * @author hdm
 * @date 创建时间：2016年4月1日 下午10:05:06 @version=1.0
 */
public class ItemsServiceImpl implements ItemsService {
	public ItemsDao itemsDao = new ItemsDaoImpl();

	/**
	 * 按商品id加载商品
	 * 
	 * @param Iid
	 * @return
	 */
	@Override
	public Items loadByIid(int Iid) {
		try {
			return itemsDao.findByIid(Iid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// /**
	// * 按商品名模糊查询
	// * @param iname
	// * @param pc
	// * @return
	// */
	// @Override
	// public PageBean<Items> findByItemname(String iname, int pc) throws
	// SQLException {
	// try {
	// return itemsDao.findByItemname(iname, pc);
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	// }
	@Override
	public void add(Items item) {
		try {
			itemsDao.add(item);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Items> findByCategory(int cid) {
		try {
			return itemsDao.findByCategory(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按商品名模糊查询（不分页）
	 * 
	 * @param cr
	 * @return
	 */
	@Override
	public List<Items> Fuzzyquery(CriteriaItems cr) {
		try {
			return itemsDao.Fuzzyquery(cr);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
