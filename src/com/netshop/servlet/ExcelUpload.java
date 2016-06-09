package com.netshop.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class ExcelUpload
 */
@WebServlet("/ExcelUpload")
public class ExcelUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����ϴ��ļ�����·��
				String filePath = this.getServletContext().getRealPath("/Excel");
				System.out.println(filePath);
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
				
				for(int i =0; i < su.getFiles().getCount(); i++){
					com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
					System.out.println("---------------------------");
					System.out.println("������name����ֵ��" + tempFile.getFieldName());
					System.out.println("�ϴ��ļ�����" + tempFile.getFieldName());
					System.out.println("�ϴ��ļ�����:" + tempFile.getSize());
					System.out.println("�ϴ��ļ�����չ����" + tempFile.getFileExt());
					System.out.println("�ϴ��ļ���ȫ����" + tempFile.getFilePathName());
					System.out.println("---------------------------");
				}
				
				req.setAttribute("result", result);
				req.getRequestDispatcher("/admin/jsp/upload.jsp").forward(req, resp);
	}

}
