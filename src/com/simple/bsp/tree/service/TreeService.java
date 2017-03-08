package com.simple.bsp.tree.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.tree.dao.TreeDao;
import com.simple.bsp.tree.po.Tree;

@Service("treeService")
public class TreeService {
	@Autowired
	TreeDao treeDao;
	
	
	/**
	 * 分页查询系统参数
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Tree tree) {
	
			return treeDao.getPageList(dgm, tree);
		}
	
	
	/**
	 * 根据父id获取所有其下的子节点
	 * @param parentId
	 * @return
	 */
	
	public List<Tree> getTreeListByParentId(String parentId){
		
		return treeDao.getTreeListByParentId(parentId);
	}
	
	/**
	 * 改变节点状态
	 * @param id
	 * @param state
	 * @return
	 */
	
	public int updateIsParentState(String id,String state){
		
		return treeDao.updateIsParentState(id, state);
	}
	
	
	/**
	 * 根据treeId批量删除机构信息
	 * 
	 * @param ids
	 * @return
	 */
	public int[] delTreeBatch(List<String> ids) {
		
		return treeDao.delTreeBatch(ids);
	}
	
	/**
	 * 根据treeId获取Tree对象
	 * 
	 * @param treeId
	 * @return
	 */
	public Tree getTreeByTreeId(String treeId) {
		
		return treeDao.getTreeByTreeId(treeId);
	}
	
	
	/**
	 * 根据父节点逐级获取下级机构（用于ComboTree）
	 */
	public List<Tree> getComTreeList(String pId) {
		
		return treeDao.getComTreeList(pId);
	}
	
	/**
	 * 保存机构信息
	 */
	public int saveTree(Tree tree) {
		
		return  treeDao.saveTree(tree);
	}
	
	/**
	 * 获取直接下级数量
	 * @param id
	 * @return
	 */
	public int getSubTreeCount(String id){
		
		return  treeDao.getSubTreeCount(id);
	}

	public List<OrgDesc> getTreeList() {
		// TODO Auto-generated method stub
		return treeDao.getTreeList();
	}
	
	public int updateTree(Tree tree){
		
		return treeDao.updateTree(tree);
		
	}

	/**
	 * 根据名称查询树
	 * @param name
	 * @return
	 */
	public Tree getTreeByName(String name){
		
		return treeDao.getTreeByName(name);
	}
}
