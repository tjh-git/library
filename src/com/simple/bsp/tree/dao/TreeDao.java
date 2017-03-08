package com.simple.bsp.tree.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.Org;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import com.simple.bsp.tree.po.Tree;

@Repository("treeDao")
public class TreeDao {
	
	@Autowired
	private DBUtil util;
	
	/**
	 * 分页查询系统参数
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Tree tree) {

		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_tree where 1=1 ");

		// 获取结果集
		String quSql = "select t1.id ,t1.tree_name as treeName,t1.parent_id as parentId,t1.deep,t2.tree_name as parentName from pub_tree t1,pub_tree t2" +
				" where  t1.parent_id=t2.id  ";
		System.out.print(quSql);
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

//		// 点击查询按钮时组装查询语句
//		if (null != param.getParamCode() && !param.getParamCode().equals("")) {
//			sqlSb.append(" and param_code like :paramCode");
//			params.put("paramCode", "%" + param.getParamCode() + "%");
//			sumSql.append(" and param_code like '%")
//					.append(param.getParamCode()).append("%'");
//		}
//		if (null != param.getParamName() && !param.getParamName().equals("")) {
//			sqlSb.append(" and param_name like :paramName");
//			params.put("paramName", "%" + param.getParamName() + "%");
//			sumSql.append(" and param_name like '%")
//					.append(param.getParamName()).append("%'");
//		}

		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = " order by " + dgm.getSort() +" "+ dgm.getOrder();
		}

		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows",util.getObjList(pageQuerySql, params, Tree.class));
		return result;
	}
	
	
	/**
	 * 保存树信息
	 */
	public int saveTree(Tree tree) {
		String sql = "insert into pub_tree (id, tree_name, parent_id, deep, is_parent) "
				+ "values (:id, :treeName, :parentId, :deep, :isParent)";
		return util.editObject(sql, tree);
	}
	
	/**
	 * 根据父id获取所有其下的子节点
	 * @param parentId
	 * @return
	 */
	
	public List<Tree> getTreeListByParentId(String parentId){
		
		String sql="select id ,tree_name as treeName,parent_id as parentId," +
				   "deep from pub_tree  where  parent_id='"+parentId+"'";
		return (List<Tree>) util.getObjList(sql, new Object[]{}, Tree.class);
	}
	
	/**
	 * 获取直接下级数量
	 * @param id
	 * @return
	 */
	public int getSubTreeCount(String id){
		String sql = "select count(1) from pub_tree where parent_id = '"+id+"'";
		return util.getObjCount(sql);
	}
	
	
	/**
	 * 改变节点状态
	 * @param id
	 * @param state
	 * @return
	 */
	
	public int updateIsParentState(String id,String state){
		String sql="update  pub_tree  set is_parent ='"+state+"' where id='"+id+"'";
		return util.editObject(sql, null);
	}
	
	
	/**
	 * 根据treeId批量删除机构信息
	 * 
	 * @param ids
	 * @return
	 */
	public int[] delTreeBatch(List<String> ids) {
		String sql = "delete from pub_tree where id = ?";
		return util.batchDelete(sql, ids);
	}
	
	
	/**
	 * 根据treeId获取Tree对象
	 * 
	 * @param orgId
	 * @return
	 */
	public Tree getTreeByTreeId(String treeId) {
		String sql = "select * from pub_tree where id = ?";
		return (Tree) util.getObject(sql, new Object[] {treeId},Tree.class);
	}
	
	
	
	/**
	 * 根据父节点逐级获取下级机构（用于ComboTree）
	 */
	@SuppressWarnings("unchecked")
	public List<Tree> getComTreeList(String pId) {
			String sql = "select id, tree_name as treeName, is_parent as isParent,parent_id  from pub_tree where parent_id =?";
		return (List<Tree>) util.getObjList(sql, new Object[] { pId },Tree.class);
	}
	
	
	/**
	 *获取全部树
	 */
	@SuppressWarnings("unchecked")
	public List<OrgDesc> getTreeList(){
		String sql = "select t.id as id, t.parent_id as pId, t.tree_name as name," +
					" t.is_parent as isParent  from pub_tree t ";
		return (List<OrgDesc>)util.getObjList(sql, new Object[]{}, OrgDesc.class);
	}
	
	public int updateTree(Tree tree){
		String sql="update pub_tree set tree_name=:treeName, parent_id=:parentId where id=:id";
		return util.editObject(sql, tree);
	}

	/**
	 * 根据名称查询树
	 * @param name
	 * @return
	 */
	public Tree getTreeByName(String name){
		String sql = "select * from pub_tree where tree_name = ?";
		
		List<Tree> list = (List<Tree>) util.getObjList(sql, new Object[] { name },Tree.class);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
