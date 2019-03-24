package com.wust.servlet;

 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.UserDao;
import com.wust.entity.UserInfo;
 
/**
 * 
 * 功能：发送邮箱功能处理
 * @author
 */

public class RegistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		String sessionCode = (String) req.getSession().getAttribute("code");
		System.out.println(sessionCode);
		//  获取session中的验证码
		if(sessionCode != null) {
			//  获取页面提交的验证码
			String inputCode = req.getParameter("code");
			System.out.println("页面提交的验证码:" + inputCode);
			if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
				// 把用户名和密码等一系列信息插入到数据库中
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String realname = req.getParameter("realname");
				String gender = req.getParameter("gender");
				System.out.println(gender);
				String telephone = req.getParameter("telephone");
				String email = req.getParameter("email");
				UserDao userDao= new UserDao();
				UserInfo userinfo=new UserInfo(username,password,realname,gender,telephone,email,0);
				userDao.regUser(userinfo);
				
				//  验证成功，跳转成功页面
				req.setAttribute("username", username);
				req.getRequestDispatcher("/success.jsp").forward(req, resp);
			}else {
				//  验证失败
				req.getRequestDispatcher("/fail.jsp").forward(req, resp);
			}
		}else {
			//  验证失败
			req.getRequestDispatcher("/fail.jsp").forward(req, resp);
		}
		//  移除session中的验证码
		req.removeAttribute("code");
	}
}

