/**
 * 
 */
package com.simple.bsp.help.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.help.dao.HelpDao;
import com.simple.bsp.help.po.HelpInfo;

/**
 * @author simple
 *
 */
@Service("helpService")
public class HelpService{
	
	@Autowired
	private HelpDao helpDao;
	
	/**
	 * 获取菜单树
	 */
	public Map<String, Object> getDataInfoPage(DataGridModel dgm,HelpInfo helpInfo){
		return helpDao.getDataInfoPage(dgm,helpInfo);
	}
	
	public HelpInfo getHelpInfoById(String id){
		return helpDao.getHelpInfoById(id);
	}
}
