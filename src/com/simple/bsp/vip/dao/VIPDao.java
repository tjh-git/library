package com.simple.bsp.vip.dao;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.vip.form.VIPForm;
import com.simple.bsp.vip.po.VIP;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.simple.BookManage.DAO.Impl.BookInfoDaoImpl.html;

/**
 * Created by 17854 on 2016/6/2.
 */
@Repository("vipDao")
public class VIPDao
{

    @Autowired
    private DBUtil util;
    public Map<String,Object> getPageList(DataGridModel dgm, VIPForm vipForm, String sidx, String school_id)
    {
        // 查询结果Map
        Map<String, Object> result = new HashMap<String, Object>(2);
        OrgDesc orgDesc = null;
        // 获取记录数
        StringBuffer sumSql = new StringBuffer();
        sumSql.append("select count(1) from vip");
        // 获取结果集
        String quSql = "select *  from vip";
        // 组装查询条件
        StringBuffer sqlSb = new StringBuffer();
        sqlSb.append(" where '1'='1' ");
        if(vipForm.getCardType()!=null&&!vipForm.getCardType().equals(""))
        sqlSb.append(" and cardtype='"+vipForm.getCardType()+"'");
        System.out.println(sqlSb);
        if(vipForm.getName()!=null&&!vipForm.getName().equals(""))
        sqlSb.append(" and name like'%"+vipForm.getName()+"%'");
        if(vipForm.getReadCard()!=null&&!vipForm.getReadCard().equals(""))
        sqlSb.append(" and readCard like'%"+vipForm.getReadCard()+"%'");
        if(school_id!=null&&!school_id.equals("0"))
        {
            sqlSb.append(" and school_id='"+school_id+"'");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        // 组装排序规则
        String orderString = "";
        if (StringUtils.isNotBlank(dgm.getSort())) {
            orderString = " order by \"" + dgm.getSort() + "\" " ;
        }
        if(vipForm.getType().equals("all"))
        {
            System.out.println(sqlSb);
            result.put("rows", util.getMapList(quSql+sqlSb.toString(), params));
            return result;
        }
        // 组装分页定义
        String sql = quSql + sqlSb.toString() + orderString ;
        int count=util.getMapList(sql,(Object[])null).size();
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
                dgm.getRows());
        // 绑定查询结果('total'和'rows'名称不能修改)
        result.put("total",(count/dgm.getRows())+1+"");
        System.out.println(pageQuerySql);
        List<Map<String,Object>> rows=util.getMapList(pageQuerySql,(Object[])null);
        System.out.println("12222");
        for(int i=0;i<rows.size();i++)
        {
            rows.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
            rows.get(i).put("edit",html);
        }
        result.put("rows", rows);
        result.put("record",count);
        result.put("page", dgm.getPage());
        return result;
    }
    public int updateVIP(VIP vip, String user_name)
    {
        vip.setOperator(user_name);
        StringBuffer sqlSb = new StringBuffer("update vip set readCard=:readCard, name=:name, IDCard=:IDCard, gender=:gender, cardType=:cardType,addTime=:addTime,status=:status,teachID=:teachID,teachClass=:teachClass,tel=:tel,qq=:qq,email=:email,operator=:operator");
        sqlSb.append(" where id=:id");
        return util.editObject(sqlSb.toString(),vip);
    }

    public int[] delVIPBatch(List<String> vipIdList)
    {
        String sql = "delete from vip where id=?";
        return util.batchDelete(sql, vipIdList);
    }
    public int saveTMoney(String id, String saveMoney)
    {
        String sql="select money from vip where id="+id;
        Map<String, ?> re=util.getMap(sql,(Object[])null);
        String money=re.get("money")+"";
        double afmon=Double.parseDouble(money)+Double.parseDouble(saveMoney);
        System.out.println(money+"   "+saveMoney+"   "+afmon);
        sql="update vip set money="+afmon+" where id="+id;
        return util.updateObject(sql);
    }

    public Map<String,Object> one(String id, String school_id)
    {
        System.out.println(id+"   "+school_id);
        String sql="select * from vip where id="+id+" and school_id="+school_id;
        List<Map<String, Object>> rows=util.getMapList(sql,(Object[])null);
        Map < String , Object > result = new HashMap < > ( ) ;
        result.put("rows",rows);
        return result;
    }
}
