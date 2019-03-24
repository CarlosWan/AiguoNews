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
 * ִ���û�������������
 */
public class UserAdminServlet extends HttpServlet {
	private UserDao userDao = new UserDao();
	private PageData pageData = new PageData(); //��ҳ���ݶ���
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���������ַ�������
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
	
	/**ִ������ɾ���û�����*/
	private void deleteUserBatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡҪ����ɾ����id
		String userIds = request.getParameter("userIds");
		//�����ŷָ�    "14,15,16"
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
		
		String msg = flag ? "����ɾ���ɹ�!" : "����ɾ��ʧ��!";
		request.setAttribute("msg", msg);
		//�����û�����ҳ��
		gotoUserManage(request, response);
	}
	
	
	/**ִ��ɾ���û�����*/
	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡҪɾ�� ���û�Id
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		//�������ݿ�,ִ��ɾ��
		boolean flag = userDao.deleteUserById(userId);
		
		String msg = flag ? "ɾ���ɹ�!" : "ɾ��ʧ��!";
		request.setAttribute("msg", msg);
		//�����û�����ҳ��
		gotoUserManage(request, response);
	}
	

	/**ִ������û�����*/
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��������Ĳ���
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		int status = Integer.parseInt(request.getParameter("status"));
		
		//�Ȳ�ѯ�û����Ƿ�ռ��
		UserInfo user = userDao.queryUserByName(username);
		if(user!=null){
			request.setAttribute("error", "�û�����ռ��!");
			request.setAttribute("username",username);
			request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
			return;
		}
		
		//����û���û�б�ռ��
		user = new UserInfo(username, password, realname, gender, telephone, email, status);
		
		//�������ݿ�,���UserInfo����
		boolean flag = userDao.addUser(user);
		if(flag){
			//�ɹ�,����user_manage.jsp
			request.setAttribute("msg", "�����û��ɹ�!");
			gotoUserManage(request,response); //�����û�����ҳ��
		}else{
			request.setAttribute("error", "�û����ʧ��!");
			request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
		}
	}
	

	/**׼������add_user.jsp*/
	private void gotoAddUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/admin/add_user.jsp").forward(request,response);
	}

	/**׼������user_manage.jsp*/
	private void gotoUserManage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ѯ������
		String conditionType = request.getParameter("conditionType");
		String queryCondition = request.getParameter("queryCondition");
		int ctype = 0;
		
		if(conditionType!=null){
			ctype =Integer.parseInt(conditionType);
		}
		
		
		//��ȡ����Ĳ��� currPage
		String currPage = request.getParameter("currPage");
		
		
		//ȷ����ҳʵ����ܼ�¼��
		int maxCount = userDao.getMaxCount(ctype,queryCondition);
		pageData.setMaxCount(maxCount);
		
		if(currPage != null){
			if(currPage.equals("")){
				pageData.setCurrPage(1);
			}else{
				pageData.setCurrPage(Integer.parseInt(currPage)); //���õ�ǰҳ��
			}
		}
		
		
		
		List<UserInfo> userList = userDao.queryUserList(pageData,ctype,queryCondition);
		
		
		//�������������� 
		request.setAttribute("userList",userList);
		request.setAttribute("pageData",pageData);
		//��������������
		request.setAttribute("conditionType", conditionType);
		request.setAttribute("queryCondition", queryCondition);
		
		//����user_manage.jsp
		request.getRequestDispatcher("/admin/user_manage.jsp").forward(request,response);
	}

}
