package com.wust.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.CommentDao;
import com.wust.dao.NewsDao;
import com.wust.entity.Comment;
import com.wust.entity.PageData;
import com.wust.entity.UserInfo;

public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID =1L;
	private NewsDao newsDao=new NewsDao();
	private PageData pageData = new PageData();
	private CommentDao commentDao = new CommentDao();
	private Comment comment = new Comment();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request,response);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String method = request.getParameter("method");
			
			if (method.equals("gotoCommentManage")){
				gotoCommentManage(request,response);
			}else if(method.equals("deleteComment")){
				deleteComment(request,response);
			}else if(method.equals("deleteCommentBatch")){
				deleteCommentBatch(request,response);
			}else if(method.equals("addComment")){
				addComment(request,response);
			}else if (method.equals("showComment")){
				showComment(request,response);
			}
	}


	private void showComment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("news_post.jsp").forward(request, response);
		
	}
	


	private void addComment(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			//获取表单中请求的参数
			int userId =Integer.parseInt(request.getParameter("userId"));
			int newsId =Integer.parseInt(request.getParameter("newsId"));
			String ipaddr = request.getParameter("ipaddr");
			String content = request.getParameter("content");
			
			comment=new Comment(userId,1,ipaddr,content);
			
			
			commentDao.addComment(comment);
			
			boolean flag = commentDao.addComment(comment);
			if(flag){
			
				request.setAttribute("msg", "新增评论成功!");
				gotoCommentManage(request,response); //进入用户管理页面
			}else{
				request.setAttribute("error", "评论添加失败!");
				request.getRequestDispatcher("/news_post.jsp").forward(request,response);
			}
		}



	private void deleteCommentBatch(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException { 
				
			//获取要批量删除的id
			String commentIds = request.getParameter("commentIds");
		
			boolean flag = commentDao.deleteCommentByIds(commentIds);
			
			String msg = flag ? "批量删除成功!" : "批量删除失败!";
			request.setAttribute("msg", msg);
			//进入评论管理页面
			gotoCommentManage(request, response);
		}



	private void deleteComment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int commentId=Integer.parseInt(request.getParameter("commentId"));
		
		boolean flag = commentDao.deleteComment(commentId);
		
		String msg= flag ? "删除成功!" : "删除失败";
		request.setAttribute("msg", msg);
		gotoCommentManage(request,response);
		
	}


	private void gotoCommentManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {{
			
				//获取查询的条件
				String conditionType = request.getParameter("conditionType");
				String queryCondition = request.getParameter("queryCondition");
				int ctype = 0;
				
				if(conditionType!=null){
					ctype =Integer.parseInt(conditionType);
				}
				
				
				//获取请求的参数 currPage
				String currPage = request.getParameter("currPage");
				
				
				//确定分页实体的总记录数
				int maxCount = commentDao.getMaxCount(ctype,queryCondition);
				pageData.setMaxCount(maxCount);
				
				if(currPage != null){
					if(currPage.equals("")){
						pageData.setCurrPage(1);
					}else{
						pageData.setCurrPage(Integer.parseInt(currPage)); //设置当前页码
					}
				}
				
				List<Comment> commentList = commentDao.queryCommentList(pageData,ctype,queryCondition);
				
				//存入请求作用域 
				request.setAttribute("commentList",commentList);
				request.setAttribute("pageData",pageData);
				//设置搜索的条件
				request.setAttribute("conditionType", conditionType);
				request.setAttribute("queryCondition", queryCondition);
				request.getRequestDispatcher("/admin/comment_manage.jsp").forward(request,response);
			}
	
  }
}
