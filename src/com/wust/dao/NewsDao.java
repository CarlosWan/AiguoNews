package com.wust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wust.entity.NewsInfo;
import com.wust.entity.PageData;



import com.wust.util.DBUtil;

public class NewsDao {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	
	public List<NewsInfo> queryNewsList() {
		try {
			List<NewsInfo> newsList = new ArrayList<NewsInfo>();
			NewsInfo news = null;
			String sql = "select * from news_info ";
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			//pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, newsname);
			
			rs=stmt.executeQuery(sql);//执行SQL
			while(rs.next()){          //处理结果集
				news = new NewsInfo();
				news.setNewsid(rs.getInt(1));
				news.setTypeid(rs.getInt(2));
				news.setNewstitle(rs.getString(3));
				news.setNewsauthor(rs.getString(4));
				news.setNewssummary(rs.getString(5));
				news.setNewscontent(rs.getString(6));
				news.setNewspic(rs.getString(7));
				news.setNewspubdate(rs.getString(8)); 
				
				
				newsList.add(news);
				
			}
			return newsList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
		return null;
	}

    

	public int getMaxCount(int sti,
			String queryAuthorName, String queryNewsTitle, String queryPubDate ) {
		try {
			StringBuffer sb = new StringBuffer("select count(*) from news_info where 1=1");
			if(sti!=0){
			    sb.append(" and type_id = "+sti);
			}
			if(queryAuthorName != null && !queryAuthorName.equals("")){
				sb.append(" and news_author  like '%"+queryAuthorName+"%'");
			}
			if(queryNewsTitle != null && !queryNewsTitle.equals("")){
				sb.append(" and news_title like '%"+queryNewsTitle+"%'");
			}
			
			if(queryPubDate != null && !queryPubDate.equals("")){
				sb.append(" and news_pubdate like '%"+queryPubDate.substring(0, 4)+'-'+queryPubDate.substring(5, 7)+'-'+queryPubDate.substring(8, 10)+"%'");
		    }
			
			String sql = sb.toString();
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			//pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, newsname);
			
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
			
		return 0;
	}



	public List<NewsInfo> queryNewsList(PageData pageData) {
		try {
			int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
			int pageSize = pageData.getPageSize();
			List<NewsInfo> newsList = new ArrayList<NewsInfo>();
			NewsInfo news = null;
			String sql = "select * from news_info limit ?,? ";
			//StringBuffer sb = new StringBuffer("select * from news_info where 1=1");
			//if(conditionType==1 && queryCondition != null && !queryCondition.equals("")){
				//sb.append(" and newsname like '%"+queryCondition+"%'");
			//}else if(conditionType==2 && queryCondition != null && !queryCondition.equals("")){
				//sb.append(" and realname like '%"+queryCondition+"%'");
			//}
			//sb.append(" limit ?,?");
			//String sql = sb.toString();
			conn = DBUtil.getConnection();
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				news = new NewsInfo();
				news.setNewsid(rs.getInt(1));
				news.setTypeid(rs.getInt(2));
				news.setNewstitle(rs.getString(3));
				news.setNewsauthor(rs.getString(4));
				news.setNewssummary(rs.getString(5));
				news.setNewscontent(rs.getString(6));
				news.setNewspic(rs.getString(7));
				news.setNewspubdate(rs.getString(8));
				
				newsList.add(news);
				
			}
			return newsList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, pstmt, null, rs);
		}
		
		return null;
	}
	
	public NewsInfo queryNewsByTitle(String newstitle) {
		NewsInfo news = null;
		try {
			String sql = "select * from news_info where news_title=?";
			conn = DBUtil.getConnection();
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newstitle);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				news = new NewsInfo();
				news.setNewsid(rs.getInt(1));
				news.setTypeid(rs.getInt(2));
				news.setNewstitle(rs.getString(3));
				news.setNewsauthor(rs.getString(4));
				news.setNewssummary(rs.getString(5));
				news.setNewscontent(rs.getString(6));
				news.setNewspic(rs.getString(7));
				news.setNewspubdate(rs.getString(8));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}finally{
			DBUtil.closeAll(conn, pstmt, null, rs);
		}
		return news;
	}




	public List<NewsInfo> queryNewsList(PageData pageData, int sti,
			String queryAuthorName, String queryNewsTitle, String queryPubDate) {
		try {
			int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
			int pageSize = pageData.getPageSize();
			List<NewsInfo> newsList = new ArrayList<NewsInfo>();
			NewsInfo news = null;
			//String sql = "select * from news_info limit ?,? ";
			StringBuffer sb = new StringBuffer("select * from news_info where 1=1");
			if(sti!=0){
			    sb.append(" and type_id = "+sti);
			}
			if(queryAuthorName != null && !queryAuthorName.equals("")){
				sb.append(" and news_author  like '%"+queryAuthorName+"%'");
			}
			if(queryNewsTitle != null && !queryNewsTitle.equals("")){
				sb.append(" and news_title like '%"+queryNewsTitle+"%'");
			}
			
			if(queryPubDate != null && !queryPubDate.equals("")){
				sb.append(" and news_pubdate like '%"+queryPubDate.substring(0, 4)+'-'+queryPubDate.substring(5, 7)+'-'+queryPubDate.substring(8, 10)+"%'");
		    }
			sb.append(" limit ?,?");
			String sql = sb.toString();
			conn = DBUtil.getConnection();
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndex);
			pstmt.setInt(2, pageSize);
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				
				news = new NewsInfo();
				news.setNewsid(rs.getInt(1));
				news.setTypeid(rs.getInt(2));
				news.setNewstitle(rs.getString(3));
				news.setNewsauthor(rs.getString(4));
				news.setNewssummary(rs.getString(5));
				news.setNewscontent(rs.getString(6));
				news.setNewspic(rs.getString(7));
				news.setNewspubdate(rs.getString(8));
				
				newsList.add(news);
				
			}
			return newsList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, pstmt, null, rs);
		}
		
		return null;
	}






	public boolean addNews(NewsInfo news) {
		try {
			String sql = "insert into news_info(type_id,news_title,news_author,news_summary,news_content,news_pic) " +
					"values(?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,news.getTypeid());
			pstmt.setString(2,news.getNewstitle());
			pstmt.setString(3,news.getNewsauthor());
			pstmt.setString(4,news.getNewssummary());
			pstmt.setString(5,news.getNewscontent());
			pstmt.setString(6,news.getNewspic());
			
			
			int rows = pstmt.executeUpdate();
			if(rows>0){
				return true;
			}
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, pstmt, null, rs);
		}
		
		return false;
		
	}



	public NewsInfo queryNewsById(int newsid) {
		NewsInfo news = null;
		try {
			String sql = "select * from news_info where news_id=?";
			conn = DBUtil.getConnection();
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newsid);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				news = new NewsInfo();
				news.setNewsid(rs.getInt(1));
				news.setTypeid(rs.getInt(2));
				news.setNewstitle(rs.getString(3));
				news.setNewsauthor(rs.getString(4));
				news.setNewssummary(rs.getString(5));
				news.setNewscontent(rs.getString(6));
				news.setNewspic(rs.getString(7));
				news.setNewspubdate(rs.getString(8));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}finally{
			DBUtil.closeAll(conn, pstmt, null, rs);
		}
		return news;
	}


}
