package com.simple.bsp.tree.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.tree.dao.TreeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/jdbc-context.xml","classpath:META-INF/spring-servlet.xml","classpath:META-INF/spring-security.xml","classpath:META-INF/spring-quartz.xml"})
public class TreeServiceTest {
	@Autowired
	TreeDao treeDao;
	@Test
	public void testGetTreeList() {
	List<OrgDesc>	treeList=treeDao.getTreeList();
		System.out.println("$$$$$$$$"+treeList.size());
	}

}
