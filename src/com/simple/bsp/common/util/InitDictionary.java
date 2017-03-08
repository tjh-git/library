package com.simple.bsp.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class InitDictionary extends HttpServlet {
	private static Logger logger = Logger.getLogger(InitDictionary.class);

	/**
	 * 读取系统参数
	 */
	public void init() throws ServletException {

		Connection conn = null;

		try {
			logger.info("加载数据字典开始...");
			conn = GetJDBCConnection.getJDBCConnection();
			Statement stmt = conn.createStatement();

			String sql = "SELECT id,num from pub_dictionary where father_id in (select id from pub_dictionary where num='root')";
			ResultSet rs = stmt.executeQuery(sql);
			List<Map<String, String>> flist = new ArrayList<Map<String, String>>();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("num", rs.getString("num"));
				flist.add(map);
			}

			for (Map<String, String> m : flist) {
				Map<String, String> map = new HashMap<String, String>();
				List<Map<String, String>> list = new ArrayList<Map<String,String>>();
				
				String subsql = "SELECT num,name FROM pub_dictionary where father_id='"
						+ m.get("id") + "' order by order_num";
				stmt = conn.createStatement();
				ResultSet subrs = stmt.executeQuery(subsql);
				while (subrs.next()) {
					map.put(subrs.getString("num"), subrs.getString("name"));
					Map<String, String> submap = new HashMap<String, String>();
					submap.put("key", subrs.getString("num"));
					submap.put("value", subrs.getString("name"));
					list.add(submap);
				}

				Dictionary.setDictionary(m.get("num").toString(), map);
				Dictionary.setListDictionary(m.get("num").toString(), list);
			}

			logger.info("加载数据字典结束!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("InitDictionnary读取数据字典错误，错误信息[" + e.getMessage() + "]");
		} finally {
			if (null != conn) {
				GetJDBCConnection.closeConnection(conn);
			}
		}
	}
}
