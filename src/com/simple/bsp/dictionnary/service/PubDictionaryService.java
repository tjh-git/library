package com.simple.bsp.dictionnary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.dictionnary.dao.PubDictionaryDao;
import com.simple.bsp.dictionnary.form.PubDictionaryForm;
import com.simple.bsp.dictionnary.po.PubDictionary;

@Service("pubDictionaryService")
public class PubDictionaryService {
	@Autowired
	private PubDictionaryDao pubDictionaryDao;
	
	public Map<String, Object> getPageList(DataGridModel dgm,
			PubDictionaryForm param){
		return pubDictionaryDao.getPageList(dgm, param);
	}
	
	public int savePubDictionnary(PubDictionary param){
		return pubDictionaryDao.savePubDictionnary(param);
	}
	
	public int updatePubDictionnary(PubDictionary param) {
		return pubDictionaryDao.updatePubDictionnary(param);
	}
	
	public int[] delPubDictionnary(List<String> idList){
		return pubDictionaryDao.delPubDictionnary(idList);
	}
	
	public List<Map<String, Object>> getFatherList() {
		return pubDictionaryDao.getFatherList();
	}
	
	public int getChildrenCount(List<String> idList){
		return pubDictionaryDao.getChildrenCount(idList);
	}
	
	public List<Map<String, Object>> getDictionnary(String key,String paramCode){
		return pubDictionaryDao.getDictionnary(key,paramCode);
	}
	
	/**
	 * 刷新数据字典
	 */
	public int reloadDictionnary(){
		return pubDictionaryDao.reloadDictionnary();
	}
}
