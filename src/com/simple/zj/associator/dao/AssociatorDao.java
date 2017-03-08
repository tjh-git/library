package com.simple.zj.associator.dao;

import java.util.List;
import java.util.Map;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.zj.associator.form.SonAssociator;
import com.simple.zj.associator.po.Associator;

public interface AssociatorDao {
    /* map查询 */
	public Map<String,Object> getAssociatorPage(DataGridModel dgm, SonAssociator form);
	/* 保存 */
	public int saveAssociator(Associator associator);
	/* 修改 */
	public int updateAssociator(Associator associator);
	/* 删除 */
	public int[] delAssociator(List<String> idList);
	
	/*public int[] checkAssociator(List<String> idList, String userId, String username, String date);
	
	public Associator getAssociator(String id);
	*/
	 
}
