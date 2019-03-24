package com.wust.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.NewsDao;
import com.wust.entity.NewsInfo;

public class DetailNewsServlet extends HttpServlet {

	 private NewsDao newsDao = new NewsDao();
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			this.doPost(request, response);
		}

		
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String method  = request.getParameter("method");
			
			if(method.equals("newsDetail")){
			       newsDetail(request,response);
			}
			
		}


		private void newsDetail(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			
			//List<NewsInfo> newsList = newsDao.queryNewsList(null,0,null,null,null);
			int newsid = Integer.parseInt(request.getParameter("newsid"));
			NewsInfo news = newsDao.queryNewsById(newsid);
			
			request.setAttribute("news",news);
			
			request.getRequestDispatcher("/news_post.jsp").forward(request, response);
			
		}

		

	}


