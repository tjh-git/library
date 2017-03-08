package com.simple.bsp.cant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.cant.dao.PubCantDao;
import com.simple.bsp.cant.service.IPubCantService;

@Service("pubCantService")
public class PubCantService implements IPubCantService {

	@Autowired
	private PubCantDao pubCantDao;

	@Override
	public List getAllListByParentId(String parentId) {
		// TODO Auto-generated method stub
		return pubCantDao.getAllListByParentId(parentId);
	}
}
