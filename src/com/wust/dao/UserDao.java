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
	private Connection conn = null; //连接对象
	private Statement stmt = null; //语句对象
	private PreparedStatement pstmt = null; //预编译语句对象 1.预编译处理,效率高   ,2支持set In型参数
	private ResultSet rs = null;   //结果集对象
	

	/**
	 * 根据用户名查询用户对象
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
	 * 查询所有用户列表
	 * @return
	 */
	public List<UserInfo> queryUserList() {
		try {
			List<UserInfo> userList = new ArrayList<UserInfo>();
			String sql = "select * from user_info";
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); //执行 SQL
			while(rs.next()){ //处理结果集
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRealname(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setStatus(rs.getInt(8));
				
				//添加到集合中
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
	 * 查询分页用户列表
	 * @return
	 */
	public List<UserInfo> queryUserList(PageData pageData,int conditionType,String queryCondition) {
		try {
			//计算起始索引号
			int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
			//确定页尺寸
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
			String sql = sf.toString(); //将StringBuffer转换为String 
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql); //生成预编译SQL语句
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery(); //执行 SQL
			while(rs.next()){ //处理结果集
				UserInfo user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRealname(rs.getString(4));
				user.setGender(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setStatus(rs.getInt(8));
				
				//添加到集合中
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
	 * 获取表中的总记录数
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
	 * 添加用户
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
	 * 根据Id删除 用户
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
	 * 根据id执行批量删除
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
