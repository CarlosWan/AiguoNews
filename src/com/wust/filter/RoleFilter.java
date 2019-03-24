package com.wust.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wust.entity.UserInfo;

/**
 * Ȩ�޿��ƹ�����
 */
public class RoleFilter implements Filter {

	public void destroy() {


	}
	
	//ִ�й��˴���
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//ת��ΪHttpServletRequest
		HttpServletRequest request = (HttpServletRequest)req;
		//�ж��û��Ƿ��¼
		HttpSession session = request.getSession();
		//��ȡ�Ự�������е�¼���û�
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		if(loginUser == null){
			request.setAttribute("error", "�Բ���,���ȵ�¼!");
			request.getRequestDispatcher("index.jsp").forward(request, resp);
		}else if(loginUser.getStatus()==0){
			request.setAttribute("error", "�Բ���,Ȩ�޲���!");
			request.getRequestDispatcher("index.jsp").forward(request, resp);
		}else{
			//������ת�����������е���һ��������,�������������û�������Ĺ������������󵽴�Ŀ����Դ
			chain.doFilter(req, resp);
		}
			
	}

	public void init(FilterConfig arg0) throws ServletException {


	}

}
