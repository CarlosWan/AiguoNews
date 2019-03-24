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
 * ���ܣ��������书�ܴ���
 * @author
 */

public class RegistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		String sessionCode = (String) req.getSession().getAttribute("code");
		System.out.println(sessionCode);
		//  ��ȡsession�е���֤��
		if(sessionCode != null) {
			//  ��ȡҳ���ύ����֤��
			String inputCode = req.getParameter("code");
			System.out.println("ҳ���ύ����֤��:" + inputCode);
			if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
				// ���û����������һϵ����Ϣ���뵽���ݿ���
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
				
				//  ��֤�ɹ�����ת�ɹ�ҳ��
				req.setAttribute("username", username);
				req.getRequestDispatcher("/success.jsp").forward(req, resp);
			}else {
				//  ��֤ʧ��
				req.getRequestDispatcher("/fail.jsp").forward(req, resp);
			}
		}else {
			//  ��֤ʧ��
			req.getRequestDispatcher("/fail.jsp").forward(req, resp);
		}
		//  �Ƴ�session�е���֤��
		req.removeAttribute("code");
	}
}

