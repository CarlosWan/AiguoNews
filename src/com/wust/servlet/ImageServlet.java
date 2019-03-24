package com.wust.servlet;

import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//������֤���Servlet
public class ImageServlet extends HttpServlet {
	private Random rand = new Random();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//���÷��������ͻ��˵���Ӧ�������� --> MIME ͼƬ��ʽ
		response.setContentType("image/jpeg");
		
		//���ÿͻ����������ʾͼ�񲻻���
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("expires", 0);
		
		//�ڷ��������ڴ�������һ������ͼ��
		int width=200;
		int height=100;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		//��ȡָ���ͼ���ϵ�Graphic����
		Graphics g = image.getGraphics();
		
		//����������
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, width, height);
		//���Ʊ߿�
		g.setColor(new Color(0,0,0));
		g.drawRect(0, 0, width-1, height-1);
		
		//���ɸ�������
		for(int i=0;i<600;i++){
			g.setColor(getColor(100,200));
			int x1=rand.nextInt(width);
			int y1=rand.nextInt(height);
			int x2=rand.nextInt(20);
			int y2=rand.nextInt(20);
			
			g.drawLine(x1, y1, x1+x2, y1+y2);
		}
		
		
		//������֤��
		String code="";
		for(int i=0;i<4;i++){
			//��ȡ����ַ�
			char ch = str.charAt(rand.nextInt(str.length()));
			code+=ch;
			//��������
			Font font = new Font("Times New Roman",Font.BOLD|Font.ITALIC,40+rand.nextInt(40));
			g.setFont(font);
			//���������ɫ
			g.setColor(getColor(0,100));
			g.drawString(String.valueOf(ch),20+i*40,40+rand.nextInt(30));
		}
		
		//����������֤����session������
		request.getSession().setAttribute("code",code);
		
		//���������������е�ͼ��������ͻ��������
		ImageIO.write(image,"jpeg",response.getOutputStream());
	}

	/**
	 * ��ȡ�����ɫ
	 * @param start
	 * @param end
	 * @return
	 */
	private Color getColor(int start, int end) {
		Integer r = start+rand.nextInt(end-start);
		Integer g = start+rand.nextInt(end-start);
		Integer b = start+rand.nextInt(end-start);
	
		return new Color(r,g,b);
	}

}
