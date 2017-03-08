package com.simple.zj.associator.dao.impl;

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
import com.simple.example.form.RefundInfoForm;
import com.simple.zj.associator.dao.AssociatorDao;
import com.simple.zj.associator.form.SonAssociator;
import com.simple.zj.associator.po.Associator;

@Repository("associatorDao")
public class AssciatorImpl implements AssociatorDao {

	@Autowired
	private DBUtil util;

	@Override
	public Map<String, Object> getAssociatorPage(DataGridModel dgm,
			SonAssociator form) {

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
		sumSql.append("select count(1) from associator a where 1=1 ");

		String quSql = "select a.* from associator a  where 1=1 ";

		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (form.getName() != null && !"".equals(form.getName())) {
			sumSql.append("and a.name like '%").append(form.getName())
					.append("%' ");
			quSql += " and a.name like'%" + form.getName() + "%'";
		}
		
		if (form.getIdcard() != null && !"".equals(form.getIdcard())) {
			sumSql.append("and a.idcard like '%").append(form.getIdcard())
			        .append("%' ");
			quSql += " and a.idcard like '%" + form.getIdcard() + "%' ";
		}
		
		if (form.getState() != null && !"".equals(form.getState())) {
			sumSql.append("and a.state like '%").append(form.getState())
			        .append("%' ");
			quSql += " and a.state like '%" + form.getState() + "%' ";
		}
		
		if(form.getCardtype() != null && !"".equals(form.getCardtype())) {
			sumSql.append(" and a.cardtype = '").append(form.getCardtype())
			        .append("' ");
			quSql += " and a.cardtype = '" + form.getCardtype() + "' ";
		}

		if (form.getStartdate() != null && !"".equals(form.getStartdate())) {
			sumSql.append("and a.join_date >='").append(form.getStartdate())
					.append("' ");
			quSql += " and a.join_date >= '" + form.getStartdate() + "' ";
		}
		if (form.getEnddate() !=null && !"".equals(form.getEnddate())) {
			sumSql.append("and a.join_date <= '").append(form.getEnddate())
					.append("' ");
			quSql += " and a.join_date <= '" + form.getEnddate() + "' ";
		}

		String orderString = "order by a.join_date desc";

		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows",
				util.getObjList(pageQuerySql, params, SonAssociator.class));

		return result;

	}

	@Override
	public int saveAssociator(Associator associator) {
		String sql = "insert into associator(id, name , idcard, gender, cardno, cardtype, expiry_date, join_date , state, teacher_no, courses, phone , qq, mail, stu_no, org_id)"
				+ " values(:id, :name, :idcard, :gender, :cardno, :cardtype, :expiryDate, :joinDate, :state, :teacherNo, :courses, :phone, :qq, :mail, :stuNo, :orgId) ";
		return util.editObject(sql, associator);
	}

	@Override
	public int updateAssociator(Associator associator) {
		StringBuffer sqlSb = new StringBuffer("update associator set ");
		sqlSb.append("name = :name,");
		sqlSb.append("idcard = :idcard,");
		sqlSb.append("gender = :gender,");
		sqlSb.append("cardno = :cardno,");
		sqlSb.append("cardtype = :cardtype,");
		sqlSb.append("expiry_date = :expiryDate,");
		sqlSb.append("join_date = :joinDate,");
		sqlSb.append("state = :state,");
		sqlSb.append("teacher_no = :teacherNo,");
		sqlSb.append("courses = :courses,");
		sqlSb.append("phone = :phone,");
		sqlSb.append("qq = :qq,");
		sqlSb.append("mail = :mail,");
		sqlSb.append("stu_no = :stuNo,");
		sqlSb.append("org_id = :orgId");
		sqlSb.append(" where id = :id");

		return util.editObject(sqlSb.toString(), associator);
	}

	@Override
	public int[] delAssociator(List<String> idList) {
		String sql = "delete from associator where id = ?";
		return util.batchDelete(sql, idList);
	}

}
