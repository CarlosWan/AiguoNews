package com.wust.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wust.entity.NoticeInfo;
import com.wust.entity.PageData;
import com.wust.util.DBUtil;

public class NoticeDao {
	   private Connection conn = null;
	   private Statement stmt = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rs = null;


	public int getMaxCount(String queryCondition) {
		try {
			String sql = "select count(*) from notice where 1=1";
			if( queryCondition!=null && !queryCondition.equals("")){
				sql +=" and content like '%"+queryCondition+"%'";				
			}
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
		return 0;
	}


	public List<NoticeInfo> queryNoticeList(PageData pageData, String queryCondition) {
		try {
			int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
			int pageSize = pageData.getPageSize();
			List<NoticeInfo> noticeList = new ArrayList<NoticeInfo>();
			
			StringBuffer sf = new StringBuffer("select * from notice where 1=1");
			if(queryCondition!=null && !queryCondition.equals("")){
				sf.append(" and content like '%"+queryCondition+"%'");				
			}
			sf.append(" limit ?,?");
			
			String sql = sf.toString();//将StringBuffer转换为String
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,startIndex);
			pstmt.setInt(2,pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				NoticeInfo notice = new NoticeInfo();
				notice.setNoticeId(rs.getInt(1));
				notice.setNoticecontent(rs.getString(2));
				notice.setNoticedate(rs.getString(3));
				
				noticeList.add(notice);
				
			}
			return noticeList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
		return null;
	}
	
	//搜索主题
	public NoticeInfo queryNoticeByContent(String noticecontent) {
		NoticeInfo notice = null;
		try {
			String sql = "select * from notice where content=?";
			conn = DBUtil.getConnection();
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,noticecontent);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				notice = new NoticeInfo();
				notice.setNoticeId(rs.getInt(1));
				notice.setNoticecontent(rs.getString(2));
				notice.setNoticedate(rs.getString(3));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			DBUtil.closeAll(conn,pstmt,null,rs);
		}
		return notice;
	}


	public boolean addNotice(NoticeInfo notice) {
		try {
	
		String sql = "insert into notice(content,date)"+
        "values(?,?)";

        conn = DBUtil.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, notice.getNoticecontent());
		pstmt.setString(2, notice.getNoticedate());
		
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

  //根据Id删除主题
	public boolean deleteNoticeById(int noticeId) {
		try {
			String sql = "delete from notice where notice_id="+noticeId;
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			int rows = stmt.executeUpdate(sql);
			if(rows>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, null, stmt, null);
		}
		return false;
	}
}
