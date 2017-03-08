package com.simple.zj.associator.service;

import java.util.List;
import java.util.Map;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.zj.associator.form.SonAssociator;
import com.simple.zj.associator.po.Associator;

public interface AssociatorService {
	
	public Map<String,Object> getAssociatorPage(DataGridModel dgm, SonAssociator form);

	public int saveAssociator(Associator associator);
	
	public int updateAssociator(Associator associator);
	
	public int[] delAssociator(List<String> idList);
}
