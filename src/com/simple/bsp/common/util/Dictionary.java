package com.simple.bsp.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
	public static HashMap<String, Object> dictionary = new HashMap<String, Object>();		//数据字典数据
	public static HashMap<String, Object> listDictionary = new HashMap<String,Object>();
	
	public static void setDictionary(String key, Map<String, String> keyValue){
		dictionary.put(key,keyValue);
	}
	public static HashMap<String, String> getDictionary(String key){
		HashMap<String, String>  keyValue = (HashMap<String, String>) dictionary.get(key);
		if(keyValue==null){
			keyValue = new HashMap<String, String>();
		}
		return keyValue;
	}
	
	public static void setListDictionary(String parentKey, List<Map<String, String>> list){
		listDictionary.put(parentKey, list);
	}
	
	public static List<Map<String, String>> getListDictionary(String parentkey){
		List<Map<String, String>> list = (List<Map<String, String>>) listDictionary.get(parentkey);
		if(list==null){
			list = new ArrayList<Map<String,String>>();
		}
		return list;
	}
}
