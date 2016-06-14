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
		//�����ϴ��ļ�����·��
		String filePath = this.getServletContext().getRealPath("/Excel");
		
		File file = new File(filePath);
		
		
		if(!file.exists()){
			file.mkdir();
		}
		SmartUpload su = new SmartUpload();
		//��ʼ������
		su.initialize(getServletConfig(), req, resp);
		//�����ϴ��ļ���С
		su.setMaxFileSize(1024*1024*10);
		//���������ļ��Ĵ�С
		su.setTotalMaxFileSize(1024*1024*100);
		//���������ϴ��ļ�����
		su.setAllowedFilesList("xls");
		String result = "�ϴ��ɹ���";
		//���ý�ֹ�ϴ����ļ�����
		try {
			su.setDeniedFilesList("rar,jsp,js");
			//�ϴ��ļ�
			su.upload();
			
			int count = su.save(filePath);
			System.out.println("�ϴ��ɹ�" +  count + "���ļ���");
		} catch (Exception e) {
			result = "�ϴ�ʧ�ܣ�";
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
