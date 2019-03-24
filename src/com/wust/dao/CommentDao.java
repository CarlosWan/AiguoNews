package com.wust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wust.entity.PageData;
import com.wust.entity.Comment;
import com.wust.entity.UserInfo;
import com.wust.util.DBUtil;

public class CommentDao {
	
	private Connection conn = null;//连接对象
	private Statement stmt = null;//语句对象
	private PreparedStatement pstmt = null;//预编译语句对象
	private ResultSet rs = null;//结果集对象

	
	
    /**
     * 查询所有评论列表
     * @return
     */
	public List<Comment> queryUserComment() {
		try {
			List<Comment> newsList = new ArrayList<Comment>();
			String sql = "select * from news_comment ";
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); //执行 SQL
			while(rs.next()){ //处理结果集
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt(1));
				comment.setUserId(rs.getInt(2));
				comment.setNewsId(rs.getInt(3));
				comment.setIpaddr(rs.getString(4));
				comment.setContent(rs.getString(5));
				comment.setDate(rs.getDate(6));
			    //添加到集合中
			    newsList.add(comment);
			    
			}
			return newsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn,null,stmt,rs);
		}
	    
		return null;
	}
	
	/**
     * 查询所有评论列表
     * @return
     */
	public List<Comment> queryCommentList(PageData pageData,int conditionType,String queryCondition) {
		try {
			//计算起始索引号
			int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
			//确定页尺寸
			int pageSize = pageData.getPageSize();
			List<Comment> userComment =new ArrayList<Comment>();
			StringBuffer sf = new StringBuffer("select * from news_comment where 1=1");
			if(conditionType==1 && queryCondition!=null && !queryCondition.equals("")){ 
				sf.append(" and IP_address like '%" +queryCondition+"%'");
			}else if(conditionType==2 && queryCondition!=null && !queryCondition.equals("")){ 
				sf.append(" and C_comment like '%" +queryCondition+"%'");
			}
			sf.append(" limit ?,?");		
			String sql = sf.toString();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql); //生成预编译SQL语句
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery(); //执行sql
			while(rs.next()){ //处理结果集
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt(1));
				comment.setUserId(rs.getInt(2));
				comment.setNewsId(rs.getInt(3));
				comment.setIpaddr(rs.getString(4));
				comment.setContent(rs.getString(5));
				comment.setDate(rs.getDate(6));
			    
			    //添加到集合中
			    userComment.add(comment);
			   
			}
			return userComment;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeAll(conn,null,stmt,rs);
		}
	    
		return null;
	}
	
	/**
	 * 获取表中的总记录数
	 * @return
	 */

	public int getMaxCount(int conditionType,String queryCondition) {
		try {
			String sql = "select count(*) from news_comment where 1=1";
			if(conditionType==1 && queryCondition!=null && !queryCondition.equals("")){ 
				sql +=" and IP_address like '%" +queryCondition+"%'";
			}else if(conditionType==2 && queryCondition!=null && !queryCondition.equals("")){ 
				sql +=" and C_comment like '%" +queryCondition+"%'";
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
	
	public boolean deleteComment(int commentId){
		
		try {
			String sql = "delete from news_comment where comment_id="+commentId;
			
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			
			int rows = stmt.executeUpdate(sql);
			if(rows>0){
				return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, null, pstmt, null);
			}
			return false;
	}
	
	public boolean deleteCommentByIds(String commentIds){
		
		try {
			String sql = "delete from news_comment where comment_id in("+commentIds+")";
			
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			
			int rows = stmt.executeUpdate(sql);
			if(rows>0){
				return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, null, pstmt, null);
			}
			return false;
	}
	
	public boolean addComment(Comment comment) {
		try {
			String sql = "insert into user_info(userId,newsId,ipaddr,content) " +
					"values(?,?,?,?)";
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getUserId());
			pstmt.setInt(2, comment.getNewsId());
			pstmt.setString(3, comment.getIpaddr());
			pstmt.setString(4, comment.getContent());
		
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
	
}

