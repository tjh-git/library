/**
 * 
 */
package com.simple.bsp.user.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.simple.bsp.common.web.controller.BaseController;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.org.service.OrgService;
import com.simple.bsp.user.po.User;
import com.simple.bsp.user.po.UserRole;
import com.simple.bsp.user.service.UserService;

/**
 * @author simple
 *
 */
@Controller
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;
	
	/**
	 * 通过菜单进入用户管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/user",method=RequestMethod.GET)
    public String list(Model model){
        return "bsp/user/user";
    }
	
	/**
	 * 默认分页查询用户信息
	 * @param dgm
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/queryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, User user){
		return userService.getPageList(dgm, user);
	}
	
	/**
	 * 进入用户添加页面
	 * @return
	 */
	@RequestMapping(value="/user/addPopWin",method=RequestMethod.GET)
	public String popWin4Add(){
		return "bsp/user/addPopWin";
	}
	
	/**
	 * 判断用户账号是否存在
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/chkExist",method=RequestMethod.POST)
	@ResponseBody
	public boolean chkExist(@RequestParam("userAccount") String userAccount){
		if(userService.getUserCountByAcc(userAccount) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 进入当前登录用户密码修改页面
	 * @return
	 */
	@RequestMapping(value="/user/updateLoginPwd",method=RequestMethod.GET)
	public String updateLoginPwd(){
		return "bsp/user/updatePassword";
	}
	
	/**
	 * 验证原始密码是否正确
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/user/chkOldPwd",method=RequestMethod.POST)
	@ResponseBody
	public int checkLoginUserPwd(@RequestParam("oldPassword") String password){
		return userService.checkLoginUserPwd(password);
	}
	
	/**
	 * 更新当前登录用户的密码
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/user/updateNewPwd",method=RequestMethod.POST)
	@ResponseBody
	public int updateNewPwd(@RequestParam("newPassword") String password){
		return userService.updatePwd(password);
	}
	
	/**
	 * 根据用户账号获取用户对象（修改登录用户个人信息）
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/user/loginMsg4Update",method=RequestMethod.GET)
	public String loginMsg4Update(HttpServletRequest req, HttpSession session){
		String userAccount  = session.getAttribute("userAccount").toString();
		User user = userService.getLoginUserObj(userAccount);
		
		req.setAttribute("row", user);
		req.setAttribute("userAccount", userAccount);
		req.setAttribute("userGender", user.getUserGender());
		return "bsp/user/updateUserMsg";
	}
	
	/**
	 * 修改登录用户个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/updateUserMsg",method=RequestMethod.POST)
	@ResponseBody
	public int updateUserMsg(HttpSession session, User user){
		String userAccount  = session.getAttribute("userAccount").toString();
		user.setUserAccount(userAccount);
		return userService.updateUserMsg(user);
	}
	
	
	/**
	 * 保存添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/addUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUser(User user){
		//添加主键
		user.setUserId(NextID.getNextID("user"));
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(userService.saveUser(user) > 0){
				map.put("mes", "添加用户成功");
			}else{
				map.put("mes", "添加用户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加用户失败");
		}
		return map; 
	}
	
	/**
	 * 进入用户修改页面
	 * @return
	 */
	@RequestMapping(value="/user/updatePopWin",method=RequestMethod.GET)
	public String popWin4Update(){
		return "bsp/user/updatePopWin";
	}
	
	/**
	 * 保存修改后的用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateUser(@RequestParam("uppwd") boolean upPwd, User user){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			//如果没有修改密码，将密码置为null，后台将不做更新
			if(!upPwd){
				user.setUserPassword(null);
			}
			if(userService.updateUser(user) > 0){
				map.put("mes", "更新用户成功");
			}else{
				map.put("mes", "更新用户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新用户失败");
		}
		return map; 
	}
	
	/**
	 * 批量删除用户
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/delUsers",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delUsers(@RequestParam("userId") List<String> userIdList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = userService.delUserBatch(userIdList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == userIdList.size()){
				map.put("mes", "删除成功["+sum+"]条用户记录");
			}else{
				map.put("mes", "删除用户失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除用户失败");
		}
		return map;//重定向
	}
	
	/**
	 * 进入角色管理页面
	 * @return
	 */
	@RequestMapping(value="/user/updateRolePopWin",method=RequestMethod.GET)
	public String popWin4Role(@RequestParam("userId") String userId, HttpSession session){
		session.setAttribute("checkedUserId", userId);
		return "bsp/user/updateRolePopWin";
	}
	
	/**
	 * 获取选中用户没有的所有角色
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/user/getAllRole",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAllRole(HttpSession session){
		
		OrgDesc orgDesc = orgService.getOrgDescByOrgId(session.getAttribute("userOrg").toString());;
		int roleLevel = 8;	//默认为最低的角色级别
		if(orgDesc != null){
			//根据登录用户的所在机构计算角色级别
			roleLevel = (orgDesc.getId().length())/4;
		}
		
		String userId = session.getAttribute("checkedUserId").toString();
		return userService.getAllRoleList(userId, roleLevel);
	}
	
	/**
	 * 获取选中用户的所有角色
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/user/getMyRole",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getMyRole(HttpSession session){
		
		String userId = session.getAttribute("checkedUserId").toString();
		return userService.getUserRoleList(userId);
	}
	
	/**
	 * 保存用户角色对应关系
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user/updateUserRole",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateUserRole(@RequestParam("roleIds") String roleIds, HttpSession session){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			String userId = session.getAttribute("checkedUserId").toString();
			//组装UserRole对象
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			
			List<Object[]> objList = new ArrayList<Object[]>();
			
			//组装插入数据
			if(roleIds.length() > 0){
				String[] ids = roleIds.split(",");
				for(int i = 0; i < ids.length; i ++){
					Object[] obj = new Object[3];
					obj[0] = NextID.getUUID();
					obj[1] = userId;
					obj[2] = ids[i];
					
					objList.add(obj);
				}
			}
			
			if(userService.saveUserRoles(userRole, objList) >= 0){
				map.put("mes", "更新用户角色成功");
			}else{
				map.put("mes", "更新用户角色失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新用户角色失败");
		}
		return map; 
	}

}
