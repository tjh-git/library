package com.simple.example.controller;

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
import com.simple.bsp.common.util.DateTool;
import com.simple.bsp.common.util.Dictionary;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.security.po.PubUsers;
import com.simple.example.form.RefundInfoForm;
import com.simple.example.po.RefundInfo;
import com.simple.example.service.IRefundInfoService;

@Controller
public class RefundInfoController {
	@Autowired
	private IRefundInfoService refundInfoService;
	
	@RequestMapping(value = "/finance/refundInfo", method = RequestMethod.GET)
	public String refundInfo(Model model, HttpServletRequest req) {

		return "example/refund/refund";

	}
	
	@RequestMapping(value = "/finance/refundList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> refundList(DataGridModel dgm, RefundInfoForm form) {

		Map<String, String> tflxMap = Dictionary.getDictionary("TFLX");
		
		Map<String, Object> map = refundInfoService.getRefundInfoPage(dgm, form);

		if(map.get("rows")!=null){
			List<RefundInfoForm> list = (List) map.get("rows");
			
			for (RefundInfoForm refundInfoForm : list) {
				refundInfoForm.setTypeName((String)tflxMap.get(refundInfoForm.getType()));
			}
		}
		
		return map;
	}
	
	@RequestMapping(value = "/finance/addRefundInfoPopWin", method = RequestMethod.GET)
	public String addRefundInfoPopWin(Model model, HttpServletRequest req) {

		//准驾车型码
		List<Map<String, String>> typeList = Dictionary.getListDictionary("TFLX");
		req.setAttribute("typeList", typeList);
		
		return "example/refund/addRefundInfoPopWin";
	}
	
	// 添加退费提交
	@RequestMapping(value = "/finance/addRefundInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addRefundInfo(RefundInfo refundInfo, HttpServletRequest req) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = "";
		String userName = "";
		if (principal instanceof PubUsers) {

			PubUsers user = (PubUsers) principal;
			userId = user.getUserId();
			userName = user.getUserName();
		}
		refundInfo.setId(NextID.getUUID());// 主键
		refundInfo.setUserId(userId);// 制单人ID
		refundInfo.setUserName(userName);// 制单人
		refundInfo.setTime(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd HH:mm:ss"));// 制单时间
		
		refundInfo.setState("0");

		Map<String, String> map = new HashMap<String, String>();
		try {
			if (refundInfoService.saveRefundInfo(refundInfo)>0) {
				map.put("mes", "退费成功");
			} else {
				map.put("mes", "退费失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "退费失败");
		}
		return map;
	}
	
	@RequestMapping(value = "/finance/updateRefundInfoPopWin", method = RequestMethod.GET)
	public String updateRefundInfoPopWin(Model model, HttpServletRequest req) {

		//准驾车型码
		List<Map<String, String>> typeList = Dictionary.getListDictionary("TFLX");
		req.setAttribute("typeList", typeList);
		
		return "example/refund/updateRefundInfoPopWin";
	}
	
	// 修改退费
	@RequestMapping(value = "/finance/updateRefundInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateRefundInfo(RefundInfo refundInfo, HttpServletRequest req) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = "";
		String userName = "";
		if (principal instanceof PubUsers) {

			PubUsers user = (PubUsers) principal;
			userId = user.getUserId();
			userName = user.getUserName();
		}
		refundInfo.setUserId(userId);// 制单人ID
		refundInfo.setUserName(userName);// 制单人
		refundInfo.setTime(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd HH:mm:ss"));// 制单时间
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (refundInfoService.updateRefundInfo(refundInfo)>0) {
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
	
	@RequestMapping(value="/finance/delRefundInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delRefundInfo(@RequestParam("id") List<String> idList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = refundInfoService.delRefundInfo(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			map.put("mes", "删除成功["+sum+"]条记录");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
		}
		return map;//重定向
	}
	
}
