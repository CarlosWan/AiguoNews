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
	/**�˳�*/
	private void logout(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {	
	  HttpSession session = request.getSession();
	  session.invalidate();//ʹ�ûỰʧЧ
	  //�ص�ϵͳ��ҳ
	  response.sendRedirect("/admin/index.jsp");
	}

   /**��¼*/
	private void login(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		//��ȡ�û���������
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			//�жϿ�
			if(username.equals("")){
				request.setAttribute("error","�û�������Ϊ�գ�");
				//�ص���¼ҳ��
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
				return;
			}
			
			if(password.equals("")){
				request.setAttribute("error","���벻��Ϊ�գ�");
				//�ص���¼ҳ��
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
				return;
			}
			
			UserInfo loginUser = userDao.queryUserByName(username);
			if(loginUser==null){
				request.setAttribute("error", "�û��������ڣ�");
				//�ص���¼����
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			}else if(!loginUser.getPassword().equals(password)){
				request.setAttribute("error","�������");
				//�ص���¼����
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			}else{
				//��½�ɹ��������̨������ҳ
				//��ȡ�Ƿ���Ҫ��ס��¼���û�
				String isSave = request.getParameter("isSave");
				//����cookie����
				Cookie c1 = new Cookie("username",username);
				Cookie c2 = new Cookie("password",password);
				//System.out.println(isSave+"------------------------");
			if(isSave!=null && isSave.equals("on")){
					//����cookie�Ĵ����
					c1.setMaxAge(60*60*60*30);
					c2.setMaxAge(60*60*60*30);	
				}else{
					//ɾ�����е�cookie
					c1.setMaxAge(0);
					c2.setMaxAge(0);
				}
				//����cookie���ͻ��������
				response.addCookie(c1);
				response.addCookie(c2);
				
				//����ǰ��¼���û�����Ự������
				/*
				 * HttpSession session = request.getSession();//��ȡ��ǰ����������ĻỰ����
				 * session.setAttribute("loginUser",loginUser);
				 */
				
				request.getSession().setAttribute("loginUser",loginUser);
				
				//request.setAttribute("loginUser",loginUser);
				request.getRequestDispatcher("/admin/manager_index.jsp").forward(request, response);
			}
			
			}	
	}




