package com.simple.example.dao.impl;

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
import com.simple.example.dao.IRefundInfoDao;
import com.simple.example.form.RefundInfoForm;
import com.simple.example.po.RefundInfo;

@Repository("refundInfoDao")
public class RefundInfoDaoImpl implements IRefundInfoDao {

	@Autowired
	private DBUtil util;

	@Override
	public Map<String, Object> getRefundInfoPage(DataGridModel dgm,
			RefundInfoForm form) {
		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		OrgDesc orgDesc = null;
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			// 根据登录用户的机构编号(userOrg)获取机构描述对象
			orgDesc = util.getOrgDescByOrgId(((PubUsers) principal)
					.getUserOrg());
		} else {
			// 如果登录用户session过期，则返回空结果集
			return result;
		}

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from refund_info r where 1=1 ");

		// 获取结果集
		String quSql = "select r.* "
				+ " from refund_info r "
				+ " where 1=1 ";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		// 点击查询按钮时组装查询语句
		if (form.getStudentNameF() != null
				&& !"".equals(form.getStudentNameF())) {
			sumSql.append(" and r.student_id like '%").append(form.getStudentNameF())
					.append("%' ");
			quSql += " and r.student_id like '%" + form.getStudentNameF() + "%' ";
		}
		if (form.getBeginDate() != null && !"".equals(form.getBeginDate())) {
			sumSql.append(" and r.date >= '").append(form.getBeginDate())
					.append("' ");
			quSql += " and r.date >= '" + form.getBeginDate() + "' ";
		}
		if (form.getEndDate() != null && !"".equals(form.getEndDate())) {
			sumSql.append(" and r.date <= '").append(form.getEndDate())
					.append("' ");
			quSql += " and r.date <= '" + form.getEndDate() + "' ";
		}

		// 组织机构过滤

		// 组装排序规则
		String orderString = " order by r.state,r.time desc ";

		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows",
				util.getObjList(pageQuerySql, params, RefundInfoForm.class));

		return result;
	}

	@Override
	public int saveRefundInfo(RefundInfo refundInfo) {
		String sql = "insert into refund_info (id, student_id, type, remarks, state, money, date, user_id, user_name, time, check_id, check_name, check_time) "
				+ "values (:id, :studentId, :type, :remarks, :state, :money, :date, :userId, :userName, :time, :checkId, :checkName, :checkTime)";
		return util.editObject(sql, refundInfo);
	}

	@Override
	public int updateRefundInfo(RefundInfo refundInfo) {
		StringBuffer sqlSb = new StringBuffer("update refund_info set ");

		sqlSb.append("student_id = :studentId, ");
		sqlSb.append("type = :type, ");
		sqlSb.append("remarks = :remarks, ");
		// sqlSb.append("state = :state, ");
		sqlSb.append("money = :money, ");
		sqlSb.append("date = :date, ");
		sqlSb.append("user_id = :userId, ");
		sqlSb.append("user_name = :userName, ");
		sqlSb.append("time = :time ");
		// sqlSb.append("check_id = :checkId, ");
		// sqlSb.append("check_name = :checkName, ");
		// sqlSb.append("check_time = :checkTime ");

		sqlSb.append(" where id = :id");

		return util.editObject(sqlSb.toString(), refundInfo);
	}

	@Override
	public int[] delRefundInfo(List<String> idList) {
		String sql = "delete from refund_info where id = ?";
		return util.batchDelete(sql, idList);
	}

	@Override
	public int[] checkRefundInfo(List<String> idList, String userId,
			String userName, String date) {
		String sql = "update refund_info set state='1', check_id='" + userId
				+ "', check_name='" + userName + "', check_time='" + date
				+ "' where id = ?";
		return util.batchDelete(sql, idList);
	}

	@Override
	public RefundInfo getRefundInfoByid(String id) {
		String sql = "select * from refund_info where id='" + id + "'";
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<RefundInfo> list = (List<RefundInfo>) util.getObjList(sql, params, RefundInfo.class);
		
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}

}
