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

//生成验证码的Servlet
public class ImageServlet extends HttpServlet {
	private Random rand = new Random();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//设置服务器到客户端的响应内容类型 --> MIME 图片格式
		response.setContentType("image/jpeg");
		
		//设置客户端浏览器显示图像不缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("expires", 0);
		
		//在服务器端内存中生成一个缓冲图像
		int width=200;
		int height=100;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		//获取指向该图像上的Graphic画笔
		Graphics g = image.getGraphics();
		
		//绘制填充矩形
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, width, height);
		//绘制边框
		g.setColor(new Color(0,0,0));
		g.drawRect(0, 0, width-1, height-1);
		
		//生成干扰线条
		for(int i=0;i<600;i++){
			g.setColor(getColor(100,200));
			int x1=rand.nextInt(width);
			int y1=rand.nextInt(height);
			int x2=rand.nextInt(20);
			int y2=rand.nextInt(20);
			
			g.drawLine(x1, y1, x1+x2, y1+y2);
		}
		
		
		//生成验证码
		String code="";
		for(int i=0;i<4;i++){
			//获取随机字符
			char ch = str.charAt(rand.nextInt(str.length()));
			code+=ch;
			//设置字体
			Font font = new Font("Times New Roman",Font.BOLD|Font.ITALIC,40+rand.nextInt(40));
			g.setFont(font);
			//设置随机颜色
			g.setColor(getColor(0,100));
			g.drawString(String.valueOf(ch),20+i*40,40+rand.nextInt(30));
		}
		
		//将服务器验证存入session作用域
		request.getSession().setAttribute("code",code);
		
		//将服务器缓冲区中的图像输出到客户端浏览器
		ImageIO.write(image,"jpeg",response.getOutputStream());
	}

	/**
	 * 获取随机颜色
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
