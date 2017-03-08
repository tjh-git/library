/**
 * 
 */
package com.simple.bsp.menu.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.menu.po.Menu;
import com.simple.bsp.menu.po.ResourceMenu;
import com.simple.bsp.menu.service.MenuService;
import com.simple.bsp.org.util.GenOrgDescID;
import com.simple.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 通过菜单进入菜单管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/menu",method=RequestMethod.GET)
    public String list(Model model){
        return "bsp/menu/menu";
    }
	
	/**
	 * 获取系统菜单树
	 * @return
	 */
	@RequestMapping(value="/menu/getMenu")
	@ResponseBody
    public List<Map<String, Object>> getMenu(){
        return menuService.getMenuTreeList();
    }
	
	/**
	 * 获取（菜单+注册资源）树
	 * @return
	 */
	@RequestMapping(value="/menu/getMenuAndRes")
	@ResponseBody
    public List<Map<String, Object>> getMenuAndRres(){
        return menuService.getMenuAndResTreeList();
    }
	
	/**
	 * 获取当前登录用户的菜单树（根据用户权限）
	 * @return
	 */
	@RequestMapping(value="/menu/getUserMenu")
	@ResponseBody
    public List<Map<String, Object>> getUserMenuTree(){
		
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		
		if (principal instanceof PubUsers) {
			
			Collection<GrantedAuthority> auths = ((PubUsers)principal).getAuthorities();
			
			String authString = "";
			for(GrantedAuthority auth: auths){
				authString += "'"+auth.getAuthority()+"', ";
			}
			authString = authString.substring(0, authString.lastIndexOf(","));
			//System.out.println("组装查询条件为:["+authString+"]");
			
			menuList = menuService.getUserMenuTreeList(authString);
			/*
			for(int i = 0; i < menuList.size(); i ++){
				Map<String, Object> map = menuList.get(i);
				
				System.out.println("--------------"+i+"-------------");
				System.out.println("id-["+map.get("id")+"]");
				System.out.println("pId-["+map.get("pId")+"]");
				System.out.println("name-["+map.get("name")+"]");
				System.out.println("title-["+map.get("title")+"]");
				System.out.println("url-["+map.get("url")+"]");
			}
			*/
		}
		
		
		return menuList;
    }
	
	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/menu/addMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveMenu(Menu menu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			//根据父节点和最大子节点计算菜单编号
			menu.setMenuId(GenOrgDescID.get2NewId(menu.getMenuPid(), menuService.getSubMenuMaxId(menu.getMenuPid())));
			String menuType = "0";	//'0'为菜单
			if(menu.getMenuUrl().equals("")){
				menuType = "1";	//'1'为菜单目录
			}
			menu.setMenuType(menuType);
			
			if(menuService.saveMenu(menu) > 0){
				map.put("mes", "添加菜单成功");
			}else{
				map.put("mes", "添加菜单失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加菜单失败");
		}
		return map;//重定向
	}
	
	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/menu/updateMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateMenu(Menu menu){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			menu.setMenuId(menu.getMenuPid());
			String menuType = "0";	//'0'为菜单
			if(menu.getMenuUrl().equals("")){
				menuType = "1";	//'1'为菜单目录
			}
			menu.setMenuType(menuType);
			
			if(menuService.updateMenu(menu) > 0){
				map.put("mes", "修改菜单成功");
			}else{
				map.put("mes", "修改菜单失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "修改菜单失败");
		}
		return map;//重定向
	}
	
	/**
	 * 删除菜单及关联数据
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/menu/delMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delMenus(@RequestParam("menuId") String menuId){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			ResourceMenu resMenu = new ResourceMenu();
			Menu menu = new Menu();
			resMenu.setMenuId(menuId);
			menu.setMenuId(menuId);
			
			int result = 0;
			if(menuService.getMenuCountByPid(menuId) > 0){
				map.put("mes", "无法删除，请先删除其下级菜单");
				return map;
			}else{
				result = menuService.delMenus(menu, resMenu);
				if(result > 0){
					map.put("mes", "菜单删除成功");
				}else{
					map.put("mes", "菜单删除失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "菜单删除失败");
		}
		return map;//重定向
	}

}
