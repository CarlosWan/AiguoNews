package com.wust.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.NoticeDao;
import com.wust.entity.NoticeInfo;
import com.wust.entity.PageData;

public class NoticeAdminServlet extends HttpServlet {
    private NoticeDao noticeDao = new NoticeDao();
    private PageData pageData = new PageData();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String method = request.getParameter("method");
		if(method.equals("gotoNoticeManage")){
			gotoNoticeManage(request,response);
		}else if(method.equals("gotoAddNotice")){
			gotoAddNotice(request,response);
		}else if(method.equals("addNotice")){
			addNotice(request,response);
		}else if(method.equals("deleteNotice")){
			deleteNotice(request,response);
		}
		
	}
	

  //执行删除主题
	private void deleteNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		int noticeId = Integer.parseInt(request.getParameter("noticeId"));
		//访问数据库，执行删除
		boolean flag = noticeDao.deleteNoticeById(noticeId);
		String msg = flag ? "删除成功" : "删除失败";
		request.setAttribute("msg",msg );
		gotoNoticeManage(request,response);
	}


  //执行添加主题
	private void addNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        String noticecontent = request.getParameter("noticecontent");
        String noticedate = request.getParameter("noticedate");
        
        NoticeInfo notice = noticeDao.queryNoticeByContent(noticecontent);
        if(notice!=null){
        	request.setAttribute("error", "公告已存在");
        	request.getRequestDispatcher("/admin/add_notice.jsp").forward(request, response);
        	return;
        }
        
        //如果没有被占用
        notice = new NoticeInfo(noticecontent, noticedate);
        
        boolean flag = noticeDao.addNotice(notice);
        if(flag){
        	//成功，进入notice_manage.jsp
        	request.setAttribute("msg", "新增公告成功！");
        	gotoNoticeManage(request,response);//进入主题管理页面
        }else{
        	request.setAttribute("error", "公告添加失败！");
        	request.getRequestDispatcher("/admin/add_notice.jsp").forward(request, response);
        	
        }
	}
	

    private void gotoAddNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/admin/add_notice.jsp").forward(request,response);
		
	}



	private void gotoNoticeManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	//获取查询的条件
		String queryCondition = request.getParameter("queryCondition");

		//获取请求的参数currPage
   	    String currPage = request.getParameter("currPage");
    	
    	
    	//确定分页实体的总记录数
    	int maxCount = noticeDao.getMaxCount(queryCondition);
    	pageData.setMaxCount(maxCount);
        if(currPage!= null ){
        	if(currPage.equals("")){
        		pageData.setCurrPage(1);
        	}else{
    		pageData.setCurrPage(Integer.parseInt(currPage));
    	 }
        }
        
    	
		List<NoticeInfo> noticeList = noticeDao.queryNoticeList(pageData,queryCondition);
		
		
		//存入请求作用域
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageData", pageData);
		//设置搜索的条件
	
		request.setAttribute("queryCondition", queryCondition);	
		
		//进入type_manage.jsp
		request.getRequestDispatcher("/admin/notice_manage.jsp").forward(request, response);
	}

}
