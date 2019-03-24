package com.wust.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.NewsDao;
import com.wust.entity.NewsInfo;
import com.wust.entity.PageData;
import com.wust.entity.UserInfo;

public class NewsServlet extends HttpServlet {
    private NewsDao newsDao = new NewsDao();
    private PageData pageData = new PageData();
     
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method  = request.getParameter("method");
		
		if(method.equals("gotoNewsManage")){
			gotoNewsManage(request,response);
		}else if(method.equals("gotoAddNews")){
			gotoAddNews(request,response);
		}else if(method.equals("addNews")){
		    addNews(request,response);
		}
		
	}


	private void addNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int selectTypeId = Integer.parseInt(request.getParameter("selectTypeId"));
        String newstitle = request.getParameter("newsTitle");
        String newsauthor = request.getParameter("newsAuthor");
        String newssummary = request.getParameter("newsSummary");
        String newscontent = request.getParameter("newsContent");
        String newspic = request.getParameter("newsPic");
       
        
        
        
        NewsInfo news = newsDao.queryNewsByTitle(newstitle);
       
        
		
		if(news!=null){
			request.setAttribute("error", "标题名已存在");
			request.setAttribute("newsTitle", newstitle);
			request.getRequestDispatcher("/admin/add_news.jsp").forward(request, response);
			return;
		}
		
	    news = new NewsInfo(selectTypeId,newstitle,newsauthor,newssummary,newscontent,newspic);
		boolean flag = newsDao.addNews(news);
		
		
		System.out.println(flag);
		if(flag){
			request.setAttribute("msg", "添加新闻成功");
			gotoNewsManage(request,response);
			
		}else{
			request.setAttribute("error", "添加新闻失败");
			request.getRequestDispatcher("/admin/add_news.jsp").forward(request, response);
		}
		
		
        
        
	}


	private void gotoAddNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/admin/add_news.jsp").forward(request, response);
		
	}


	private void gotoNewsManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取分页信息
        
        String selectTypeId = request.getParameter("selectTypeId");
        String queryAuthorName = request.getParameter("queryAuthorName");
        String queryNewsTitle = request.getParameter("queryNewsTitle");
        String queryPubDate = request.getParameter("queryPubDate");
		
        int sti = 0;
		if(selectTypeId != null ){
			System.out.println(selectTypeId);
			sti = Integer.parseInt(selectTypeId);
		}
		String currentPage = request.getParameter("currentPage"); 
		
		int maxCount = newsDao.getMaxCount(sti,queryAuthorName,queryNewsTitle,queryPubDate);
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
		
		
		System.out.println(sti);
	     //创建新闻列表
		List<NewsInfo> newsList = newsDao.queryNewsList(pageData,sti,queryAuthorName,queryNewsTitle,queryPubDate);
		
		//存入作用域
		request.setAttribute("newsList", newsList);
		request.setAttribute("pageData", pageData);
		request.setAttribute("selectTypeId", selectTypeId);
		request.setAttribute("queryAuthorName", queryAuthorName);
		request.setAttribute("queryNewsTitle", queryNewsTitle);
		request.setAttribute("queryPubDate", queryPubDate);
		
		//跳转到news_manage.jsp
		request.getRequestDispatcher("news_manage.jsp").forward(request, response);
		
	}

	

}
