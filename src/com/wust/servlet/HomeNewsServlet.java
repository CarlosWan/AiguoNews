package com.wust.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wust.dao.NewsDao;
import com.wust.entity.NewsInfo;
import com.wust.entity.PageData;

public class HomeNewsServlet extends HttpServlet {
	private NewsDao newsDao = new NewsDao();
    private PageData pageData = new PageData();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	private void gotoManage(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException{
		    String newstitle = request.getParameter("newstitle");
	        String newsauthor = request.getParameter("newsauthor");
	        String newspubdate = request.getParameter("newspubdate");
	        //String queryPubDate = request.getParameter("queryPubDate");
	        String typeid = request.getParameter("typeid");
	        int sti=0;
			if(typeid!= null){
				sti=Integer.parseInt(typeid);
			}
	       
			String currentPage = request.getParameter("currentPage"); 
			System.out.println(sti);
			int maxCount = newsDao.getMaxCount(sti,newsauthor,newstitle,newspubdate);
			pageData.setMaxCount(maxCount);
			
			if(currentPage != null){
				if(currentPage.equals("")){
					pageData.setCurrPage(pageData.getCurrPage()); 
				}else if(Integer.parseInt(currentPage)>pageData.getSumPages() || Integer.parseInt(currentPage)==0){
					pageData.setCurrPage(pageData.getCurrPage());
				}
			else{
				 pageData.setCurrPage(Integer.parseInt(currentPage));
				}
			}
			
			
			
		     //创建新闻列表
			List<NewsInfo> newsList = newsDao.queryNewsList(pageData,sti,newsauthor,newstitle,newspubdate);
			
			
			//存入作用域
			HttpSession session = request.getSession();
			session.setAttribute("newsList", newsList);
			request.setAttribute("pageData", pageData);
			request.setAttribute("newstitle", newstitle);
			request.setAttribute("newsauthor", newsauthor);
			request.setAttribute("newspubdate", newspubdate);
			//request.setAttribute("queryPubDate", queryPubDate);
			
			request.getRequestDispatcher("/userindex.jsp").forward(request, response);
			
		}


}

