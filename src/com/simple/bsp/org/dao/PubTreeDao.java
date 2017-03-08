/**
 * 
 */
package com.simple.bsp.org.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.org.po.PubTree;

/**
 * @author simple
 * 
 */
@Repository("pubTreeDao")
public class PubTreeDao {

	@Autowired
	private DBUtil util;


	/**
	 * 查询完整的树list
	 */
	@SuppressWarnings("unchecked")
	public List<PubTree> getPubTreeList() {
		String sql = "select t.id as \"id\", t.TREE_NAME as \"treeName\" "
				+ "from PUB_TREE t ";
		return (List<PubTree>) util.getObjList(sql, new Object[] {},
				PubTree.class);
	}



}
