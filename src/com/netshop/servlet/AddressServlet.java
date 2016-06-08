package com.netshop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netshop.commons.CommonUtils;
import com.netshop.model.User;
import com.netshop.model.UserAddress;
import com.netshop.service.AddressService;
import com.netshop.service.implement.AddressServiceImpl;

@WebServlet("/AddressServlet")
public class AddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private AddressService addrService = new AddressServiceImpl();

	/**
	 * ����ջ���ַ
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("usersession");
		int uid = user.getU_id();
		String name = req.getParameter("name");
		String state = req.getParameter("province");
		String city = req.getParameter("city");
		String county = req.getParameter("county");
		String addr = req.getParameter("addr");
		String tel = req.getParameter("tel");
		String zip = req.getParameter("zip");
		String isDefault = req.getParameter("isDefault");

		// name,addr,tel����Ϊ�գ�����ǿվ���ʾ
		if (name.equals("") || addr.equals("") || tel.equals("")) {
			req.setAttribute("msg", "��*����Ϊ�գ�");
			return loadaddress(req, resp);
		}
		// name,addr,tel����Ϊ�գ�����ǿվ���ʾ
		if (tel.length() != 11) {
			req.setAttribute("msg", "����д��ȷ�ĵ绰���룡");
			return loadaddress(req, resp);
		}

		// ʡ�ݼ��鲻��ΪĬ�ϵ�
		if (state.equals("ʡ��") || city.equals("�ؼ���") || county.equals("�С��ؼ���")) {
			req.setAttribute("msg", "��ѡ�������");
			return loadaddress(req, resp);
		}

		UserAddress address = new UserAddress();
		address.setA_uid(uid);
		address.setA_uname(name);
		address.setA_state(state);
		address.setA_county(county);
		address.setA_city(city);
		address.setA_addr(addr);
		address.setA_tel(tel);
		address.setA_zip(zip);
		address.setA_isDefault(isDefault);

		addrService.addAddress(address);

		return loadaddress(req, resp);
	}

	/**
	 * ���ض��addressId
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadaddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �õ�uid
		User user = (User) req.getSession().getAttribute("usersession");
		// �����û��Ƿ��½
		if (user == null) {
			return "f:/jsps/msg.jsp";
		}

		int uid = user.getU_id();
		/*
		 * 2. ͨ��service�õ�List<UserAddress>
		 */
		List<UserAddress> userAddressList = addrService.loadaddress(uid);
		/*
		 * 3. ���棬Ȼ��ת����/views/address/addresslist.jsp
		 */
		req.setAttribute("userAddressList", userAddressList);

		return "f:/jsps/address/addresslist.jsp";
	}

	public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("aid"));
		addrService.delete(id);
		return loadaddress(req, resp);

	}

	public String findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("aid"));
		UserAddress userddress = addrService.findById(id);
		req.setAttribute("userddress", userddress);
		return "f:/jsps/address/updateaddress.jsp";

	}

	public String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String state = req.getParameter("province");
		String city = req.getParameter("city");
		String county = req.getParameter("county");
		String addr = req.getParameter("addr");
		String tel = req.getParameter("tel");
		String zip = req.getParameter("zip");
		String isDefault = req.getParameter("isDefault");
		Map map = req.getParameterMap();
		UserAddress address = CommonUtils.toBean(map, UserAddress.class);
		address.setA_id(Integer.parseInt(req.getParameter("aid")));

		// name,addr,tel����Ϊ�գ�����ǿվ���ʾ
		if (name.equals("") || addr.equals("") || tel.equals("")) {
			req.setAttribute("msg", "��*����Ϊ�գ�");
			return loadaddress(req, resp);
		}
		// name,addr,tel����Ϊ�գ�����ǿվ���ʾ
		if (tel.length() != 11) {
			req.setAttribute("msg", "����д��ȷ�ĵ绰���룡");
			return loadaddress(req, resp);
		}

		// ʡ�ݼ��鲻��ΪĬ�ϵ�
		if (state.equals("ʡ��") || city.equals("�ؼ���") || county.equals("�С��ؼ���")) {
			req.setAttribute("msg", "��ѡ�������");
			return loadaddress(req, resp);
		}

		address.setA_uname(name);
		address.setA_state(state);
		address.setA_county(county);
		address.setA_city(city);
		address.setA_addr(addr);
		address.setA_tel(tel);
		address.setA_zip(zip);
		address.setA_isDefault(isDefault);
		addrService.update(address);

		return loadaddress(req, resp);

	}

	public String setDefault(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		User user = (User) req.getSession().getAttribute("usersession");
		int uid = user.getU_id();
		UserAddress address = new UserAddress();
		int id = Integer.parseInt(req.getParameter("aid"));
		address.setA_uid(uid);
		address.setA_id(id);
		addrService.setDefault(address);
		return loadaddress(req, resp);
	}

	public String setNoDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("usersession");
		int uid = user.getU_id();
		UserAddress address = new UserAddress();
		int id = Integer.parseInt(req.getParameter("aid"));
		address.setA_uid(uid);
		// address.setA_id(id);
		addrService.setNoDefault(address);
		return loadaddress(req, resp);

	}
}