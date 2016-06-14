package com.netshop.servlet.Excel;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class InUpServlet
 */
@WebServlet("/InUpServlet")
public class InUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置上传文件保存路径
		String filePath = this.getServletContext().getRealPath("/Excel");
		
		File file = new File(filePath);
		
		
		if(!file.exists()){
			file.mkdir();
		}
		SmartUpload su = new SmartUpload();
		//初始化对象
		su.initialize(getServletConfig(), req, resp);
		//设置上传文件大小
		su.setMaxFileSize(1024*1024*10);
		//设置所有文件的大小
		su.setTotalMaxFileSize(1024*1024*100);
		//设置允许上传文件类型
		su.setAllowedFilesList("xls");
		String result = "上传成功！";
		//设置禁止上传的文件类型
		try {
			su.setDeniedFilesList("rar,jsp,js");
			//上传文件
			su.upload();
			
			int count = su.save(filePath);
			System.out.println("上传成功" +  count + "个文件！");
		} catch (Exception e) {
			result = "上传失败！";
			e.printStackTrace();
		}
		req.setAttribute("result1", result);
		req.getRequestDispatcher("/adminjsps/admin/storage/storage.jsp").forward(req, resp);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
