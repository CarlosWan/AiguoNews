package com.wust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wust.entity.PageData;
import com.wust.entity.UserInfo;
import com.wust.util.DBUtil;

public class UserDao {
	private Connection conn = null; //���Ӷ���
	private Statement stmt = null; //������
	private PreparedStatement pstmt = null; //Ԥ���������� 1.Ԥ���봦��,Ч�ʸ�   ,2֧��set In�Ͳ���
	private ResultSet rs = null;   //���������
	

	/**
	 * �����û�����ѯ�û�����
	 * @param username
	 * @return
	 */
	public UserInfo queryUserByName(String username) {
		UserInfo user = null;
		try {
			String sql = "select * from user_info where username=?";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRealname(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setStatus(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally{
			DBUtil.closeAll(conn, pstmt, null, rs);
		}
		return user;
	}

	/**
	 * ��ѯ�����û��б�
	 * @return
	 */
	public List<UserInfo> queryUserList() {
		try {
			List<UserInfo> userList = new ArrayList<UserInfo>();
			String sql = "select * from user_info";
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); //ִ�� SQL
			while(rs.next()){ //��������
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRealname(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setStatus(rs.getInt(8));
				
				//��ӵ�������
				userList.add(user);
			}
			return userList; //System.exit(0)
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
		return null;
	}
	
	/**
	 * ��ѯ��ҳ�û��б�
	 * @return
	 */
	public List<UserInfo> queryUserList(PageData pageData,int conditionType,String queryCondition) {
		try {
			//������ʼ������
			int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
			//ȷ��ҳ�ߴ�
			int pageSize = pageData.getPageSize();
			List<UserInfo> userList = new ArrayList<UserInfo>();
			//String sql = "select * from user_info limit ?,?";
			StringBuffer sf = new StringBuffer("select * from user_info where 1=1");
			if(conditionType==1 && queryCondition!=null && !queryCondition.equals("")){
				sf.append(" and username like '%"+queryCondition+"%'");
			}else if(conditionType==2 && queryCondition!=null && !queryCondition.equals("")){
				sf.append(" and realname like '%"+queryCondition+"%'");
			}
			sf.append(" limit ?,?");
			String sql = sf.toString(); //��StringBufferת��ΪString 
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql); //����Ԥ����SQL���
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery(); //ִ�� SQL
			while(rs.next()){ //��������
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRealname(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setStatus(rs.getInt(8));
				
				//��ӵ�������
				userList.add(user);
			}
			return userList; //System.exit(0)
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
		return null;
	}

	/**
	 * ��ȡ���е��ܼ�¼��
	 * @return
	 */
	public int getMaxCount(int conditionType,String queryCondition) {
		try {
			String sql = "select count(*) from user_info where 1=1";
			if(conditionType==1 && queryCondition!=null && !queryCondition.equals("")){
				sql += " and username like '%"+queryCondition+"%'";
			}else if(conditionType==2 && queryCondition!=null && !queryCondition.equals("")){
				sql += " and realname like '%"+queryCondition+"%'";
			}
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
		return 0;
	}

	/**
	 * ����û�
	 * @param user
	 * @return
	 */
	public boolean addUser(UserInfo user) {
		try {
			String sql = "insert into user_info(username,password,realname,gender,telephone,email,status) " +
					"values(?,?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getRealname());
			pstmt.setString(4, user.getGender());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getEmail());
			pstmt.setInt(7, user.getStatus());
			
			int rows = pstmt.executeUpdate();
			if(rows>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn, pstmt, null, null);
		}
		return false;
	}

	/**
	 * ����Idɾ�� �û�
	 * @param userId
	 * @return
	 */
	public boolean deleteUserById(int userId) {
		try {
			String sql = "delete from user_info where user_id="+userId;
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			int rows = stmt.executeUpdate(sql);
			if(rows>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn, null, stmt, null);
		}
		return false;
	}

	/**
	 * ����idִ������ɾ��
	 * @param userIds
	 * @return
	 */
	public boolean deleteUserByIds(String userIds) {
		try {
			String sql = "delete from user_info where user_id in ("+userIds+")";
			System.out.println(sql);
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			int rows = stmt.executeUpdate(sql);
			if(rows>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn, null, stmt, null);
		}
		return false;
		
	}
	public boolean regUser(UserInfo user) {
		try {
			String sql = "insert into user_info(username,password,realname,gender,telephone,email,status) "+
					"values(?,?,?,?,?,?,0)";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getRealname());
			pstmt.setString(4, user.getGender());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getEmail());
			//pstmt.setInt(7, user.getStatus());
			
			int rows = pstmt.executeUpdate();
			if(rows>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, pstmt, null, null);
		}
		return false;
	}

}
