package com.simple.bsp.authority.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.authority.po.Authority;
import com.simple.bsp.authority.po.AuthorityResource;
import com.simple.bsp.authority.service.AuthorityService;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.NextID;

/**
 * @author simple
 *
 */
@Controller
public class AuthorityController {
	
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * 通过菜单进入权限管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/authority",method=RequestMethod.GET)
    public String list(Model model){
        return "bsp/authority/authority";
    }
	
	/**
	 * 默认分页查询权限信息
	 * @param dgm
	 * @param org
	 * @return
	 */
	@RequestMapping(value="/authority/queryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Authority authority){
		return authorityService.getPageList(dgm, authority);
	}
	
	/**
	 * 进入权限添加页面
	 * @return
	 */
	@RequestMapping(value="/authority/addPopWin",method=RequestMethod.GET)
	public String popWin4Add(){
		return "bsp/authority/addPopWin";
	}
	
	/**
	 * 保存添加用户
	 * @param authority
	 * @return
	 */
	@RequestMapping(value="/authority/addAuthority",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAuthority(Authority authority){
		//添加主键
		authority.setAuthorityId(NextID.getAuthID("AUTH_"));
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(authorityService.saveAuthority(authority) > 0){
				map.put("mes", "添加权限成功");
			}else{
				map.put("mes", "添加权限失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加权限失败");
		}
		return map; 
	}
	
	/**
	 * 进入权限修改页面
	 * @return
	 */
	@RequestMapping(value="/authority/updatePopWin",method=RequestMethod.GET)
	public String popWin4Update(){
		return "bsp/authority/updatePopWin";
	}
	
	/**
	 * 保存修改后的权限信息
	 * @param authority
	 * @return
	 */
	@RequestMapping(value="/authority/updateAuthority",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateAuthority(Authority authority){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			if(authorityService.updateAuthority(authority) > 0){
				map.put("mes", "更新权限成功");
			}else{
				map.put("mes", "更新权限失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新权限失败");
		}
		return map; 
	}
	
	/**
	 * 批量删除权限
	 * @param authorityIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/authority/delAuthorities",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delAuthorities(@RequestParam("authorityId") List<String> authorityIdList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = authorityService.delAuthorityBatch(authorityIdList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == authorityIdList.size()){
				map.put("mes", "删除成功["+sum+"]条权限记录");
			}else{
				map.put("mes", "删除权限失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除权限失败");
		}
		return map;//重定向
	}
	
	/**
	 * 进入资源管理页面
	 * @return
	 */
	@RequestMapping(value="/authority/updateResPopWin",method=RequestMethod.GET)
	public String popWin4Res(@RequestParam("authorityId") String authorityId, HttpSession session){
		session.setAttribute("checkedAuthId", authorityId);
		return "bsp/authority/updateResPopWin";
	}
	
	/**
	 * 获取选中权限的所有资源
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/authority/getMyRes",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getMyRes(HttpSession session){
		
		String authorityId = session.getAttribute("checkedAuthId").toString();
		return authorityService.getAuthResList(authorityId);
	}
	
	/**
	 * 保存权限资源对应关系
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/authority/updateAuthRes",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateAuthRes(@RequestParam("authorityIds") String authorityIds, HttpSession session){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			String authorityId = session.getAttribute("checkedAuthId").toString();
			//组装AuthorityResource对象
			AuthorityResource authRes = new AuthorityResource();
			authRes.setAuthorityId(authorityId);
			
			List<Object[]> objList = new ArrayList<Object[]>();
			
			//组装插入数据
			if(authorityIds.length() > 0){
				String[] ids = authorityIds.split(",");
				for(int i = 0; i < ids.length; i ++){
					Object[] obj = new Object[3];
					obj[0] = NextID.getUUID();
					obj[1] = authorityId;
					obj[2] = ids[i];
					
					objList.add(obj);
				}
			}
			
			if(authorityService.saveAuthRes(authRes, objList) >= 0){
				map.put("mes", "更新权限资源成功");
			}else{
				map.put("mes", "更新权限资源失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新权限资源失败");
		}
		return map; 
	}

}
