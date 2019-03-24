package com.wust.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.UserDao;
import com.wust.entity.UserInfo;

public class UserLoginServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         this.doPost(request, response);
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   
          //获取请求参数method
		 String method=request.getParameter("method");
		 if(method.equals("login")){
			 login(request,response);
		 }
	}
	
	
	/**登录**/
	private void login(HttpServletRequest request, HttpServletResponse response) 
		    throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("")){
			request.setAttribute("error", "Please Enter Username!");
			request.getRequestDispatcher("/userindex.jsp").forward(request, response);
			return;
		}
		if(password.equals("")){
			request.setAttribute("error", "Please Enter Password!");
			request.getRequestDispatcher("/userindex.jsp").forward(request, response);
			return;
		}
		UserInfo loginUser = userDao.queryUserByName(username);
		if(loginUser==null){
			request.setAttribute("error","User doesn't exist.Please register!");
			request.getRequestDispatcher("/userindex.jsp").forward(request,response);
		}else if(!loginUser.getPassword().equals(password)){
			request.setAttribute("error","Password error!");
			request.getRequestDispatcher("/userindex.jsp").forward(request,response);
		}else{
			request.setAttribute("msg", "Login successful!");
			request.getRequestDispatcher("/userindex.jsp").forward(request, response);
		}
	}
}
		
	


