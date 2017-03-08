package com.simple.bsp.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class InitSysServlet extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(InitSysServlet.class);

	private static final long serialVersionUID = -6832856544655485237L;
	
	/**
	 * 读取系统参数
	 */
	public  void init() throws ServletException  {
		
		Connection conn = null;
		
		try{
			logger.info("加载系统参数开始...");
			conn = GetJDBCConnection.getJDBCConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "select param_code, param_value, param_status from pub_sys_param";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("param_code").toString().toLowerCase().startsWith("pub")){
					PubData.setPubData(rs.getString("param_code"), rs.getString("param_value"));
					logger.info("["+rs.getString("param_code")+"]-["+rs.getString("param_value")+"]加载完毕！");
				}
			}
			logger.info("加载系统参数结束!");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("InitSysServlet读取数据库参数错误，错误信息["+e.getMessage()+"]");
		}finally{
			if(null != conn){
				GetJDBCConnection.closeConnection(conn);
			}
		}
	}

}
