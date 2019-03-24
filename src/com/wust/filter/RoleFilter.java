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
 * 权限控制过滤器
 */
public class RoleFilter implements Filter {

	public void destroy() {


	}
	
	//执行过滤处理
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//转换为HttpServletRequest
		HttpServletRequest request = (HttpServletRequest)req;
		//判断用户是否登录
		HttpSession session = request.getSession();
		//获取会话作用域中登录的用户
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		if(loginUser == null){
			request.setAttribute("error", "对不起,请先登录!");
			request.getRequestDispatcher("index.jsp").forward(request, resp);
		}else if(loginUser.getStatus()==0){
			request.setAttribute("error", "对不起,权限不足!");
			request.getRequestDispatcher("index.jsp").forward(request, resp);
		}else{
			//将请求转至过滤器链中的下一个过滤器,如果过滤器链中没有其他的过滤器，则请求到达目标资源
			chain.doFilter(req, resp);
		}
			
	}

	public void init(FilterConfig arg0) throws ServletException {


	}

}
