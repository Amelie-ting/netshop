package com.netshop.service.implement;

import java.sql.SQLException;
import java.util.List;

import com.netshop.dao.AddessDao;
import com.netshop.dao.implement.AddessDaoImpl;
import com.netshop.model.UserAddress;
import com.netshop.service.AddressService;

public class AddressServiceImpl implements AddressService {
   private AddessDao addessDao=new AddessDaoImpl();
  
	@Override
	public void addAddress(UserAddress addr) {
		     addr.setA_id(addr.getA_id());
			if (addr.getA_isDefault()==null) {
				addr.setA_isDefault("2");
				}else{
					try {
						addessDao.setNoDefault(addr.getA_uid());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			try {
				addessDao.addAddress(addr);
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
	@Override
	public List<UserAddress> loadaddress(int uid) {
		try {
			return addessDao.findaddressByUser(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void delete(int aid) {
		try {
			 addessDao.delete(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void update(UserAddress userAddress) {
		userAddress.setA_id(userAddress.getA_id());
		if (userAddress.getA_isDefault()==null) {
			userAddress.setA_isDefault("2");
			}else{
				try {
					addessDao.setNoDefault(userAddress.getA_uid());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		try {
			 addessDao.update(userAddress);;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public UserAddress findById(int aid) {
		try {
			return addessDao.findById(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void setDefault(UserAddress aaddr) {
		try {
			addessDao.setNoDefault(aaddr.getA_uid());
			aaddr=addessDao.findById(aaddr.getA_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (aaddr.getA_isDefault().equals("2")) {
			try {
				addessDao.setDefault(aaddr.getA_id());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void setNoDefault(UserAddress aaddr) {
	try {
		addessDao.setNoDefault(aaddr.getA_uid());
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
