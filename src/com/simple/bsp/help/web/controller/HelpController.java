/**
 * 
 */
package com.simple.bsp.help.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.help.po.HelpInfo;
import com.simple.bsp.help.service.HelpService;

/**
 * @author simple
 *
 */
@Controller
public class HelpController {
	
	@Autowired
	private HelpService helpService;
	
	@RequestMapping(value = "/pubHelp", method = RequestMethod.GET)
	public String pubHelpInit(Model model,HttpServletRequest req) {

		String helpId=req.getParameter("helpId");
		req.setAttribute("id", helpId);
		HelpInfo helpInfo=helpService.getHelpInfoById(helpId);
		
		String param1=req.getParameter("param1");
		String param2=req.getParameter("param2");
		
		req.setAttribute("param1", param1);
		req.setAttribute("param2", param2);
		
		//拼装查询条件
		String query=helpInfo.getHelpQuery();
		
		List<Map<String, Object>> queryParamList=new ArrayList<Map<String, Object>>();

		String [] queryArray=query.split(",");
		for(int i=0;i<queryArray.length;i++){
			String row=queryArray[i];
			String[] rowArray=row.split("#");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("key", rowArray[0]);
			paramMap.put("value", rowArray[1]);
			
			queryParamList.add(paramMap);
		}		
		req.setAttribute("queryParamList", queryParamList);

		String show=helpInfo.getHelpShow();
		Map<String, Object> showMap = new HashMap<String, Object>();
		
		String [] showArray=show.split(",");
		for(int i=0;i<showArray.length;i++){
			String row=showArray[i];
			String[] rowArray=row.split("#");			
			showMap.put(rowArray[0], row);		
		}
		
		req.setAttribute("showMap", showMap);
		
		List<String> columList=new ArrayList<String>();
		String colum=helpInfo.getHelpColum();
		String [] columArray=colum.split(",");
		columList=Arrays.asList(columArray);
		
		req.setAttribute("columList", columList);
		
		String easyUiString="";
		for(int j=1;j<columList.size();j++){
			
			
			easyUiString=easyUiString+"{field:'"+columList.get(j)+"',title:'";
			
			if(showMap.get(columList.get(j))!=null){
				
				String showStr=(String)showMap.get(columList.get(j));
				String[] showStrArray=showStr.split("#");
				
				easyUiString=easyUiString+showStrArray[1]+"',width:"+showStrArray[2]+",sortable:true,formatter:function(value,row,index){return row."+columList.get(j)+";}},";
			}else{
				easyUiString=easyUiString+"',width:200,sortable:true,hidden:true,formatter:function(value,row,index){return row."+columList.get(j)+";}},";
			}
			
		}
		
		req.setAttribute("easyUiString", easyUiString);
		
//		System.out.println("easyUiString:"+easyUiString);
		
		return "bsp/help/help";

	}

	@RequestMapping(value = "/help/dataList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dataInfoList(DataGridModel dgm,HelpInfo helpInfo) {

		HelpInfo helpInfoNew=helpService.getHelpInfoById(helpInfo.getId());
		helpInfo.setHelpColum(helpInfoNew.getHelpColum());
		helpInfo.setHelpQuery(helpInfoNew.getHelpQuery());
		helpInfo.setHelpShow(helpInfoNew.getHelpShow());
		helpInfo.setHelpSql(helpInfoNew.getHelpSql());
		
		
		Map<String, Object> map = helpService.getDataInfoPage(dgm, helpInfo);

		return map;

	}

}
