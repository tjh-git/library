package com.simple.bsp.security.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simple.bsp.security.po.PubAuthorities;
import com.simple.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Repository("pubUsersDao")
public class PubUsersDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 绑定数据源
	 * @param dataSource
	 */
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	}
	
	/**
	 * 根据用户账号获取该用户的所有权限
	 * @param userName
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public List<PubAuthorities> findAuthByUsername(String userName){
		String sql = "select a.authority_id, a.authority_name, a.authority_desc, a.enable, a.is_sys from pub_authorities a, " +
				     "pub_roles_authorities ra, pub_users_roles ur, pub_users u where a.authority_id = ra.authority_id and " +
				     "ra.role_id = ur.role_id and ur.user_id = u.user_id and u.user_account = ?";
				     
		return (List<PubAuthorities>)jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper(PubAuthorities.class));
	}
	
	/**
	 * 根据用户账号获取用户对象
	 * @param userName
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public PubUsers findUserByName(String userName){
		String sql = "select * from pub_users where user_account = ?";
		return (PubUsers)jdbcTemplate.queryForObject(sql, new Object[]{userName}, new BeanPropertyRowMapper(PubUsers.class));
	}

}
