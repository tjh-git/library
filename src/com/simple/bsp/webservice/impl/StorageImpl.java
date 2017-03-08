package com.simple.bsp.webservice.impl;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.simple.bsp.webservice.Storage;
@WebService(endpointInterface="com.simple.bsp.webservice.Storage")
public class StorageImpl implements Storage {
	
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	
	/**
	 * 绑定数据源
	 * @param dataSource
	 */
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	

	@Override
	public String storageData(String storageList) {
	
		return null;
	}

}
