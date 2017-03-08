package com.simple.bsp.role.web.controller;

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

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.org.service.OrgService;
import com.simple.bsp.role.po.Role;
import com.simple.bsp.role.po.RoleAuthority;
import com.simple.bsp.role.service.RoleService;

/**
 * @author simple
 *
 */
@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrgService orgService;
	
	/**
	 * 通过菜单进入角色管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/role",method=RequestMethod.GET)
    public String list(Model model){
        return "bsp/role/role";
    }
	
	/**
	 * 默认分页查询角色信息
	 * @param dgm
	 * @param org
	 * @return
	 */
	@RequestMapping(value="/role/queryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Role role){
		return roleService.getPageList(dgm, role);
	}
	
	/**
	 * 进入角色添加页面
	 * @return
	 */
	@RequestMapping(value="/role/addPopWin",method=RequestMethod.GET)
	public String popWin4Add(){
		return "bsp/role/addPopWin";
	}
	
	/**
	 * 保存添加用户
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/role/addRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveRole(Role role){
		
		//添加主键
		role.setRoleId(NextID.getNextID("role"));
		
		OrgDesc orgDesc = orgService.getOrgDescByOrgId(role.getRoleLevel());
		int roleLevel = 0;
		if(orgDesc != null){
			roleLevel = (orgDesc.getId().length())/4;
		}
		//添加角色级别（根据机构级别计算）
		role.setRoleLevel(roleLevel+"");
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(roleService.saveRole(role) > 0){
				map.put("mes", "添加角色成功");
			}else{
				map.put("mes", "添加角色失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加角色失败");
		}
		return map; 
	}
	
	/**
	 * 进入角色修改页面
	 * @return
	 */
	@RequestMapping(value="/role/updatePopWin",method=RequestMethod.GET)
	public String popWin4Update(){
		return "bsp/role/updatePopWin";
	}
	
	/**
	 * 保存修改后的角色信息
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/role/updateRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateRole(Role role){
		
		//如果前台页面修改了角色级别（系统默认最高8级，这里实际为机构编码），则重新计算角色级别。
		if(role.getRoleLevel().trim().length() > 1){
			OrgDesc orgDesc = orgService.getOrgDescByOrgId(role.getRoleLevel());
			int roleLevel = 0;
			if(orgDesc != null){
				roleLevel = (orgDesc.getId().length())/4;
			}
			//设置角色级别（根据机构级别计算）
			role.setRoleLevel(roleLevel+"");
		}
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			if(roleService.updateRole(role) > 0){
				map.put("mes", "更新角色成功");
			}else{
				map.put("mes", "更新角色失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新角色失败");
		}
		return map; 
	}
	
	/**
	 * 批量删除角色
	 * @param roleIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/role/delRoles",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delRoles(@RequestParam("roleId") List<String> roleIdList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = roleService.delRoleBatch(roleIdList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == roleIdList.size()){
				map.put("mes", "删除成功["+sum+"]条角色记录");
			}else{
				map.put("mes", "删除角色失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除角色失败");
		}
		return map;//重定向
	}
	
	/**
	 * 进入权限管理页面
	 * @return
	 */
	@RequestMapping(value="/role/updateAuthPopWin",method=RequestMethod.GET)
	public String popWin4Role(@RequestParam("roleId") String roleId, HttpSession session){
		session.setAttribute("checkedRoleId", roleId);
		return "bsp/role/updateAuthPopWin";
	}
	
	/**
	 * 获取选中角色没有的所有权限
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/role/getAllAuth",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAllAuth(HttpSession session){
		
		String roleId = session.getAttribute("checkedRoleId").toString();
		return roleService.getAllAuthList(roleId);
	}
	
	/**
	 * 获取选中角色的所有权限
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/role/getMyAuth",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getMyAuth(HttpSession session){
		
		String roleId = session.getAttribute("checkedRoleId").toString();
		return roleService.getRoleAuthList(roleId);
	}
	
	/**
	 * 保存角色权限对应关系
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/role/updateRoleAuth",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateRoleAuth(@RequestParam("roleIds") String roleIds, HttpSession session){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			String roleId = session.getAttribute("checkedRoleId").toString();
			//组装RoleAuthority对象
			RoleAuthority roleAuth = new RoleAuthority();
			roleAuth.setRoleId(roleId);
			
			List<Object[]> objList = new ArrayList<Object[]>();
			
			//组装插入数据
			if(roleIds.length() > 0){
				String[] ids = roleIds.split(",");
				for(int i = 0; i < ids.length; i ++){
					Object[] obj = new Object[3];
					obj[0] = NextID.getUUID();
					obj[1] = roleId;
					obj[2] = ids[i];
					
					objList.add(obj);
				}
			}
			
			if(roleService.saveRoleAuths(roleAuth, objList) >= 0){
				map.put("mes", "更新角色权限成功");
			}else{
				map.put("mes", "更新角色权限失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新角色权限失败");
		}
		return map; 
	}

}
