package com.netshop.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.netshop.commons.CommonUtils;
import com.netshop.model.Category;
import com.netshop.model.Items;
import com.netshop.service.CategoryService;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.CategoryServiceImpl;
import com.netshop.service.implement.ItemsServiceImpl;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		/*
		 * 1. commons-fileupload的上传三步
		 */
		// 创建工具
		FileItemFactory factory = new DiskFileItemFactory();
		/*
		 * 2. 创建解析器对象
		 */
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(80 * 1024);// 设置单个上传的文件上限为80KB
		/*
		 * 3. 解析request得到List<FileItem>
		 */
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// 如果出现这个异步，说明单个文件超出了80KB
			error("上传的文件超出了80KB", request, response);
			return;
		}

		/*
		 * 4.把List<FileItem>封装到Book对象中 4.1
		 * 首先把“普通表单字段”放到一个Map中，再把Map转换成Book和Category对象，再建立两者的关系
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		for (FileItem fileItem : fileItemList) {
			if (fileItem.isFormField()) {// 如果是普通表单字�?
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}

		}
		Items item = CommonUtils.toBean(map, Items.class);

		/*
		 * 4.2 把上传的图片保存起来 > 获取文件名：截取之 > 给文件添加前缀：使用uuid前缀，为也避免文件同名现象 >
		 * 校验文件的扩展名：只能是jpg > 校验图片的尺寸 >
		 * 指定图片的保存路径，这需要使用ServletContext#getRealPath() > 保存之 > 把图片的路径设置给Book对象
		 */
		// 获取文件名
		FileItem fileItem = fileItemList.get(3);// 获取大图
		String filename = fileItem.getName();
		// 截取文件名，因为部分浏览器上传的绝对路径
		int index = filename.lastIndexOf("\\");
		if (index != -1) {
			filename = filename.substring(index + 1);
		}
		// 给文件名添加uuid前缀，避免文件同名现象
		filename = CommonUtils.uuid() + "_" + filename;
		// 校验文件名称的扩展名
		if (!filename.toLowerCase().endsWith(".jpg")) {
			error("上传的图片扩展名必须是JPG", request, response);
			return;
		}
		// 校验图片的尺寸
		// 保存上传的图片，把图片new成图片对象：Image、Icon、ImageIcon、BufferedImage、ImageIO
		/*
		 * 保存图片： 1. 获取真实路径
		 */
		String savepath = this.getServletContext().getRealPath("/item_img");
		/*
		 * 2. 创建目标文件
		 */
		File destFile = new File(savepath, filename);
		/*
		 * 3. 保存文件
		 */
		try {
			fileItem.write(destFile);// 它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 校验尺寸
		// 1. 使用文件路径创建ImageIcon
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		// 2. 通过ImageIcon得到Image对象
		Image image = icon.getImage();
		// 3. 获取宽高来进行校�?
		if (image.getWidth(null) > 350 || image.getHeight(null) > 350) {
			error("您上传的图片尺寸超出了350*350！", request, response);
			destFile.delete();// 删除图片
			return;
		}

		// 把图片的路径设置给book对象
		item.setItem_pic("item_img/" + filename);

		// 调用service完成保存

		ItemsService itemsService = new ItemsServiceImpl();
		itemsService.add(item);

		// 保存成功信息转发到msg.jsp
		request.setAttribute("msg", "添加商品成功");
		request.getRequestDispatcher("/app/msg.jsp").forward(request, response);
	}

	/*
	 * 保存错误信息，转发到add.jsp
	 */
	private void error(String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		// request.setAttribute("parents", new
		// CategoryService().findParents());//�?有一级分�?
		request.getRequestDispatcher("/app/add.jsp").forward(request, response);
	}

	/**
	 * 查询所有分类
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	CategoryService categoryService = new CategoryServiceImpl();

	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("parents", categoryService.findAll());
		return "f:/app/category/list.jsp";
	}

	/**
	 * 添加一级分类
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addParent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * 1. 封装表单数据到Category中 2. 调用service的add()方法完成添加 3.
		 * 调用findAll()，返回list.jsp显示所有分类
		 */
		Category parent = CommonUtils.toBean(req.getParameterMap(), Category.class);
		parent.setCa_id(CommonUtils.uuid());
		;// 设置cid
		categoryService.add(parent);
		return findAll(req, resp);
	}

	public String addChild(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Category中 2. 需要手动的把表单中的pid映射到child对象中 2.
		 * 调用service的add()方法完成添加 3. 调用findAll()，返回list.jsp显示所有分类
		 */
		Category child = CommonUtils.toBean(req.getParameterMap(), Category.class);
		child.setCa_id(CommonUtils.uuid());
		;// 设置cid

		// 手动映射pid
		String pid = req.getParameter("pid");
		Category parent = new Category();
		parent.setCa_id(pid);
		;
		child.setParent(parent);

		categoryService.add(child);
		return findAll(req, resp);
	}

	/**
	 * 添加第二分类：第一步
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addChildPre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");// 当前点击的父分类id
		List<Category> parents = categoryService.findParents();
		req.setAttribute("pid", pid);
		req.setAttribute("parents", parents);

		return "f:/app/category/add2.jsp";
	}

}
