package com.wust.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wust.entity.NewsType;
import com.wust.entity.PageData;
import com.wust.util.DBUtil;

public class TypeDao {
	   private Connection conn = null;
	   private Statement stmt = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rs = null;


	public int getMaxCount(String queryCondition) {
		try {
			String sql = "select count(*) from news_type where 1=1";
			if( queryCondition!=null && !queryCondition.equals("")){
				sql +=" and typename like '%"+queryCondition+"%'";				
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


	public List<NewsType> queryTypeList(PageData pageData, String queryCondition) {
		try {
			int startIndex = pageData.getPageSize()*(pageData.getCurrPage()-1);
			int pageSize = pageData.getPageSize();
			List<NewsType> typeList = new ArrayList<NewsType>();
			
			StringBuffer sf = new StringBuffer("select * from news_type where 1=1");
			if(queryCondition!=null && !queryCondition.equals("")){
				sf.append(" and typename like '%"+queryCondition+"%'");				
			}
			sf.append(" limit ?,?");
			
			String sql = sf.toString();//将StringBuffer转换为String
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,startIndex);
			pstmt.setInt(2,pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				NewsType type = new NewsType();
				type.setTypeId(rs.getInt(1));
				type.setTypename(rs.getString(2));
				type.setTypedescription(rs.getString(3));
				
				typeList.add(type);
				
			}
			return typeList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, null, stmt, rs);
		}
		return null;
	}
	
	//搜索主题
	public NewsType queryTypeByName(String typename) {
		NewsType type = null;
		try {
			String sql = "select * from news_type where typename=?";
			conn = DBUtil.getConnection();
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,typename);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				type = new NewsType();
				type.setTypeId(rs.getInt(1));
				type.setTypename(rs.getString(2));
				type.setTypedescription(rs.getString(3));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			DBUtil.closeAll(conn,pstmt,null,rs);
		}
		return type;
	}


	public boolean addType(NewsType type) {
		try {
	
		String sql = "insert into news_type(typename,typedescr)"+
        "values(?,?)";

        conn = DBUtil.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, type.getTypename());
		pstmt.setString(2, type.getTypedescription());
		
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
	public boolean deleteTypeById(int typeId) {
		try {
			String sql = "delete from news_type where type_id="+typeId;
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
