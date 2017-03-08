/**
 * 
 */
package com.simple.bsp.sysparam.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.common.util.PubData;
import com.simple.bsp.sysparam.po.SysParam;
import com.simple.bsp.sysparam.service.SysParamService;

/**
 * @author simple
 *
 */
@Controller
public class SysParamController {
	
	@Autowired
	private SysParamService sysParamService;
	
	/**
	 * 通过菜单进入系统参数管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sysparam",method=RequestMethod.GET)
    public String list(Model model){
        return "bsp/sysparam/sysparam";
    }
	
	/**
	 * 默认分页查询系统参数信息
	 * @param dgm
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/sysparam/queryList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, SysParam param){
		return sysParamService.getPageList(dgm, param);
	}
	
	/**
	 * 进入系统参数添加页面
	 * @return
	 */
	@RequestMapping(value="/sysparam/addPopWin",method=RequestMethod.GET)
	public String popWin4Add(){
		return "bsp/sysparam/addPopWin";
	}
	
	/**
	 * 保存添加系统参数
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/sysparam/addSysParam",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveSysParam(SysParam param){
		//添加主键
		param.setParamId(NextID.getUUID());
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(sysParamService.saveSysParam(param) > 0){
				map.put("mes", "添加系统参数成功");
			}else{
				map.put("mes", "添加系统参数失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加系统参数失败");
		}
		return map; 
	}
	
	/**
	 * 进入系统参数修改页面
	 * @return
	 */
	@RequestMapping(value="/sysparam/updatePopWin",method=RequestMethod.GET)
	public String popWin4Update(){
		return "bsp/sysparam/updatePopWin";
	}
	
	/**
	 * 保存修改后的系统参数信息
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/sysparam/updateSysParam",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateSysParam(SysParam param){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			if(sysParamService.updateSysParam(param) > 0){
				map.put("mes", "更新系统参数成功");
			}else{
				map.put("mes", "更新系统参数失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新系统参数失败");
		}
		return map; 
	}
	
	/**
	 * 批量删除角色
	 * @param paramIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sysparam/delSysParams",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delSysParams(@RequestParam("paramId") List<String> paramIdList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = sysParamService.delSysParamBatch(paramIdList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == paramIdList.size()){
				map.put("mes", "删除成功["+sum+"]条系统参数记录");
			}else{
				map.put("mes", "删除系统参数失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除系统参数失败");
		}
		return map;//重定向
	}
	
	/**
	 * 刷新系统参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sysparam/reloadSysParam",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> reloadSysParam(){
		Map<String, String> map = new HashMap<String, String>();
		//清空内存中的系统参数
		PubData.pubDataMap.clear();
		int ret = sysParamService.reloadSysParam();
		if(ret > 0){
			map.put("mes", "刷新系统参数完成！");
		}else{
			map.put("mes", "无系统参数！");
		}
		
		return map;//重定向
	}

}
