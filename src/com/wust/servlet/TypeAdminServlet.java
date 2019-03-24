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
	

  //执行删除主题
	private void deleteType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		//访问数据库，执行删除
		boolean flag = typeDao.deleteTypeById(typeId);
		String msg = flag ? "删除成功" : "删除失败";
		request.setAttribute("msg",msg );
		gotoTypeManage(request,response);
	}


  //执行添加主题
	private void addType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        String typename = request.getParameter("typename");
        String typedescription = request.getParameter("typedescription");
        
        NewsType type = typeDao.queryTypeByName(typename);
        if(type!=null){
        	request.setAttribute("error", "主题名称被占用");
        	request.getRequestDispatcher("/admin/add_type.jsp").forward(request, response);
        	return;
        }
        
        //如果没有被占用
        type = new NewsType(typename, typedescription);
        
        boolean flag = typeDao.addType(type);
        if(flag){
        	//成功，进入type_manage.jsp
        	request.setAttribute("msg", "新增主题成功！");
        	gotoTypeManage(request,response);//进入主题管理页面
        }else{
        	request.setAttribute("error", "主题添加失败！");
        	request.getRequestDispatcher("/admin/add_type.jsp").forward(request, response);
        	
        }
	}
	

    private void gotoAddType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/admin/add_type.jsp").forward(request,response);
		
	}



	private void gotoTypeManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	//获取查询的条件
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String queryCondition = request.getParameter("queryCondition");

		//获取请求的参数currPage
   	    String currPage = request.getParameter("currPage");
    	
    	
    	//确定分页实体的总记录数
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
		
		
		//存入请求作用域
		request.setAttribute("typeList", typeList);
		request.setAttribute("pageData", pageData);
		//设置搜索的条件
	
		request.setAttribute("queryCondition", queryCondition);	
		
		//进入type_manage.jsp
		request.getRequestDispatcher("/admin/type_manage.jsp").forward(request, response);
	}

}
