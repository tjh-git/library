/**
 * 
 */
package com.simple.bsp.org.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.org.po.ComboTreeModel;
import com.simple.bsp.org.po.Org;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.org.service.OrgService;
import com.simple.bsp.org.util.GenOrgDescID;
import com.simple.bsp.security.po.PubUsers;
import com.simple.bsp.user.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author simple
 *
 */
@Controller
public class OrgController {

	@Autowired
	private OrgService orgService;

	@Autowired
	private UserService userService;

	/**
	 * 通过菜单进入用户管理页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/org", method = RequestMethod.GET)
	public String list(Model model) {
		return "bsp/org/org";
	}

	/**
	 * 根据当前登录用户过滤机构树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/getUserOrg")
	@ResponseBody
	public List<OrgDesc> getUserOrg() {
		// 获取当前登录用户信息
		String userOrg = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			userOrg = ((PubUsers) principal).getUserOrg();
		}
		return orgService.getUserOrgList(userOrg);
	}

	/**
	 * 根据当前登录用户过滤机构树
	 * 
	 * @return
	 */
	@RequestMapping(value = {"*/org/comTree","/org/comTree"})
	@ResponseBody
	public void comTree(HttpServletResponse response, @RequestParam String pid) {

		List<OrgDesc> comOrg = orgService.getComOrgList(pid);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();

		for (int i = 0; i < comOrg.size(); i++) {

			ComboTreeModel ctm = new ComboTreeModel();
			ctm.setId(comOrg.get(i).getId());
			ctm.setText(comOrg.get(i).getName());

			String isParent = comOrg.get(i).getIsParent();
			if (isParent.equals("1")) {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("closed");
				list.add(ctm);
			} else {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("open");
				list.add(ctm);
			}
		}
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		pw.write(json);// 返回前台
	}

