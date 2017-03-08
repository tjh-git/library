/**
 * 
 */
package com.simple.bsp.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.menu.dao.MenuDao;
import com.simple.bsp.menu.po.Menu;
import com.simple.bsp.menu.po.ResourceMenu;
import com.simple.bsp.resource.dao.ResourceDao;

/**
 * @author simple
 *
 */
@Service("menuService")
public class MenuService{
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 获取菜单树
	 */
	public List<Map<String, Object>> getMenuTreeList(){
		return menuDao.getMenuTreeList();
	}
	
	/**
	 * 获取（菜单+注册资源）树
	 */
	public List<Map<String, Object>> getMenuAndResTreeList(){
		List<Map<String, Object>> menuTree = menuDao.getMenuTreeList();
		List<Map<String, Object>> resTree = menuDao.getResTreeList();
		menuTree.addAll(resTree);
		return menuTree;
	}
	
	/**
	 * 根据登录用户权限集合获取菜单集合
	 * @param authString
	 * @return
	 */
	public List<Map<String, Object>> getUserMenuTreeList(String authString){
		
		//定义返回的List
		List<Map<String, Object>> finalMenuList = new ArrayList<Map<String, Object>>();
		
		//根据用户权限获取菜单集合
		List<Map<String, Object>> menuList = menuDao.getUserMenuList(authString);
		
		//对获取的菜单进行过滤（已排序），保留属于不同菜单目录的菜单的pid
		List<String> diffPidList = new ArrayList<String>();
		String tmpId = ((Map<String, Object>)menuList.get(0)).get("pId").toString();
		diffPidList.add(tmpId);
		for(int i = 1; i < menuList.size(); i ++){
			Map<String, Object> menuMap = menuList.get(i);
			if(!menuMap.get("pId").toString().equals(tmpId)){
				diffPidList.add(menuMap.get("pId").toString());
				tmpId = menuMap.get("pId").toString();
			}
		}
		
		//根据过滤后的pid一级一级向上查找菜单目录，截止到根目录
		List<Map<String, Object>> midMenuList = new ArrayList<Map<String, Object>>();
		String newIdStr = "";
		for(int j = 0; j < diffPidList.size(); j ++){
			String pId = diffPidList.get(j);
			//System.out.println("++++>["+pId+"]");
			int count = pId.length()/2;
			for(int k = count; k > 1; k --){
				//循环查询上级父节点
				String menuId = "00"+pId.substring(2, k*2);
				//System.out.println("---->["+menuId+"]");
				//System.out.println("====>["+newIdStr+"]");
				if(!newIdStr.contains("["+menuId+"]")){
					midMenuList.addAll(menuDao.getParentMenuList("00"+pId.substring(2, k*2)));
					newIdStr += "["+menuId+"]";
				}
			}
		}
		
		//单独查询根目录，然后依次添加目录和菜单
		finalMenuList.addAll(menuDao.getParentMenuList("00"));
		finalMenuList.addAll(midMenuList);
		finalMenuList.addAll(menuList);
		
		return finalMenuList;
	}
	
	/**
	 * 保存菜单树
	 * @param menu
	 * @return
	 */
	public int saveMenu(Menu menu){
		return menuDao.saveMenu(menu);
	}
	
	/**
	 * 更新菜单树
	 * @param menu
	 * @return
	 */
	public int updateMenu(Menu menu){
		return menuDao.updateMenu(menu);
	}
	
	/**
	 * 删除菜单及关联数据
	 * @param menu
	 * @param resMenu
	 * @return
	 */
	public int delMenus(Menu menu, ResourceMenu resMenu){
		//更新已经注册到菜单的资源状态为'未注册'
		resourceDao.updateResIsSys(menu.getMenuId());
		//删除[资源-菜单]对应关系
		menuDao.delResMenu(resMenu);
		return menuDao.delMenu(menu);
	}
	
	/**
	 * 获取直接下级菜单节点中id最大的，返回其最后2位字符串的整形值
	 * @param id
	 * @return
	 */
	public int getSubMenuMaxId(String id){
		String sql= "select max(menu_id) as \"maxId\" from pub_menu where menu_pid = ?";
		Map<String, Object> idMap = (Map<String, Object>)menuDao.getMap(sql, new Object[]{id});
		int orgCount = 0;
		String maxId = (String)idMap.get("maxId");
		if(null != maxId){
			orgCount = Integer.parseInt(maxId.substring(maxId.length()-2));
		}else{
			orgCount = -1;
		}
		
		return orgCount;
	}
	
	/**
	 * 查询子菜单的数量
	 * @param menuId
	 * @return
	 */
	public int getMenuCountByPid(String menuId){
		return menuDao.getMenuCountByPid(menuId);
	}

}
