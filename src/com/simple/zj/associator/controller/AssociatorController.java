package com.simple.zj.associator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.Dictionary;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.security.po.PubUsers;
import com.simple.zj.associator.form.SonAssociator;
import com.simple.zj.associator.po.Associator;
import com.simple.zj.associator.service.AssociatorService;

@Controller
public class AssociatorController {

	@Autowired
	private AssociatorService associatorService;
	
	@RequestMapping(value = "/associator/associator", method = RequestMethod.GET)
	public String skip(){
		
		return "zj/associator/associatorStudent";
		
	}
	
	@RequestMapping(value = "/associator/teacherassociator", method = RequestMethod.GET)
	public String skipT(){
		
		return "zj/associator/associatorTeacher";
		
	}
	
	@RequestMapping(value = "/associator/associatorStudentList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> studentList(DataGridModel dgm, SonAssociator form){
		
		Map<String, String> tflxMap = Dictionary.getDictionary("KPLX");
		Map<String, String> hyztMap = Dictionary.getDictionary("HYZT");
		form.setCardtype("02");
		Map<String, Object> map =  associatorService.getAssociatorPage(dgm, form);
		
		if(map.get("rows")!=null){
			List<SonAssociator> list = (List) map.get("rows");
			
			for(SonAssociator sonAssociator : list){
				sonAssociator.setCardtypeName((String)tflxMap.get(sonAssociator.getCardtype()));
				sonAssociator.setStateName((String)hyztMap.get(sonAssociator.getState()));
			}
		}
		return map;
		
	}
	
	@RequestMapping(value = "/associator/addStudentAssociator", method = RequestMethod.GET)
	public String addStudentAssociator(Model model, HttpServletRequest req){
		
		List<Map<String,String>> typelist = Dictionary.getListDictionary("KPLX");
		List<Map<String,String>> asslist = Dictionary.getListDictionary("HYZT");
		req.setAttribute("typelist",typelist);
		req.setAttribute("asslist", asslist);
		return "zj/associator/addStudentAssociator";
	}
	
	@RequestMapping(value = "/associator/addStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> addStudent(Associator associator, HttpServletRequest req){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String orgId = "";
		if (principal instanceof PubUsers) {
            PubUsers user = (PubUsers) principal;
			orgId = user.getUserOrg();
		}
		associator.setId(NextID.getUUID());// 主键
		associator.setOrgId(orgId);

		Map<String, String> map = new HashMap<String,String>();
		try {
			if(associatorService.saveAssociator(associator)>0){
				map.put("mes", "添加成功");
			} else {
				map.put("mes", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加失败");
		}
			
		return map;
	}
	
	@RequestMapping(value = "/associator/updateStudentAssociator", method = RequestMethod.GET)
	public String StudentAssociator(Model model, HttpServletRequest req){
		
		List<Map<String,String>> typelist = Dictionary.getListDictionary("KPLX");
		List<Map<String,String>> asslist = Dictionary.getListDictionary("HYZT");
		req.setAttribute("typelist",typelist);
		req.setAttribute("asslist", asslist);
		return "zj/associator/updateStudentAssociator";
	}
	
	@RequestMapping(value = "/associator/updateStudent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateStudent(Associator associator,HttpServletRequest req){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String orgId = "";
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers) principal;
			orgId = user.getUserOrg();
		}
		
		associator.setOrgId(orgId);
		
		Map<String, String> map = new HashMap<String,String>();
		try {
			if(associatorService.updateAssociator(associator)>0){
				map.put("mes", "修改成功");
			} else {
				map.put("mes", "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "修改失败");
		}
			
		return map;
		
	}
	
	@RequestMapping(value = "/associator/delStudent",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delStudent(@RequestParam("id") List<String> idList){
		
		Map<String, String> map = new HashMap<String, String>(); 
		try {
			int[] result = associatorService.delAssociator(idList);
			
			int sum = 0;
			
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			
			map.put("mes", "删除成功["+sum+"]条记录");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
		}
		return map;
	}
	
	//教师 
	
	@RequestMapping(value = "/associator/associatorTeacherList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> associatorTeacherList(DataGridModel dgm, SonAssociator form){
		
        Map<String, String> tflxMap = Dictionary.getDictionary("KPLX");
		Map<String, String> hyztMap = Dictionary.getDictionary("HYZT");
		
		form.setCardtype("01");
		Map<String, Object> map =  associatorService.getAssociatorPage(dgm, form);
		
		if(map.get("rows")!=null){
			List<SonAssociator> list = (List) map.get("rows");

			for(SonAssociator sonAssociator : list){
				sonAssociator.setCardtypeName((String)tflxMap.get(sonAssociator.getCardtype()));
				sonAssociator.setStateName((String)hyztMap.get(sonAssociator.getState()));
			}
		}
		return map;
		
	}
	
	
	@RequestMapping(value = "/associator/addTeacherAssociator", method = RequestMethod.GET)
	public String addTeacherAssociator(Model model, HttpServletRequest req){
		List<Map<String,String>> typelist = Dictionary.getListDictionary("KPLX");
		List<Map<String,String>> asslist = Dictionary.getListDictionary("HYZT");
		req.setAttribute("typelist",typelist);
		req.setAttribute("asslist", asslist);
		return "zj/associator/addTeacherAssociator";
	}
	
	@RequestMapping(value = "/associator/addTeacher", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> addTeacher(Associator associator, HttpServletRequest req){
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String orgId = "";
		if (principal instanceof PubUsers) {
            PubUsers user = (PubUsers) principal;
			orgId = user.getUserOrg();
		}
		associator.setId(NextID.getUUID());// 主键
		associator.setOrgId(orgId);
		
		Map<String, String> map = new HashMap<String,String>();
		try {
			if(associatorService.saveAssociator(associator)>0){
				map.put("mes", "添加成功");
			} else {
				map.put("mes", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加失败");
		}
			
		return map;
	}
	
	@RequestMapping(value = "/associator/updateTeacherAssociator", method = RequestMethod.GET)
	public String updateTeacherAssociator(Model model,HttpServletRequest req){
		
		List<Map<String,String>> typelist = Dictionary.getListDictionary("KPLX");
		List<Map<String,String>> asslist = Dictionary.getListDictionary("HYZT");
		req.setAttribute("typelist",typelist);
		req.setAttribute("asslist", asslist);
		return "zj/associator/updateTeacherAssociator";
	}
	
	@RequestMapping(value = "/associator/updateTeacher", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateTeacher(Associator associator,HttpServletRequest req){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String orgId = "";
		
		if (principal instanceof PubUsers) {
		    PubUsers user = (PubUsers) principal;
		    orgId = user.getUserOrg();
		}
		
		associator.setOrgId(orgId);
		
		Map<String, String> map = new HashMap<String,String>();
		
		try {
			
			if(associatorService.updateAssociator(associator)>0){
				map.put("mes", "修改成功");
			} else {
				map.put("mes", "修改失败");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			map.put("mes", "修改失败");
		}
			
		return map;
		
	}
	
	@RequestMapping(value = "/associator/delTeacher",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delTeacher(@RequestParam("id") List<String> idList){
		
		Map<String, String> map = new HashMap<String, String>(); 
		try {
			int[] result = associatorService.delAssociator(idList);
			
			int sum = 0;
			
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			
			map.put("mes", "删除成功["+sum+"]条记录");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
		}
		return map;
	}
	
}
