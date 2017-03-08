package com.simple.bsp.usernewstype.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.usernewstype.po.UserNewsType;

@Service("newsTypeService")
public class NewsTypeService {

	@Autowired
	private DBUtil util;

	/**
	 * 根据当前用户人Id查找其所拥有的id
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getAllNewsTypeByUserId(String userId) {

		// String sql =
		// "select news_type_code as newsTypeCode,p.param_name as newsTypeName "
		// +
		// "from user_news_type t, pub_sys_param p where t.news_type_code=p.param_value and"
		// + " p.param_code='NEWSTYPE' and t.user_id='"
		// + userId + "' order by cast(p.param_value as SIGNED) ";
		String sql = "select param_value as newsTypeCode,p.param_name as newsTypeName "
				+ "from pub_sys_param p" + " where p.param_code='NEWSTYPE' order by cast(p.param_value as SIGNED) ";
		return util.getMapList(sql, new Object[] {});
	}

	public List<Map<String, Object>> getAllActivityTypeByUserId(String userId) {

		String sql = "select activity_type_code as activityTypeCode,p.param_name as activityTypeName "
				+ "from user_activity_type t, pub_sys_param p where t.activity_type_code=p.param_value and"
				+ " p.param_code='ACTIVITYTYPE' and t.user_id='"
				+ userId
				+ "' order by cast(p.param_value as SIGNED) ";
		return util.getMapList(sql, new Object[] {});
	}

	public List<Map<String, Object>> getAllNewsType(String userId) {
		String sql = "select p.param_value as newsTypeCode,p.param_name as newsTypeName "
				+ " from pub_sys_param p where  p.param_code='NEWSTYPE' and p.param_value not "
				+ " in (select news_type_code from user_news_type where user_id='"
				+ userId + "') order by cast(p.param_value as SIGNED) ";
		return util.getMapList(sql, new Object[] {});
	}

	public List<Map<String, Object>> getAllActivityType(String userId) {
		String sql = "select p.param_value as activityTypeCode,p.param_name as activityTypeName "
				+ " from pub_sys_param p where  p.param_code='ACTIVITYTYPE' and p.param_value not "
				+ " in (select activity_type_code from user_activity_type where user_id='"
				+ userId + "') order by cast(p.param_value as SIGNED) ";
		return util.getMapList(sql, new Object[] {});
	}

	public int addNewsTypeToUser(UserNewsType userNewsType) {
		String sql = "insert into user_news_type (user_news_type_id,user_id,news_type_code)values"
				+ " (:userNewsTypeId,:userId,:newsTypeCode )";
		return util.editObject(sql, userNewsType);
	}

	public int delNewsType(String userId) {
		String sql = "delete from user_news_type where user_id='" + userId
				+ "'";
		return util.editObject(sql, new Object[] {});
	}

	public int delActivityType(String userId) {
		String sql = "delete from user_activity_type where user_id='" + userId
				+ "'";
		return util.editObject(sql, new Object[] {});
	}

}
