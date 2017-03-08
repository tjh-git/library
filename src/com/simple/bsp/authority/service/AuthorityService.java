package com.simple.bsp.authority.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.authority.dao.AuthorityDao;
import com.simple.bsp.authority.po.Authority;
import com.simple.bsp.authority.po.AuthorityResource;
import com.simple.bsp.common.util.DataGridModel;

/**
 * @author simple
 *
 */
@Service("authorityService")
public class AuthorityService{
	
	@Autowired
	private AuthorityDao authorityDao;
	
	/**
	 * 分页查询权限
	 * @param dgm
	 * @param org
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Authority authority){
		return authorityDao.getPageList(dgm, authority);
	}
	
	/**
	 * 保存权限
	 * @param authority
	 * @return
	 */
	public int saveAuthority(Authority authority){
		return authorityDao.saveAuthority(authority);
	}
	
	/**
	 * 更新权限
	 * @param authority
	 * @return
	 */
	public int updateAuthority(Authority authority){
		return authorityDao.updateAuthority(authority);
	}
	
	/**
	 * 批量删除权限及关联数据
	 * @param idList
	 * @return
	 */
	public int[] delAuthorityBatch(List<String> idList){
		//删除[权限-资源]对应关系
		authorityDao.delAuthRes(idList);
		//删除[角色-权限]对应关系
		authorityDao.delRoleAuth(idList);
		return authorityDao.delAuthorityBatch(idList);
	}
	
	/**
	 * 获取选中权限的所有资源
	 * @param authorityId
	 * @return
	 */
	public List<Map<String, Object>> getAuthResList(String authorityId){
		String sql = "select ar.resource_id as \"id\", r.resource_name as \"name\" from pub_authorities_resources ar, pub_resources r " +
					 "where ar.resource_id = r.resource_id and ar.authority_id = ?";
		return authorityDao.getAllRes(sql, new Object[]{authorityId});
	}
	
	/**
	 * 删除旧的，添加新的权限资源对应关系
	 */
	public int saveAuthRes(AuthorityResource authRes, List<Object[]> saveList){
		
		int result = authorityDao.delAuthRes(authRes);
		
		if(saveList.size() > 0){
			int[] saveCount = authorityDao.saveAuthRes(saveList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i];
			}
		}
		return result;
	}

}
