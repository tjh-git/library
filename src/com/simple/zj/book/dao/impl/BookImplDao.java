package com.simple.zj.book.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import com.simple.zj.associator.form.SonAssociator;
import com.simple.zj.book.dao.BookDao;
import com.simple.zj.book.form.SonBook;
import com.simple.zj.book.po.Book;


@Repository("bookDao")
public class BookImplDao implements BookDao {
	
	@Autowired
	private DBUtil util;

	@Override
	public Map<String, Object> getBookPage(DataGridModel dgm, SonBook form) {
		
		Map<String, Object> result = new HashMap<String, Object>(2);

		OrgDesc orgDesc = null;

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof PubUsers) {
			orgDesc = util.getOrgDescByOrgId(((PubUsers) principal)
					.getUserOrg());
		} else {
			return result;
		}
		
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from book b where 1=1 ");
		
		String quSql = "select b.* from book b where 1=1 ";
		
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (form.getTsmc() != null && !"".equals(form.getTsmc())) {
			
			sumSql.append("and b.tsmc like '%").append(form.getTsmc())
			      .append("%' ");
			quSql += " and b.tsmc like '%" + form.getTsmc() + "%' ";
		}
		
        if (form.getIsbn() != null && !"".equals(form.getIsbn())) {
			
			sumSql.append("and b.isbn like '%").append(form.getIsbn())
			      .append("%' ");
			quSql += " and b.isbn like '%" + form.getIsbn() + "%' ";
		}
		
        if (form.getBzzz() != null && !"".equals(form.getBzzz())) {
			
			sumSql.append("and b.bzzz like '%").append(form.getBzzz())
			      .append("%' ");
			quSql += " and b.bzzz like '%" + form.getBzzz() + "%' ";
		}
		
        if (form.getCbs() != null && !"".equals(form.getCbs())) {
			
			sumSql.append("and b.cbs like '%").append(form.getCbs())
			      .append("%' ");
			quSql += " and b.cbs like '%" + form.getCbs() + "%' ";
		}
		
		if (form.getStartdate() != null && !"".equals(form.getStartdate())) {
			sumSql.append("and b.lrrp >='").append(form.getStartdate())
					.append("' ");
			quSql += " and b.lrrp >= '" + form.getStartdate() + "' ";
		}
		if (form.getEnddate() !=null && !"".equals(form.getEnddate())) {
			sumSql.append("and b.lrrp <= '").append(form.getEnddate())
					.append("' ");
			quSql += " and b.lrrp <= '" + form.getEnddate() + "' ";
		}

		String orderString = "order by b.lrrp desc";
		
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows",
				util.getObjList(pageQuerySql, params, SonBook.class));

		return result;
	}

	@Override
	public int saveBook(Book book) {
		
		String sql = "insert into book(id, tstm, tsmc, ywfj, csmc, bzzz, fyzz, isbn, cbs, tsyy, ylsx, cbsj, tsbc, tskc, tskb, yszz, tsys, dj, sfgjs, sfqj, lrrp, user_id, org_id)"
				+ " values(:id, :tstm, :tsmc, :ywfj, :csmc, :bzzz, :fyzz, :isbn, :cbs, :tsyy, :ylsx, :cbsj, :tsbc, :tskc, :tskb, :yszz, :tsys, :dj, :sfgjs, :sfqj, :lrrp, :userId, :orgId) ";
		
		return util.editObject(sql, book);
	}

	@Override
	public int updateBook(Book book) {
		StringBuffer sqlSb = new StringBuffer("update book set ");
		sqlSb.append("tstm = :tstm,");
		sqlSb.append("tsmc = :tsmc,");
		sqlSb.append("ywfj = :ywfj,");
		sqlSb.append("csmc = :csmc,");
		sqlSb.append("bzzz = :bzzz,");
		sqlSb.append("fyzz = :fyzz,");
		sqlSb.append("isbn = :isbn,");
		sqlSb.append("cbs = :cbs,");
		sqlSb.append("tsyy = :tsyy,");
		sqlSb.append("ylsx = :ylsx,");
		sqlSb.append("cbsj = :cbsj,");
		sqlSb.append("tsbc = :tsbc,");
		sqlSb.append("tskc = :tskc,");
		sqlSb.append("tskb = :tskb,");
		sqlSb.append("yszz = :yszz,");
		sqlSb.append("tsys = :tsys,");
		sqlSb.append("dj = :dj,");
		sqlSb.append("sfgjs = :sfgjs,");
		sqlSb.append("sfqj = :sfqj,");
		sqlSb.append("lrrp = :lrrp,");
		sqlSb.append("user_id = :userId,");
		sqlSb.append("org_id = :orgId");
		sqlSb.append(" where id = :id");

		return util.editObject(sqlSb.toString(), book);
	}

	@Override
	public int[] delBook(List<String> idList) {
		String sql = "delete from book where id = ?";
		return util.batchDelete(sql, idList);
	}
	
	
	
	

}
