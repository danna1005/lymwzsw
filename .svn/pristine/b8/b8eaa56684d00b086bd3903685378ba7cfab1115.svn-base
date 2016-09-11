package com.thiscc.tools.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shensoft.tools.utils.FileUtils;


public class Jdbc {
	
	private static Log log = LogFactory.getLog(Jdbc.class);
	private String jdbc_driver_class;
	private String jdbc_url;
	private String jdbc_username;
	private String jdbc_password;

	public Jdbc(String paramString1, String paramString2, String paramString3,
			String paramString4) {
		this.jdbc_driver_class = paramString1;
		this.jdbc_url = paramString2;
		this.jdbc_username = paramString3;
		this.jdbc_password = paramString4;
	}

	public Jdbc() {
		String path = Jdbc.class.getResource("/jdbc.properties").toString();
		String os = System.getProperty("os.name");
		
		if ( os.startsWith("Windows") ) {
			path = path.replaceFirst("file:/", "");
		} else if ( os.startsWith("Linux") ) {
			path = path.replaceFirst("file:", "");
		}
		
		Properties localProperties = FileUtils.readProperties(path);
		this.jdbc_driver_class = localProperties.getProperty("jdbc.driver_class");
		this.jdbc_url = localProperties.getProperty("jdbc.url");
		this.jdbc_username = localProperties.getProperty("jdbc.username");
		this.jdbc_password = localProperties.getProperty("jdbc.password");
	}

	public Connection getConnection() {
		Connection localConnection = null;
		try {
			Class.forName(this.jdbc_driver_class);
		} catch (ClassNotFoundException localClassNotFoundException) {
			log.error(localClassNotFoundException.getMessage());
		}
		try {
			localConnection = DriverManager.getConnection(this.jdbc_url,
					this.jdbc_username, this.jdbc_password);
		} catch (SQLException localSQLException) {
			log.error(localSQLException.getMessage());
		}
		return localConnection;
	}

	public Statement getStatement(Connection paramConnection)
			throws SQLException {
		return paramConnection.createStatement();
	}

	public ResultSet getResultSet(Statement paramStatement, String paramString) {
		ResultSet localResultSet;
		try {
			localResultSet = paramStatement.executeQuery(paramString);
		} catch (SQLException localSQLException) {
			localResultSet = null;
			localSQLException.printStackTrace();
		}
		return localResultSet;
	}

	public int executeUpdate(Statement paramStatement, String paramString)
			throws SQLException {
		return paramStatement.executeUpdate(paramString);
	}

	public void rsClose(ResultSet paramResultSet) throws SQLException {
		paramResultSet.close();
	}

	public void connClose(Connection paramConnection) throws SQLException {
		paramConnection.close();
	}

	public int update(String paramString) throws SQLException {
		Connection localConnection = getConnection();
		int i = executeUpdate(localConnection.createStatement(), paramString);
		connClose(localConnection);
		return i;
	}
}