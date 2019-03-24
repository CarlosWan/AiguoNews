package com.wust.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.TypeDao;
import com.wust.entity.NewsType;
import com.wust.entity.PageData;

public class TypeAdminServlet extends HttpServlet {
    private TypeDao typeDao = new TypeDao();
    private PageData pageData = new PageData();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
		
	}

		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String method = request.getParameter("method");
		if(method.equals("gotoTypeManage")){
			gotoTypeManage(request,response);
		}else if(method.equals("gotoAddType")){
			gotoAddType(request,response);
		}else if(method.equals("addType")){
			addType(request,response);
		}else if(method.equals("deleteType")){
			deleteType(request,response);
		}
		
	}
	

  //ִ��ɾ������
	private void deleteType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		//�������ݿ⣬ִ��ɾ��
		boolean flag = typeDao.deleteTypeById(typeId);
		String msg = flag ? "ɾ���ɹ�" : "ɾ��ʧ��";
		request.setAttribute("msg",msg );
		gotoTypeManage(request,response);
	}


  //ִ���������
	private void addType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        String typename = request.getParameter("typename");
        String typedescription = request.getParameter("typedescription");
        
        NewsType type = typeDao.queryTypeByName(typename);
        if(type!=null){
        	request.setAttribute("error", "�������Ʊ�ռ��");
        	request.getRequestDispatcher("/admin/add_type.jsp").forward(request, response);
        	return;
        }
        
        //���û�б�ռ��
        type = new NewsType(typename, typedescription);
        
        boolean flag = typeDao.addType(type);
        if(flag){
        	//�ɹ�������type_manage.jsp
        	request.setAttribute("msg", "��������ɹ���");
        	gotoTypeManage(request,response);//�����������ҳ��
        }else{
        	request.setAttribute("error", "�������ʧ�ܣ�");
        	request.getRequestDispatcher("/admin/add_type.jsp").forward(request, response);
        	
        }
	}
	

    private void gotoAddType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/admin/add_type.jsp").forward(request,response);
		
	}



	private void gotoTypeManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	//��ȡ��ѯ������
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String queryCondition = request.getParameter("queryCondition");

		//��ȡ����Ĳ���currPage
   	    String currPage = request.getParameter("currPage");
    	
    	
    	//ȷ����ҳʵ����ܼ�¼��
    	int maxCount = typeDao.getMaxCount(queryCondition);
    	pageData.setMaxCount(maxCount);
        if(currPage!= null ){
        	if(currPage.equals("")){
        		pageData.setCurrPage(1);
        	}else{
    		pageData.setCurrPage(Integer.parseInt(currPage));
    	 }
        }
        
    	
		List<NewsType> typeList = typeDao.queryTypeList(pageData,queryCondition);
		
		
		//��������������
		request.setAttribute("typeList", typeList);
		request.setAttribute("pageData", pageData);
		//��������������
	
		request.setAttribute("queryCondition", queryCondition);	
		
		//����type_manage.jsp
		request.getRequestDispatcher("/admin/type_manage.jsp").forward(request, response);
	}

}
