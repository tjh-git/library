/**
 * 
 */
package com.simple.bsp.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author simple
 *
 */
public class GetJDBCConnection {
	
	private static Logger logger = Logger.getLogger(GetJDBCConnection.class);
	
	public static Connection conn = null;
	
	/**
	 * 获取JDBC数据库连接
	 * @return
	 */
	public static synchronized Connection getJDBCConnection(){
		
	    try{
	    	Resource resource = new ClassPathResource("/database.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String className = props.getProperty("mysql.driverClassName");
			String url = props.getProperty("mysql.url");
			String userName = props.getProperty("mysql.username");
			String password = props.getProperty("mysql.password");
			Class.forName(className);
	    	conn = DriverManager.getConnection(url , userName , password);
	    	
	    }catch(Exception e){
	    	logger.error("获取数据库连接失败!错误信息:["+e.getMessage()+"]");
	    	e.printStackTrace() ;   
	    }
	    return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		if(null != conn){
			try {
				
				conn.close();
				
			} catch (SQLException e) {
				logger.error("关闭数据库连接失败!错误信息:["+e.getMessage()+"]");
				e.printStackTrace();
			}
		}
	}

}
