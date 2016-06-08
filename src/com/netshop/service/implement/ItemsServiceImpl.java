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
 * @Description: ��Ʒģ��ҵ���
 * @author hdm
 * @date ����ʱ�䣺2016��4��1�� ����10:05:06 @version=1.0
 */
public class ItemsServiceImpl implements ItemsService {
	public ItemsDao itemsDao = new ItemsDaoImpl();

	/**
	 * ����Ʒid������Ʒ
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
	// * ����Ʒ��ģ����ѯ
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
	 * ����Ʒ��ģ����ѯ������ҳ��
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
