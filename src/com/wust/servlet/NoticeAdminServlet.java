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
	

  //ִ��ɾ������
	private void deleteNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		int noticeId = Integer.parseInt(request.getParameter("noticeId"));
		//�������ݿ⣬ִ��ɾ��
		boolean flag = noticeDao.deleteNoticeById(noticeId);
		String msg = flag ? "ɾ���ɹ�" : "ɾ��ʧ��";
		request.setAttribute("msg",msg );
		gotoNoticeManage(request,response);
	}


  //ִ���������
	private void addNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        String noticecontent = request.getParameter("noticecontent");
        String noticedate = request.getParameter("noticedate");
        
        NoticeInfo notice = noticeDao.queryNoticeByContent(noticecontent);
        if(notice!=null){
        	request.setAttribute("error", "�����Ѵ���");
        	request.getRequestDispatcher("/admin/add_notice.jsp").forward(request, response);
        	return;
        }
        
        //���û�б�ռ��
        notice = new NoticeInfo(noticecontent, noticedate);
        
        boolean flag = noticeDao.addNotice(notice);
        if(flag){
        	//�ɹ�������notice_manage.jsp
        	request.setAttribute("msg", "��������ɹ���");
        	gotoNoticeManage(request,response);//�����������ҳ��
        }else{
        	request.setAttribute("error", "�������ʧ�ܣ�");
        	request.getRequestDispatcher("/admin/add_notice.jsp").forward(request, response);
        	
        }
	}
	

    private void gotoAddNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/admin/add_notice.jsp").forward(request,response);
		
	}



	private void gotoNoticeManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	//��ȡ��ѯ������
		String queryCondition = request.getParameter("queryCondition");

		//��ȡ����Ĳ���currPage
   	    String currPage = request.getParameter("currPage");
    	
    	
    	//ȷ����ҳʵ����ܼ�¼��
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
		
		
		//��������������
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pageData", pageData);
		//��������������
	
		request.setAttribute("queryCondition", queryCondition);	
		
		//����type_manage.jsp
		request.getRequestDispatcher("/admin/notice_manage.jsp").forward(request, response);
	}

}
