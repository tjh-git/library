package com.simple.bsp.tree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.org.po.ComboTreeModel;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.tree.po.Tree;
import com.simple.bsp.tree.service.TreeService;

@Controller
public class TreeController {
	@Autowired
	TreeService treeService;
	
	
	/**
	 * 通过菜单进入管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tree",method=RequestMethod.GET)
    public String list(Model model){
        return "bsp/tree/tree";
    }
	
	/**
	 * 默认分页查询机构信息
	 * @param dgm
	 * @param org
	 * @return
	 */
	@RequestMapping(value="/tree/queryTreeList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryTreeList(DataGridModel dgm, Tree tree){
		return treeService.getPageList(dgm, tree);
	}
	
	
	
	/**
	 * 进入机构添加页面
	 * @return
	 */
	@RequestMapping(value="/tree/addTreePopWin",method=RequestMethod.GET)
	public String popWin4Add(){
		return "bsp/tree/addTreePopWin";
	}
	
	/**
	 * 保存添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/tree/addTree",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addTree(Tree tree){
		//生成机构主键(机构表和机构描述表共用)
		String treeId = NextID.getNextID("tree");
		tree.setId(treeId);
		tree.setIsParent("0");
		tree.setDeep("0");//暂未启用
		Map<String, String> map = new HashMap<String, String>();
		if(treeService.saveTree(tree) > 0){
			//更新上级机构的IsParent状态，2014-05-08添加
			treeService.updateIsParentState(tree.getParentId(), "1");
			map.put("mes", "添加成功");
		}else{
			map.put("mes", "添加失败");
		}
		return map; 
	}
	
	
	
	/**
	 * 根据当前登录用户过滤机构树
	 * @return
	 */
	@RequestMapping(value="/tree/comTree")
	@ResponseBody
	public void comTree( HttpServletResponse response,@RequestParam String pid){

		List<Tree> comTree=treeService.getComTreeList(pid);

		response.setContentType("text/html;charset=UTF-8");  
		PrintWriter pw = null;  
	    try {  
	    	pw = response.getWriter();  
	    } catch (IOException e) {  
	    	e.printStackTrace();  
	    }
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		
		for(int i=0;i<comTree.size();i++){
			 
			ComboTreeModel ctm = new ComboTreeModel();
			ctm.setId(comTree.get(i).getId());  
	        ctm.setText(comTree.get(i).getTreeName());
	        String isParent = comTree.get(i).getIsParent();
	        if(isParent.equals("1")){
		        List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
		        ctm.setChildren(children);  
		        ctm.setState("closed");
		        list.add(ctm);
	        }else{
		        List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
		        ctm.setChildren(children);  
		        ctm.setState("open");
		        list.add(ctm);
	        }
		}
		String json = JSONArray.fromObject(list).toString();//转化为JSON  
		pw.write(json);//返回前台  
    }
	
	/**
	 * 进入机构修改页面
	 * @return
	 */
	@RequestMapping(value="/tree/updatePopWin",method=RequestMethod.GET)
	public String updatePopWin(){
		return "bsp/tree/updatePopWin";
	}
	
	/**
	 * 保存修改后的信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/tree/updateTree",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateTree( Tree tree,@RequestParam("oldParentId") String oldParentId){
		Map<String, String> map = new HashMap<String, String>();
		if(treeService.updateTree(tree)>0){
			if(treeService.getSubTreeCount(oldParentId)==0){
				treeService.updateIsParentState(oldParentId, "0");
			}
			treeService.updateIsParentState(tree.getParentId(), "1");
			map.put("mes", "修改成功");
		}else{
			map.put("mes", "没做任何修改");
		}
		return map; 
	}
	
	
	
	
	
	
	
	/**
	 * 批量删除机构
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/tree/delTrees",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delTrees(@RequestParam("id") List<String> treeIdList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			List<String> delList = new ArrayList<String>();	//删除机构号列表
			String pidStr = "";
			
			String pid = "";
			for(int i = 0; i < treeIdList.size(); i ++){
				String id = treeIdList.get(i);
				Tree tree = treeService.getTreeByTreeId(id);
				pid=tree.getParentId();
				if(treeService.getSubTreeCount(id) > 0){
					map.put("mes", "["+tree.getTreeName()+"] 不能删除，请先删除该记录下的子记录");
					return map;
				}else{
					delList.add(id);
					if(!pidStr.contains(pid)){
						pidStr += pid+",";
					}
				}
			}
			if(delList.size() > 0){
				int[] result = treeService.delTreeBatch(treeIdList);
				int sum = 0;
				for(int j = 0; j < result.length; j ++){
					sum += result[j];
				}
				//更新上级机构的IsParent状态
				if(!pidStr.equals("")){
					String[] pids = pidStr.split(",");
					for(int i = 0; i < pids.length; i ++){
						
						if(treeService.getSubTreeCount(pids[i])==0){
							treeService.updateIsParentState(pids[i], "0");
						}
					}
				}
				if(sum == treeIdList.size()){
					map.put("mes", "删除成功["+sum+"]条记录");
				}else{
					map.put("mes", "删除失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
		}
		return map;//重定向
	}
	
	
	/**
	 * 进入机构树查看页面
	 * @return
	 */
	@RequestMapping(value="/tree/treePopWin",method=RequestMethod.GET)
	public String popWin4Tree(){
		return "bsp/tree/treePopWin";
	}
	
	
	
	/**
	 * 根据当前登录用户过滤机构树
	 * @return
	 */
	@RequestMapping(value="/tree/getTree")
	@ResponseBody
    public List<OrgDesc> getTree(){
        return treeService.getTreeList();
    }
}
