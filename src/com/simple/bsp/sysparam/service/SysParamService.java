/**
 * 
 */
package com.simple.bsp.sysparam.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.PubData;
import com.simple.bsp.sysparam.dao.SysParamDao;
import com.simple.bsp.sysparam.po.SysParam;

/**
 * @author simple
 *
 */
@Service("sysParamService")
public class SysParamService{
	
	private static Logger logger = Logger.getLogger(SysParamService.class);
	
	@Autowired
	private SysParamDao sysParamDao;
	
	/**
	 * 分页查询系统参数
	 * @param dgm
	 * @param param
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, SysParam param){
		return sysParamDao.getPageList(dgm, param);
	}
	
	/**
	 * 保存系统参数
	 * @param param
	 * @return
	 */
	public int saveSysParam(SysParam param){
		return sysParamDao.saveSysParam(param);
	}
	
	/**
	 * 更新系统参数
	 * @param param
	 * @return
	 */
	public int updateSysParam(SysParam param){
//		if(param.getParamCode().toLowerCase().startsWith("sock")){
//			PubData.setSockData("value", param.getParamValue());
//			PubData.setSockData("status", param.getParamStatus());
//			logger.info("SOCK-KEY value 更新为:["+PubData.getSockData("value")+"]");
//			logger.info("SOCK-KEY status 更新为:["+PubData.getSockData("status")+"]");
//		}
//		if(param.getParamCode().toLowerCase().startsWith("api")){
//			PubData.setApiData("value", param.getParamValue());
//			PubData.setApiData("status", param.getParamStatus());
//			logger.info("API-KEY value 更新为:["+PubData.getApiData("value")+"]");
//			logger.info("API-KEY status 更新为:["+PubData.getApiData("status")+"]");
//		}
		return sysParamDao.updateSysParam(param);
	}
	
	/**
	 * 批量删除系统参数
	 * @param idList
	 * @return
	 */
	public int[] delSysParamBatch(List<String> idList){
		return sysParamDao.delSysParamBatch(idList);
	}
	
	/**
	 * 刷新系统参数
	 */
	public int reloadSysParam(){
		return sysParamDao.reloadSysParam();
	}

}
