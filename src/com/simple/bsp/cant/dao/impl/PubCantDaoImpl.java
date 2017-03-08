package com.simple.bsp.cant.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.bsp.cant.dao.PubCantDao;
import com.simple.bsp.common.util.DBUtil;

@Repository("pubCantDao")
public class PubCantDaoImpl implements PubCantDao {

	@Autowired
	private DBUtil util;

	@Override
	public List getAllListByParentId(String parentId) {
		// TODO Auto-generated method stub
		String quSql = "select c.CANT_CODE as cantCode, c.CANT_NAME as cantName, c.SHORT_NAME as shortName, c.CANT_TYPE as cantType,c.SUPER_CODE as superCode  from pub_cant c where 1=1 and c.SUPER_CODE='";
		quSql += parentId;
		quSql += "'";
		// quSql+=" order by orderNum";
		Map<String, Object> params = new HashMap<String, Object>();
		return util.getMapList(quSql, params);
	}

}
