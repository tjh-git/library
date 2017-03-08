/**
 * 
 */
package com.simple.bsp.help.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.help.po.HelpInfo;

/**
 * @author simple
 *
 */
@Repository("helpDao")
public class HelpDao{
	@Autowired
	private DBUtil util;
	
	
	public Map<String, Object> getDataInfoPage(DataGridModel dgm,HelpInfo helpInfo){
		
		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		String sql=helpInfo.getHelpSql();//sql
		
		sql=sql.replaceAll("@param1@", helpInfo.getParam1());
		sql=sql.replaceAll("@param2@", helpInfo.getParam2());
		
		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from ( ").append(sql);

		// 获取结果集
		StringBuffer sqlSb = new StringBuffer(sql);

		//拼装查询条件
		String queryKey=helpInfo.getQueryKey();
		String queryValue=helpInfo.getQueryValue();
		
		if (null != queryValue && !"".equals(queryValue)) {
			sqlSb.append(" and "+queryKey+" like '%")
					.append(queryValue).append("%' ");
			sumSql.append(" and "+queryKey+" like '%")
					.append(queryValue).append("%' ");
		}
		
		sumSql.append(") aaa where 1=1");
		
		
		// 组装查询条件
		Map<String, Object> params = new HashMap<String, Object>();

		// 组装分页定义
		String pageString = "";
		if (dgm.getRows() != 0) {
			pageString = " limit " + (dgm.getPage() - 1) * dgm.getRows() + ", "
					+ dgm.getRows();
		}

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(sqlSb.toString()+ pageString, params));

		return result;

	}
	
	public HelpInfo getHelpInfoById(String id){
		// TODO Auto-generated method stub
		
		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" id as id,");// 主键
		sqlSb.append(" help_query as helpQuery,");// 查询条件
		sqlSb.append(" help_show as helpShow,");// 展示列
		sqlSb.append(" help_sql as helpSql,");//sql
		sqlSb.append(" help_colum as helpColum");//查询列
		
		sqlSb.append(" from pub_help where 1=1 ");
		
		sqlSb.append(" and id=:id ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);

		return (HelpInfo) util.getObject(sqlSb.toString(), paramMap, HelpInfo.class);
	}
}
