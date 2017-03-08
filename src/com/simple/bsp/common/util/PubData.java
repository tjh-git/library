package com.simple.bsp.common.util;

import java.util.HashMap;

/**
 * @author simple
 * 系统公共数据，系统启动或参数更新时加载至静态变量中
 *
 */
public class PubData {
	
	public static HashMap<String, Object> pubDataMap = new HashMap<String, Object>();		//系统公共数据
	
	
	/**
	 * 放值
	 * @param key
	 * @param keyValue
	 */
	public static void setPubData(String key,String keyValue){
		pubDataMap.put(key,keyValue);
	}
	/**
	 * 根据key取对应的值
	 * @param key
	 * @return
	 */
	public static Object getPubData(String key){
		Object  keyValue = pubDataMap.get(key);
		if(null == keyValue){
		   return ""; 
		}
		return keyValue;
	}
	
	

}
