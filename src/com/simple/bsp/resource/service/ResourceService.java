package com.simple.bsp.resource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.resource.dao.ResourceDao;
import com.simple.bsp.resource.po.Resource;

/**
 * @author simple
 *
 */
@Service("resourceService")
public class ResourceService{
	
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 分页查询角色
	 * @param dgm
	 * @param resource
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Resource resource){
		return resourceDao.getPageList(dgm, resource);
	}
	
	/**
	 * 保存资源
	 * @param resource
	 * @return
	 */
	public int saveResource(Resource resource){
		return resourceDao.saveResource(resource);
	}
	
	/**
	 * 批量保存[资源-菜单]对应关系
	 * @param objList
	 * @return
	 */
	public int[] saveResMenu(List<Object[]> objList){
		
		List<String> idList = new ArrayList<String>();
		List<Object[]> idObjList = new ArrayList<Object[]>();
		
		for(int i = 0; i < objList.size(); i ++){
			idList.add(((Object[])objList.get(i))[1].toString());
			
			Object[] obj = new Object[1];
			obj[0] = ((Object[])objList.get(i))[1].toString();
			idObjList.add(obj);
		}
		//删除原有[资源-菜单]对应关系
		resourceDao.delResMenu(idList);
		//批量更新资源注册状态
		resourceDao.updateIsSysBatch(idObjList, "1");
		//保存新的[资源-菜单]对应关系
		return resourceDao.saveResMenu(objList);
	}
	
	/**
	 * 更新资源
	 * @param resource
	 * @return
	 */
	public int updateResource(Resource resource){
		return resourceDao.updateResource(resource);
	}
	
	/**
	 * 批量删除资源及关联数据
	 * @param idList
	 * @return
	 */
	public int[] delResourceBatch(List<String> idList){
		//删除[权限-资源]对应关系
		resourceDao.delAuthRes(idList);
		//删除[资源-菜单对应关系]
		resourceDao.delResMenu(idList);
		return resourceDao.delResourceBatch(idList);
	}

}
