package com.netshop.admin.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.netshop.model.Items;
import com.netshop.service.ItemsService;
import com.netshop.service.implement.ItemsServiceImpl;

/**
 * Servlet implementation class AdminAddEditServlet
 */
@WebServlet("/AdminAddEditServlet")
public class AdminAddEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1. commons-fileupload���ϴ�����
		 */
		// ��������
		FileItemFactory factory = new DiskFileItemFactory();
		/*
		 * 2.��������������
		 */
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(100*80 * 1024);//���õ����ϴ����ļ�����Ϊ80KB
		/*
		 * 3.����request�õ�List<FileItem>
		 */
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// �����������첽��˵�������ļ�������80KB
			error("�ϴ����ļ�������80KB", request, response);
			return;
		}
		
		/*
		 * 4. ��List<FileItem>��װ��Book������
		 * 4.1 ���Ȱѡ���ͨ���ֶΡ��ŵ�һ��Map�У��ٰ�Mapת����Book��Category�����ٽ������ߵĹ�ϵ
		 */
		Map<String,Object> map = new HashMap<String,Object>();
		for(FileItem fileItem : fileItemList) {
			if(fileItem.isFormField()) {///�������ͨ���ֶ�
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
			
		}
		Items item = CommonUtils.toBean(map, Items.class);
		
		/*
		 * 4.2 ���ϴ���ͼƬ��������
		 *   > ��ȡ�ļ�������ȡ֮
		 *   > ���ļ����ǰ׺��ʹ��uuidǰ׺��ΪҲ�����ļ�ͬ������
		 *   > У���ļ�����չ����ֻ����jpg
		 *   > У��ͼƬ�ĳߴ�
		 *   > ָ��ͼƬ�ı���·��������Ҫʹ��ServletContext#getRealPath()
		 *   > ����֮
		 *   > ��ͼƬ��·�����ø�Book����
		 */
		// ��ȡ�ļ���
		FileItem fileItem = fileItemList.get(3);//��ȡͼƬ
		System.out.println(fileItem);
		String filename = fileItem.getName();
		// ��ȡ�ļ�������Ϊ����������ϴ��ľ���·��
		int index = filename.lastIndexOf("\\");
		if(index != -1) {
			filename = filename.substring(index + 1);
		}
		// ���ļ������uuidǰ׺�������ļ�ͬ������
		filename = CommonUtils.uuid() + "_" + filename;
		// У���ļ����Ƶ���չ��
		if(!filename.toLowerCase().endsWith(".jpg")) {
			error("�ϴ���ͼƬ��չ��������JPG", request, response);
			return;
		}
		// У��ͼƬ�ĳߴ�
				// �����ϴ���ͼƬ����ͼƬnew��ͼƬ����Image��Icon��ImageIcon��BufferedImage��ImageIO
				/*
				 * ����ͼƬ��
				 * 1. ��ȡ��ʵ·��
				 */
		String savepath = this.getServletContext().getRealPath("/item_img");
		/*
		 * 2. ����Ŀ���ļ�
		 */
		File destFile = new File(savepath, filename);
		/*
		 * 3.  �����ļ�
		 */
		try {
			fileItem.write(destFile);//�������ʱ�ļ��ض���ָ����·������ɾ����ʱ�ļ�
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// У��ߴ�
				// 1. ʹ���ļ�·������ImageIcon
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		// 2. ͨ��ImageIcon�õ�Image����
		Image image = icon.getImage();
		// 3. ��ȡ���������У��
		if(image.getWidth(null) > 1050 || image.getHeight(null) > 1050) {
			error("���ϴ���ͼƬ�ߴ糬����350*350��", request, response);
			destFile.delete();//ɾ��ͼƬ
			return;
		}
		
		// ��ͼƬ��·�����ø�items����
		item.setItem_pic("item_img/" + filename);

		// ����service��ɱ���
		
		ItemsService itemsService = new ItemsServiceImpl();
		
		//��ʹ��add��������������Ʒ֮ǰ���Ȱ�֮ǰ������barcode��һ����ɾ��
		int barid=itemsService.findIdByBar(item.getBarcode());
		String newid=String.valueOf(barid);
		itemsService.delete(newid);
		itemsService.add(item);
		
		// ����ɹ���Ϣת����msg.jsp
//		String bid = request.getParameter("barcode");
//		System.out.println(bid);
		request.setAttribute("msg", "�����Ʒ�ɹ���");
		request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
	}
	
	/*
	 * ���������Ϣ��ת����add.jsp
	 */
	private void error(String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		//request.setAttribute("parents", new CategoryService().findParents());
		request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").
				forward(request, response);
	}

}
