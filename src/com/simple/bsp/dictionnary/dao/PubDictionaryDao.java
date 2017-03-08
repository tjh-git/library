package com.simple.bsp.dictionnary.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.dictionnary.form.PubDictionaryForm;
import com.simple.bsp.dictionnary.po.PubDictionary;

@Repository("pubDictionaryDao")
public class PubDictionaryDao {
	@Autowired
	private DBUtil util;

	/**
	 * 分页查询系统参数
	 */
	public Map<String, Object> getPageList(DataGridModel dgm,
			PubDictionaryForm param) {

		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_dictionary t where 1=1 ");

		// 获取结果集
		String quSql = "select t.id as \"id\", t.num as \"num\", t.name as \"name\", t.father_id as \"fatherId\", t.order_num as \"orderNum\", t.remarks as \"remarks\", t.enable as \"enable\", p.name as \"parentName\", p.num as \"parentNum\" "
				+ " from pub_dictionary t left join pub_dictionary p on t.father_id=p.id where 1=1 ";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(param.getName()!=null&&!param.getName().equals("")){
			sumSql.append(" and t.name like '%"+param.getName()+"%' ");
			quSql = quSql + " and t.name like '%"+param.getName()+"%' ";
		}
		
		if(param.getNum()!=null&&!param.getNum().equals("")){
			sumSql.append(" and t.num like '%"+param.getNum()+"%' ");
			quSql = quSql + " and t.num like '%"+param.getNum()+"%' ";
		}
		
		if(param.getPname()!=null&&!param.getPname().equals("")){
			sumSql.append(" and exists ( select id from pub_dictionary where name like '%"+param.getPname()+"%' and id=t.father_id )");
			quSql = quSql + " and exists ( select id from pub_dictionary where name like '%"+param.getPname()+"%' and id=t.father_id) ";
		}
		
		if(param.getPnum()!=null&&!param.getPnum().equals("")){
			sumSql.append(" and exists ( select id from pub_dictionary where num like '%"+param.getPnum()+"%' and id=t.father_id) ");
			quSql = quSql + " and exists ( select id from pub_dictionary where num like '%"+param.getPnum()+"%' and id=t.father_id) ";
		}

		// 组装排序规则
		String orderString = " order by fatherId, orderNum";

		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));

		return result;
	}
	
	public List<Map<String, Object>> getFatherList() {

		// 获取结果集
		String quSql = "select t.id as \"parentId\", t.name as \"parentName\" "
				+ " from pub_dictionary t where t.father_id is null or t.father_id in (select id from pub_dictionary where num='root' ) ";

		// 组装查询条件
		Map<String, Object> params = new HashMap<String, Object>();

		// 组装排序规则
		quSql += " order by num ";


		// 绑定查询结果('total'和'rows'名称不能修改)
		util.getMapList(quSql, params);

		return util.getMapList(quSql, params);
	}

	public int savePubDictionnary(PubDictionary param) {
		String sql = "insert into pub_dictionary (id, num, name, father_id, order_num, remarks, enable ) "
				+ "values (:id, :num, :name, :fatherId, :orderNum, :remarks, :enable )";
		return util.editObject(sql, param);
	}

	public int updatePubDictionnary(PubDictionary param) {
		String sql = "update pub_dictionary set num = :num, name = :name, father_id = :fatherId, order_num = :orderNum, remarks = :remarks, enable = :enable "
				+ " where id = :id";
		return util.editObject(sql, param);
	}

	public int[] delPubDictionnary(List<String> idList) {
		String sql = "delete from pub_dictionary where id = ?";
		return util.batchDelete(sql, idList);
	}
	
	public int getChildrenCount(List<String> idList){
		String strid = "";
		if(idList!=null&&idList.size()>0){
			for(int i=0; i<idList.size(); i++){
				if(i==0){
					strid = "'"+idList.get(i)+"'";
				}else{
					strid = strid + ",'"+idList.get(i)+"'";
				}
			}
		}
		
		String sql = "select count(1) from pub_dictionary where father_id in ("+strid+")";
		
		return util.getObjCount(sql);
	}
	
	public List<Map<String, Object>> getDictionnary(String key, String paramCode) {

		String sql = "select name as paramName , num as paramValue from "
				+ " pub_dictionary where father_id IN( SELECT id FROM pub_dictionary b where num='" + paramCode + "') ORDER BY num";
		if (null != key) {
			sql = sql + " and num = '" + key + "'";
		}
		return util.getMapList(sql, new HashMap<String, String>());
	}

	public int reloadDictionnary() {
		// TODO Auto-generated method stub
		return 0;
	}
}
