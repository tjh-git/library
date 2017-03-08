package com.simple.bsp.dictionnary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.Dictionary;
import com.simple.bsp.common.util.InitDictionary;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.common.util.PubData;
import com.simple.bsp.dictionnary.form.PubDictionaryForm;
import com.simple.bsp.dictionnary.po.PubDictionary;
import com.simple.bsp.dictionnary.service.PubDictionaryService;

@Controller
public class PubDictionaryController {
	@Autowired
	private PubDictionaryService pubDictionaryService;

	@RequestMapping(value = "/dictionnary", method = RequestMethod.GET)
	public String list(Model model) {
		return "bsp/dictionnary/dictionnary";
	}

	@RequestMapping(value = "/dictionnary/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm,
			PubDictionaryForm param) {
		return pubDictionaryService.getPageList(dgm, param);
	}

	@RequestMapping(value = "/dictionnary/addPopWin", method = RequestMethod.GET)
	public String popWin4Add(HttpServletRequest request,
			HttpServletResponse response) {

		return "bsp/dictionnary/addPopWin";
	}

	@RequestMapping(value = "/dictionnary/queryFatherDictionnary", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> queryFatherDictionnary(
			HttpServletRequest request) throws Exception, Exception {
		List list = pubDictionaryService.getFatherList();
		return list;
	}

	@RequestMapping(value = "/dictionnary/addPubDictionnary", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addPubDictionnary(PubDictionary param) {
		// 添加主键
		param.setId(NextID.getUUID());

		Map<String, String> map = new HashMap<String, String>();
		try {
			if (pubDictionaryService.savePubDictionnary(param) > 0) {
				map.put("mes", "添加数据字典成功");
			} else {
				map.put("mes", "添加数据字典失败");
			}
		} catch (Exception e) {
			map.put("mes", "添加数据字典失败");
		}
		return map;
	}

	@RequestMapping(value = "/dictionnary/updatePopWin", method = RequestMethod.GET)
	public String popWin4Update() {
		return "bsp/dictionnary/updatePopWin";
	}

	/**
	 * 保存修改后的系统参数信息
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/dictionnary/updateDictionnary", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePubDictionnary(PubDictionary param) {

		Map<String, String> map = new HashMap<String, String>();
		try {

			if (pubDictionaryService.updatePubDictionnary(param) > 0) {
				map.put("mes", "更新成功");
			} else {
				map.put("mes", "更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新失败");
		}
		return map;
	}

	@RequestMapping(value = "/dictionnary/delDictionnary", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delPubDictionnary(
			@RequestParam("id") List<String> idList) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			int num = pubDictionaryService.getChildrenCount(idList);

			if (num > 0) {
				map.put("mes", "您要删除的数据含有子节点，请删除子节点后再删除");
			} else {
				int[] result = pubDictionaryService.delPubDictionnary(idList);
				int sum = 0;
				for (int j = 0; j < result.length; j++) {
					sum += result[j];
				}
				if (sum == idList.size()) {
					map.put("mes", "删除成功[" + sum + "]条数据字典记录");
				} else {
					map.put("mes", "删除数据字典失败");
				}
			}

		} catch (Exception e) {
			map.put("mes", "删除数据字典失败");
		}
		return map;
	}

	@RequestMapping(value = "/dictionnary/reloadDictionnary", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> reloadDictionnary() {
		Map<String, String> map = new HashMap<String, String>();
		// 清空内存中的数据字典
		Dictionary.dictionary.clear();
		Dictionary.listDictionary.clear();
		
		try {
			new InitDictionary().init();
			map.put("mes", "刷新数据字典完成！");

		} catch (ServletException e) {
			map.put("mes", "刷新数据字典失败！");
		}

		return map;// 重定向
	}
}
