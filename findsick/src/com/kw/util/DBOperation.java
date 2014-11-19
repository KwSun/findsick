package com.kw.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperation {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
    private static final String DBDRIVER ="org.sqlite.JDBC";	
    //private static final String url="jdbc:mysql://localhost:3306/illcheck?characterEncoding=utf8";
   /* private static final String username="root";
    private static final String password="930820";
   int courseNo = 100;    //疾病编号起始位置
    int teacherNo = 1000;
    int studentNo = 10000;*/

	public DBOperation() {
		try {
			Class.forName( DBDRIVER);
			//conn = DriverManager.getConnection(url,username,password);
			conn = DriverManager.getConnection("jdbc:sqlite:db/illcheck.db");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
		}catch (SQLException e1) {
		}
	}
	/**
	 * 数据库查询模块
	 */
	public ResultSet Query(String sql) {
			try {
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
			}
		return rs;
	}
	/**
	 * 数据库执行、更新、删除模块
	 */
	public void TheAll(String sql){
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
		}
	}
	/**
	 * 关闭数据库链接模块
	 */
	public void CloseAll() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException ac) {
			ac.printStackTrace();
		}
	}
	/**
	 * 获取记录集总数模块
	 */
	public int getTotalRow(String sql){//获取记录集总数
		DBOperation dbo = new DBOperation();
		ResultSet rsline = dbo.Query(sql);
		int i=0;
		try{
		while(rsline.next())
		{
			i++;
		}
		dbo.CloseAll();
		}catch(Exception er){
		}
		return i;
	}
	
	/**
	 * 获取主键最大值,然后加“1”模块
	 */
	  public String getMax(String table, String IDItem) {
		    //生成根据ID项从高到底排序的查询语句
		    DBOperation dbo = new DBOperation();
		    String sql = "select  "+IDItem+"   from   "+table+"   order   by   "+IDItem+"   desc";
		    String id,i = null;
		    try {
		      //得到结果集
		      ResultSet rs = dbo.Query(sql);
		      if (rs.next()) {
		        //如果数据库非空，得到第一条记录，也就是ID值最大的记录
		        id = rs.getString(IDItem);
		        //ID值增加1，得到新ID值
		        i=(Integer.parseInt(id)+1)+"";
		      }
		      /*
		       * 根据表，自动判断插入的初始值
		       */
		      else {
		    	  if (table == "Student"){
		    		  i = "10000";
		    	  } else if (table == "Teacher"){
		    		  i = "1000";
		    	  } else if (table == "Course"){
		    		  i = "100";
		    	  }
		      }
		      dbo.CloseAll();
		    }
		    catch (Exception e) {
		    }
		    return i;
		  }
}