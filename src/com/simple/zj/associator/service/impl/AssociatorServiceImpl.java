package com.simple.zj.associator.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.zj.associator.dao.AssociatorDao;
import com.simple.zj.associator.form.SonAssociator;
import com.simple.zj.associator.po.Associator;
import com.simple.zj.associator.service.AssociatorService;

@Service("associatorService")
public class AssociatorServiceImpl implements AssociatorService {
    
	@Autowired
	private AssociatorDao associatorDao;
	
	
	@Override
	public Map<String, Object> getAssociatorPage(DataGridModel dgm,
			SonAssociator form) {
		
		return associatorDao.getAssociatorPage(dgm, form);
	}

	@Override
	public int saveAssociator(Associator associator) {
		// TODO Auto-generated method stub
		return associatorDao.saveAssociator(associator);
	}

	@Override
	public int updateAssociator(Associator associator) {
		// TODO Auto-generated method stub
		return associatorDao.updateAssociator(associator);
	}

	@Override
	public int[] delAssociator(List<String> idList) {
		// TODO Auto-generated method stub
		return associatorDao.delAssociator(idList);
	}

}
