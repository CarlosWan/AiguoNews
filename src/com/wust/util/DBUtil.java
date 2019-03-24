package com.wust.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	private static ComboPooledDataSource cpds = null;
	
	public static Connection getConnection(){
		Connection conn = null;
		if(cpds == null){
			cpds = new ComboPooledDataSource("mysql/news");
		}
		try {
			conn =  cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public static void closeAll(Connection conn,PreparedStatement pstmt,Statement stmt,ResultSet rs){
		try {
			if(rs !=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			
			if(pstmt!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void main(String[] args) {
    	Connection conn = DBUtil.getConnection();
    	System.out.println(conn!=null?"连接成功":"连接失败");
    }


	}

