package com.thiscc.zm;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.thiscc.tools.sql.Jdbc;

public class TestDB {
	//mysql
	/*public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost/plusoft_test?useUnicode=true&characterEncoding=GBK";
	public static String user = "root";
	public static String pwd = "";*/
	
	//sqlserver
	/*public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String url = "jdbc:sqlserver://192.168.1.44:1434;DatabaseName=test;";
	public static String user = "sa";
	public static String pwd = "";*/ 	

    /////////////////////////////////////////////////////////////////
	private Connection getConn() throws Exception{		
		Jdbc jdbc = new Jdbc();
		return jdbc.getConnection();
	}	    
	@SuppressWarnings("unchecked")
	public ArrayList DBSelect(String sql) throws Exception{
    	Connection conn = getConn();		
		Statement stmt = conn.createStatement();
    	
        ResultSet rst = stmt.executeQuery(sql);		
		ArrayList list = ResultSetToList(rst);
		
		rst.close();
		stmt.close();
		conn.close();
		
        return list;
	}
	public JSONArray DbSelectJSON(String sql) throws Exception{
		Connection conn = getConn();		
		Statement stmt = conn.createStatement();
    	
        ResultSet rst = stmt.executeQuery(sql);		
        JSONArray list = ResultSetToArray(rst);
		
		rst.close();
		stmt.close();
		conn.close();
		
        return list;	
	}
	
	public List<Map<String,Object>> DbSelectList(String sql) throws Exception{
		Connection conn = getConn();		
		Statement stmt = conn.createStatement();
    	
        ResultSet rst = stmt.executeQuery(sql);		
        List<Map<String,Object>>  list = ResultToList(rst);
		
		rst.close();
		stmt.close();
		conn.close();
		
        return list;	
	}
	public void DBDelete(String sql) throws Exception{
		Connection conn = getConn();		
		Statement stmt = conn.createStatement();
		
        stmt.executeUpdate(sql);
                
		stmt.close();
		conn.close();
	}
	public void DBUpdate(String sql) throws Exception{
		Connection conn = getConn();		
		Statement stmt = conn.createStatement();
		
        stmt.executeUpdate(sql);
                
		stmt.close();
		conn.close();
	}
	public void DBInsert(String sql) throws Exception{
		Connection conn = getConn();		
		Statement stmt = conn.createStatement();
		
        stmt.executeUpdate(sql);
                
		stmt.close();
		conn.close();
	}
    private static ArrayList ResultSetToList(ResultSet rs) throws Exception{    	
    	ResultSetMetaData md = rs.getMetaData();
    	int columnCount = md.getColumnCount();
    	ArrayList list = new ArrayList();
    	Map rowData;
    	while(rs.next()){
	    	rowData = new HashMap(columnCount);
	    	for(int i = 1; i <= columnCount; i++)   {	 	    		
	    		Object v = rs.getObject(i);	    		
	    		
	    		if(v != null && (v.getClass() == Date.class || v.getClass() == java.sql.Date.class)){
	    			Timestamp ts= rs.getTimestamp(i);
	    			v = new java.util.Date(ts.getTime());
	    		}else if(v != null && v.getClass() == Clob.class){
	    			v = clob2String((Clob)v);
	    		}
	    		rowData.put(md.getColumnName(i),   v);
	    	}
	    	list.add(rowData);	    	
    	}
    	return list;
	}
    private static JSONArray ResultSetToArray(ResultSet rs) throws Exception{    	
 	    List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
 	    while (rs.next()) {
			 Map<String,Object> p = new HashMap<String,Object>();
			 for (int i=1; i<=rs.getMetaData().getColumnCount();i++){
			    p.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			 }
			 ls.add(p);
		}
 	    JSONArray json = JSONArray.fromObject(ls);
    	return json;
	} 
    private static List<Map<String,Object>> ResultToList(ResultSet rs) throws Exception{    	
 	    List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
 	    while (rs.next()) {
			 Map<String,Object> p = new HashMap<String,Object>();
			 for (int i=1; i<=rs.getMetaData().getColumnCount();i++){
			    p.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			 }
			 ls.add(p);
		}
    	return ls;
	} 
    private static String clob2String(Clob clob) throws Exception {
        return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
    }	    
}
