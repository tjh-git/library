/**
 * 
 */
package com.simple.bsp.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.org.dao.PubTreeDao;
import com.simple.bsp.org.po.PubTree;

/**
 * @author simple
 *
 */
@Service("pubTreeService")
public class PubTreeService{
	
	@Autowired
	private PubTreeDao pubTreeDao;
	

	
	/**
	 * 查询完整的树
	 */
	public List<PubTree> getPubTreeList(){
		return pubTreeDao.getPubTreeList();
	}

	

}
