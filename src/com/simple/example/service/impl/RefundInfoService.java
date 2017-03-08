package com.simple.example.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.example.dao.IRefundInfoDao;
import com.simple.example.form.RefundInfoForm;
import com.simple.example.po.RefundInfo;
import com.simple.example.service.IRefundInfoService;

@Service("refundInfoService")
public class RefundInfoService implements IRefundInfoService {

	@Autowired
	private IRefundInfoDao refundInfoDao;
	
	@Override
	public Map<String, Object> getRefundInfoPage(DataGridModel dgm,
			RefundInfoForm form) {
		return refundInfoDao.getRefundInfoPage(dgm, form);
	}

	@Override
	public int saveRefundInfo(RefundInfo refundInfo) {
		return refundInfoDao.saveRefundInfo(refundInfo);
	}

	@Override
	public int updateRefundInfo(RefundInfo refundInfo) {
		return refundInfoDao.updateRefundInfo(refundInfo);
	}

	@Override
	public int[] delRefundInfo(List<String> idList) {
		return refundInfoDao.delRefundInfo(idList);
	}


}
