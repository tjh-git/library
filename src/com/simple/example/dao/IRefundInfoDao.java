package com.simple.example.dao;

import java.util.List;
import java.util.Map;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.example.form.RefundInfoForm;
import com.simple.example.po.RefundInfo;

public interface IRefundInfoDao {
	/**
	 * 分页查询退费信息基本信息
	 * @param dgm
	 * @param refundInfo
	 * @return
	 */
	public Map<String, Object> getRefundInfoPage(DataGridModel dgm, RefundInfoForm form);

	/**
	 * 添加退费信息
	 * @param refundInfo
	 * @return
	 */
	public int saveRefundInfo(RefundInfo refundInfo);

	/**
	 * 修改退费信息
	 * @param refundInfo
	 * @return
	 */
	public int updateRefundInfo(RefundInfo refundInfo);

	/**
	 * 删除退费信息
	 * @param idList
	 * @return
	 */
	public int[] delRefundInfo(List<String> idList);

	public int[] checkRefundInfo(List<String> idList, String userId, String userName, String date);
	
	public RefundInfo getRefundInfoByid(String id);
}
