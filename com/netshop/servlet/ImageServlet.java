package com.netshop.servlet;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 验证码
 * @author lucah
 *
 */
public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//背景 先是宽度，然后是长度
		BufferedImage bi = new BufferedImage(100,35,BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		//颜色
		Color c = new Color(200,150,255);
		g.setColor(c);
		//框
		g.fillRect(0, 0, 100, 35);
		
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random r = new Random();
		int len=ch.length,index;
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<4; i++){
			index = r.nextInt(len);
			//随机区别字体的颜色
			g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
			
			g.drawString(ch[index]+"", (i*22)+3, 20);
			sb.append(ch[index]);
		}
		request.getSession().setAttribute("piccode", sb.toString());
		ImageIO.write(bi, "JPG", response.getOutputStream());
	}
}

