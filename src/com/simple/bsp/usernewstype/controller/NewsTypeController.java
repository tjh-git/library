package com.simple.bsp.usernewstype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.usernewstype.po.UserNewsType;
import com.simple.bsp.usernewstype.service.NewsTypeService;
@Controller
public class NewsTypeController {
	@Autowired
	NewsTypeService newsTypeService;
	
	/**
	 * 获取所有的新闻发布类型(不包含用户已有的)
	 * @return
	 */
	
	@RequestMapping(value="/news/getAllNewsType",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAllNewsType(@RequestParam("userId") String userId){
		return newsTypeService.getAllNewsType(userId);
	}
	
	
	/**
	 * 进入新闻类型修改页面
	 * @return
	 */
	@RequestMapping(value="/news/updateNewsTypePopWin",method=RequestMethod.GET)
	public String updateNewsTypePopWin(){
		return "bsp/user/updateNewsTypePopWin";
	}
	
	/**
	 * 获取当前用户所有的新闻发布类型
	 * @return
	 */
	@RequestMapping(value="/news/getAllNewsTypeByUserId",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAllNewsTypeByUserId(@RequestParam("userId") String userId ){
			return newsTypeService.getAllNewsTypeByUserId(userId);
	}
	
	
	/**
	 * 保存用户角色对应关系
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/news/updateUserNewsType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateUserNewsType(@RequestParam("newsTypeCode")
			String newsTypeCode,@RequestParam("userId") String userId){
		
		Map<String, String> map = new HashMap<String, String>();
		 String[] code=newsTypeCode.split(",");
		//先删除用户所拥有的新闻发布类型
		UserNewsType userNewsType=new UserNewsType();
		newsTypeService.delNewsType(userId);
		userNewsType.setUserId(userId);
		int k=0;
		for(int i=0;i<code.length;i++){
		
			userNewsType.setUserNewsTypeId(NextID.getUUID());
			userNewsType.setNewsTypeCode(code[i]);
			if(newsTypeService.addNewsTypeToUser(userNewsType)>0){
				k++;
			}
		}
		map.put("mes", "本次给用户赋予"+code.length+"条类型，成功"+k+"条");
	
	
		return map; 
	}
	

}
