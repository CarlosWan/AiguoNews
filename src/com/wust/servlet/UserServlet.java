package com.wust.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wust.dao.UserDao;
import com.wust.entity.UserInfo;

public class UserServlet extends HttpServlet {
    private UserDao userDao=new UserDao();
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
		String method=request.getParameter("method");
		if(method.equals("login")){
			login(request,response);
		 }else if(method.equals("logout")) {
			 logout(request,response);
		 }
	}
	/**退出*/
	private void logout(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
	  HttpSession session = request.getSession();
	  session.invalidate();//使用会话失效
	  //回到系统首页
	  response.sendRedirect("/admin/index.jsp");
	}

   /**登录*/
	private void login(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		//获取用户名和密码
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			//判断空
			if(username.equals("")){
				request.setAttribute("error","用户名不能为空！");
				//回到登录页面
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
				return;
			}
			
			if(password.equals("")){
				request.setAttribute("error","密码不能为空！");
				//回到登录页面
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
				return;
			}
			
			UserInfo loginUser = userDao.queryUserByName(username);
			if(loginUser==null){
				request.setAttribute("error", "用户名不存在！");
				//回到登录界面
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			}else if(!loginUser.getPassword().equals(password)){
				request.setAttribute("error","密码错误！");
				//回到登录界面
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			}else{
				//登陆成功，进入后台管理首页
				//获取是否需要记住登录的用户
				String isSave = request.getParameter("isSave");
				//创建cookie对象
				Cookie c1 = new Cookie("username",username);
				Cookie c2 = new Cookie("password",password);
				//System.out.println(isSave+"------------------------");
			if(isSave!=null && isSave.equals("on")){
					//设置cookie的存活期
					c1.setMaxAge(60*60*60*30);
					c2.setMaxAge(60*60*60*30);	
				}else{
					//删除已有的cookie
					c1.setMaxAge(0);
					c2.setMaxAge(0);
				}
				//发送cookie到客户端浏览器
				response.addCookie(c1);
				response.addCookie(c2);
				
				//将当前登录的用户存入会话作用域
				/*
				 * HttpSession session = request.getSession();//获取当前请求相关联的会话对象
				 * session.setAttribute("loginUser",loginUser);
				 */
				
				request.getSession().setAttribute("loginUser",loginUser);
				
				//request.setAttribute("loginUser",loginUser);
				request.getRequestDispatcher("/admin/manager_index.jsp").forward(request, response);
			}
			
			}	
	}




