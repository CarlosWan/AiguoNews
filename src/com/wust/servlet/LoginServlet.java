package com.wust.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uCode = request.getParameter("uCode");
		//��ȡ�Ự�������е���֤��
		String vcode = (String)request.getSession().getAttribute("vcode");
		
		if(uCode==null || "".equals(uCode) || !uCode.equalsIgnoreCase(vcode)){
			request.setAttribute("error", "��֤�����!");
			request.getRequestDispatcher("/registration.jsp").forward(request,
					response);
			return;
		}

	}

}
