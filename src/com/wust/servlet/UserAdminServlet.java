package com.wust.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wust.dao.UserDao;
import com.wust.entity.PageData;
import com.wust.entity.UserInfo;

/**
 * 执行用户管理的相关请求
 */
public class UserAdminServlet extends HttpServlet {
	private UserDao userDao = new UserDao();
	private PageData pageData = new PageData(); //分页数据对象
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求字符集编码
		request.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
		if(method.equals("gotoUserManage")){
			gotoUserManage(request,response);
		}else if(method.equals("gotoAddUser")){
			gotoAddUser(request,response);
		}else if(method.equals("addUser")){
			addUser(request,response);
		}else if(method.equals("deleteUser")){
			deleteUser(request,response);
		}else if(method.equals("deleteUserBatch")){
			deleteUserBatch(request,response);
		}
	}
	
	/**执行批量删除用户操作*/
	private void deleteUserBatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取要批量删除的id
		String userIds = request.getParameter("userIds");
		//按逗号分割    "14,15,16"
		//String[] strArr = userIds.split(",");
		
//		boolean flag = true;
//		try {
//			for(String userId : strArr){
//				userDao.deleteUserById(Integer.parseInt(userId));
//			}
		
//		} catch (Exception e) {
//			flag = false;
//		}
		
		boolean flag = userDao.deleteUserByIds(userIds);
		
		String msg = flag ? "批量删除成功!" : "批量删除失败!";
		request.setAttribute("msg", msg);
		//进入用户管理页面
		gotoUserManage(request, response);
	}
	
	
	/**执行删除用户操作*/
	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取要删除 的用户Id
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		//访问数据库,执行删除
		boolean flag = userDao.deleteUserById(userId);
		
		String msg = flag ? "删除成功!" : "删除失败!";
		request.setAttribute("msg", msg);
		//进入用户管理页面
		gotoUserManage(request, response);
	}
	

	/**执行添加用户操作*/
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取表单中请求的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		int status = Integer.parseInt(request.getParameter("status"));
		
		//先查询用户名是否被占用
		UserInfo user = userDao.queryUserByName(username);
		if(user!=null){
			request.setAttribute("error", "用户名被占用!");
			request.setAttribute("username",username);
			request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
			return;
		}
		
		//如果用户名没有被占用
		user = new UserInfo(username, password, realname, gender, telephone, email, status);
		
		//访问数据库,添加UserInfo对象
		boolean flag = userDao.addUser(user);
		if(flag){
			//成功,进入user_manage.jsp
			request.setAttribute("msg", "新增用户成功!");
			gotoUserManage(request,response); //进入用户管理页面
		}else{
			request.setAttribute("error", "用户添加失败!");
			request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
		}
	}
	

	/**准备进入add_user.jsp*/
	private void gotoAddUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
	}

	/**准备进入user_manage.jsp*/
	private void gotoUserManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		int maxCount = userDao.getMaxCount(ctype,queryCondition);
		pageData.setMaxCount(maxCount);
		
		if(currPage != null){
			if(currPage.equals("")){
				pageData.setCurrPage(1);
			}else{
				pageData.setCurrPage(Integer.parseInt(currPage)); //设置当前页码
			}
		}
		
		
		
		List<UserInfo> userList = userDao.queryUserList(pageData,ctype,queryCondition);
		
		
		//存入请求作用域 
		request.setAttribute("userList",userList);
		request.setAttribute("pageData",pageData);
		//设置搜索的条件
		request.setAttribute("conditionType", conditionType);
		request.setAttribute("queryCondition", queryCondition);
		
		//进入user_manage.jsp
		request.getRequestDispatcher("/admin/user_manage.jsp").forward(request,response);
	}

}