	@RequestMapping(value = "/org/comboTree")
	@ResponseBody
	public void comboTree(HttpServletResponse response, @RequestParam String pid) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List list = getRecursiveComboTree(pid);
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		pw.write(json);// 返回前台
		pw.flush();
		pw.close();
		return ;
	}

	public List getRecursiveComboTree(String pid) {
		List<OrgDesc> comOrg = orgService.getComOrgList(pid);
		if (comOrg != null && comOrg.size() > 0) {
			JSONArray jsona = new JSONArray();
			List mapList = new ArrayList();
			JSONObject json = new JSONObject();
			for (int i = 0; i < comOrg.size(); i++) {
				Map map = new HashMap();
				map.put("id", comOrg.get(i).getId());
				map.put("text", comOrg.get(i).getName());
				mapList.add(map);
				if (comOrg.get(i).getIsParent().equals("1")) {
					map.put("children", this.getRecursiveComboTree(comOrg.get(i).getId()));
				}
			}
			return mapList;
		} else {
			return null;
		}

	}

	/**
	 * 默认分页查询机构信息
	 * 
	 * @param dgm
	 * @param org
	 * @return
	 */
	@RequestMapping(value = "/org/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Org org) {
		return orgService.getPageList(dgm, org);
	}

	/**
	 * 进入机构添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/addPopWin", method = RequestMethod.GET)
	public String popWin4Add() {
		return "bsp/org/addPopWin";
	}

	/**
	 * 保存添加用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/org/addOrg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveOrg(Org org) {

		// 生成机构主键(机构表和机构描述表共用)
		String orgId = NextID.getNextID("org");

		// 组装机构描述对象
		OrgDesc orgDesc = new OrgDesc();
		String pId = org.getOrgId(); // 父节点，通过前台的树传过来时就是机构描述id
		String id = GenOrgDescID.get4NewId(pId, orgService.getSubOrgMaxId(pId)); // 机构描述码，根据父节点和最大子节点计算
		String orgLevel = id.length() / 4 + ""; // 机构级别，根据机构描述码长度计算
		String isParent = "0"; // 是否父节点，默认【否】，2014-05-08启用该字段，用于comboTree获取机构树的一个参数
		String open = "0"; // 节点是否打开，默认都是关闭
		orgDesc.setId(id);
		orgDesc.setOrgId(orgId);
		orgDesc.setOrgLevel(orgLevel);
		orgDesc.setpId(pId);
		orgDesc.setIsParent(isParent);
		orgDesc.setOpen(open);

		// 组装完善机构对象
		org.setOrgId(orgId); // 系统生成

		Map<String, String> map = new HashMap<String, String>();
		if (orgService.saveOrgDesc(org, orgDesc) > 0) {

			// 更新上级机构的IsParent状态，2014-05-08添加
			orgService.updateIsParent(pId);

			map.put("mes", "添加机构成功");
		} else {
			map.put("mes", "添加机构失败");
		}

		return map;
	}

	/**
	 * 进入机构修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/updatePopWin", method = RequestMethod.GET)
	public String popWin4Update(@RequestParam("pId") String pId, HttpServletRequest request) {
		
		String upOrgName = null;
		OrgDesc desc = orgService.getOrgDescById(pId);
		if(desc!=null){
			Org org = orgService.getOrgByOrgId(desc.getOrgId());
			if(org!=null){
				upOrgName = org.getOrgName();
			}
		}
		
		request.setAttribute("upOrgName", upOrgName);
		
		return "bsp/org/updatePopWin";
	}

	/**
	 * 保存修改后的机构信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/org/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateOrg(@RequestParam("updesc") int upDesc, @RequestParam("orgid") String orgId,
			@RequestParam("id") String id, @RequestParam("oldPid") String oldPid, Org org) {

		Map<String, String> map = new HashMap<String, String>();

		// 没有修改上级机构(只更新Org对象)
		if (upDesc == 0) {
			org.setOrgId(orgId);
			if (orgService.updateOrg(org) > 0) {
				map.put("mes", "修改机构成功");
			} else {
				map.put("mes", "修改机构失败");
			}
		} else { // 修改了上级机构
			String[] pid = org.getOrgId().split(",");
			String newPID = "";
			if (pid.length > 1) {
				newPID = pid[1];
			}
			String[] ids = id.split(",");
			if (newPID.contains(ids[0])) {
				map.put("mes", "不能将机构变更到自身或下级机构中");
				return map;
			}
			// 组装机构描述对象
			OrgDesc orgDesc = new OrgDesc();
			// 计算新的机构描述ID,根据父节点和最大子节点计算
			String newID = GenOrgDescID.get4NewId(newPID, orgService.getSubOrgMaxId(newPID));
			orgDesc.setId(newID);
			orgDesc.setOpen("0");
			orgDesc.setOrgLevel((newID.length() / 4) + "");
			orgDesc.setOrgId(orgId); // 机构描述对象的orgId替换为参数传递过来的实际orgId
			orgDesc.setpId(newPID);

			org.setOrgId(orgId); // 机构对象的orgId替换为参数传递过来的实际orgId

			int[] rst = orgService.updateOrgDescs(org, orgDesc);
			int sum = 1;
			for (int j = 0; j < rst.length; j++) {
				sum += rst[j];
			}
			if (sum > 0) {

				// 更新上级机构的IsParent状态，2014-05-08添加
				orgService.updateIsParent(newPID);
				orgService.updateIsParent(oldPid);

				map.put("mes", "修改关联机构[" + sum + "]个");
			} else {
				map.put("mes", "修改机构失败");
			}
		}
		return map;
	}

	/**
	 * 批量删除机构
	 * 
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/org/delOrgs", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delOrgs(@RequestParam("orgId") List<String> orgIdList) {

		Map<String, String> map = new HashMap<String, String>();
		try {

			List<String> delList = new ArrayList<String>(); // 删除机构号列表
			String pidStr = "";

			String pid = "";
			for (int i = 0; i < orgIdList.size(); i++) {
				String orgId = orgIdList.get(i);
				Org org = orgService.getOrgByOrgId(orgId);
				OrgDesc orgDesc = orgService.getOrgDescByOrgId(orgId);
				pid = orgDesc.getpId();

				if (orgService.getSubOrgCount(orgDesc.getId()) > 0) {
					map.put("mes", "[" + org.getOrgName() + "] 不能删除，请先删除该机构的下属机构");
					return map;
				} else if (userService.getUserCountByOrgId(orgId) > 0) {
					map.put("mes", "[" + org.getOrgName() + "] 不能删除，请先删除该机构下的用户");
					return map;
				} else {
					delList.add(orgId);

					if (!pidStr.contains(pid)) {
						pidStr += pid + ",";
					}
				}
			}

			if (delList.size() > 0) {
				int[] result = orgService.delOrgs(orgIdList);
				int sum = 0;
				for (int j = 0; j < result.length; j++) {
					sum += result[j];
				}

				// 更新上级机构的IsParent状态，2014-05-08添加
				if (!pidStr.equals("")) {
					String[] pids = pidStr.split(",");
					for (int i = 0; i < pids.length; i++) {
						orgService.updateIsParent(pids[i]);
					}
				}

				if (sum == orgIdList.size()) {
					map.put("mes", "删除成功[" + sum + "]条机构记录");
				} else {
					map.put("mes", "删除机构失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除机构失败");
		}
		return map;// 重定向
	}

	/**
	 * 进入机构树查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/treePopWin", method = RequestMethod.GET)
	public String popWin4Tree() {
		return "bsp/org/treePopWin";
	}
	
	/**
	 * 所有机构 同步加载
	 * @author guo
	 * @return
	 */
	@RequestMapping(value = {"*/org/comTreeAll","/org/comTreeAll"})
	@ResponseBody
	public void comTreeAll(HttpServletResponse response, @RequestParam String pid) {

		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<ComboTreeModel> list = getTreeList(pid);
		
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		pw.write(json);// 返回前台
	}
	
	private List<ComboTreeModel> getTreeList(String pid){
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		
		List<OrgDesc> comOrg = orgService.getComOrgListWithoutUserCheck(pid);
		for (int i = 0; i < comOrg.size(); i++) {

			ComboTreeModel ctm = new ComboTreeModel();
			ctm.setId(comOrg.get(i).getId());
			ctm.setText(comOrg.get(i).getName());

			String isParent = comOrg.get(i).getIsParent();
			if (isParent.equals("1")) {
				List<ComboTreeModel> children = getTreeList(comOrg.get(i).getId());
				ctm.setChildren(children);
				ctm.setState("closed");
				list.add(ctm);
			} else {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("open");
				list.add(ctm);
			}
		}
		
		return list;
	}

}
