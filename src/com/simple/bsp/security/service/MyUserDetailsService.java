package com.simple.bsp.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.simple.bsp.security.dao.PubUsersDao;
import com.simple.bsp.security.po.PubAuthorities;
import com.simple.bsp.security.po.PubUsers;

/**
 * 该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 * 该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 * 
 * @author simple
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private static Logger logger = Logger.getLogger(MyUserDetailsService.class);
	
	@Autowired
	private PubUsersDao pubUsersDao;
	
	public void setPubUsersDao(PubUsersDao pubUsersDao){
		this.pubUsersDao = pubUsersDao;
	}
	
	public PubUsersDao getPubUsersDao (){
		return this.pubUsersDao;
	}
	
	/**
	 * 获取登录用户基本信息和拥有的权限
	 */
	@SuppressWarnings({ "unchecked" })
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		
		if(null == pubUsersDao){
			System.out.println("[pubUsersDao]为null");
			pubUsersDao = new PubUsersDao();
		}
		
		//取得用户对象
		PubUsers pUser = pubUsersDao.findUserByName(username);
		//String password = null;
		//password = pUser.getUserPassword();
			
		//取得用户的权限
		List<PubAuthorities> authList=pubUsersDao.findAuthByUsername(username);
		
		//System.out.println("用户["+username+"]登录！权限有：");
		logger.info("用户["+username+"]登录！权限有：");
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
		for(PubAuthorities auth: authList){
			//System.out.println("["+auth.getAuthorityName()+"]-["+auth.getAuthorityId()+"]");
			logger.info("["+auth.getAuthorityName()+"]");
			auths.add(new SimpleGrantedAuthority(auth.getAuthorityId()));
		}
			
	    //User user = new User(username, password, true, true, true, true, auths);
		PubUsers user = new PubUsers(pUser.getUserId(), pUser.getUserAccount(), pUser.getUserName(), pUser.getUserPassword(), 
				pUser.getUserOrg(), pUser.getUserDuty(), pUser.getUserDesc(), pUser.getEnable(), pUser.getIsSys(), new HashSet(0), 
				true, true, true, auths);
		
	    return user;
	}

}
