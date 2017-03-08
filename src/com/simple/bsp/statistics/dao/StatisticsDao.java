package com.simple.bsp.statistics.dao;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.statistics.form.LibraryCatalog;
import com.simple.bsp.statistics.form.StatisticsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/11.
 */
@Repository("statisticsDao")
public class StatisticsDao
{
    @Autowired
    private DBUtil util;

    /**
     * 流通率统计
     * @param dgm
     * @param statisticsForm
     * @param school_id
     * @return
     */
    public Map<String,Object> list(DataGridModel dgm, StatisticsForm statisticsForm, String school_id)
    {
        String rows="oppration.id,time,action,user,book,vip.name as user_name,book_catalog.bookname as book_name";
        String count="count(1)";
        String sql="select - from oppration,book_login,vip,book_catalog,book where oppration.user=vip.readCard and oppration.book=book.book_id and book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id";
        if(statisticsForm.getStart().length()==0&&statisticsForm.getEnd().length()==0){}else
        if(statisticsForm.getEnd().length()==0)sql+=" and time="+statisticsForm.getStart();else
        if(statisticsForm.getEnd().length()!=0)sql+=" and time>=date_format('"+statisticsForm.getStart()+"','%Y-%m-%d')"+" and time<=date_format('"+statisticsForm.getEnd()+"','%Y-%m-%d')";
        if(statisticsForm.getAction().length()!=0)sql+=" and action='"+statisticsForm.getAction()+"'";
        if(school_id!=null&&!school_id.equals("0"))
        {
            sql+=" and book.school_id='"+school_id+"'";
        }
        if(statisticsForm.getExport().equals("all"))
        {
            Map<String, Object> params = new HashMap<String, Object>();
            List<Map<String, Object>> result=util.getMapList(sql.replace("-",rows), params);
            params.put("rows",result);
            return params;
        }
        sql+=" order by time desc";
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),dgm.getRows());
        System.out.println(pageQuerySql);
        Map<String, Object> params = new HashMap<String, Object>();
        System.out.println(pageQuerySql);
        List<Map<String, Object>> result=util.getMapList(pageQuerySql.replace("-",rows), params);
        for(int i=0;i<result.size();i++)
        {
            result.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
        }
        int counts=util.getObjCount(sql.replace("-",count));
        params.put("page", dgm.getPage());
        params.put("total", (counts/dgm.getRows())+1+"");
        params.put("rows", result);
        params.put("record", counts);
        return params;
    }
    public int getOneBook(String id)
    {
        String sql = "select borrow_count from book where book_id=" + id;
        Map<String, ?> param = new HashMap<String, Object>();
        param = util.getMap(sql, param);
        return Integer.parseInt(param.get("borrow_count").toString());
    }

    /**
     *图书借阅率统计
     * @param dgm
     * @param statisticsForm
     * @return
     */
    public Map<String,Object> typeList(DataGridModel dgm, StatisticsForm statisticsForm, String school_id)
    {
        int sum=0;
        String sql="select sum(book.borrow_count) as count from book_catalog,book,book_login where book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id group by book_catalog.booktype";
        Map<String, ?> param = new HashMap<String, Object>();
        List<Map<String, Object>> params=new ArrayList<Map<String, Object>>();
        params=util.getMapList(sql,param);
        for(int i=0;i<params.size();i++)sum+=Integer.parseInt(params.get(i).get("count").toString());
        String row=" book_catalog.booktype as class_code,sum(book.borrow_count) as borrow_count,TypeName_TypeCode.TypeName as class_name";
        String count="count(1)";
        sql="select - from TypeName_TypeCode,book_catalog,book_login,book where book_catalog.booktype=TypeName_TypeCode.TypeId and book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id "+(statisticsForm.getType().length()==0?"":"and book_catalog.booktype='"+statisticsForm.getType()+"'");
        if(school_id!=null&&!school_id.equals("0"))
        {
            sql+=" and book.school_id='"+school_id+"'";
        }
        sql+=" group by book_catalog.booktype";
        int counts=util.getMapList(sql.replace("-",row),(Object[])null).size();
        String pageQuerySql = SqlUtil.getPageQuerySql(sql.replace("-",row), dgm.getPage(),dgm.getRows());
        params=util.getMapList(pageQuerySql,param);
        for(int i=0;i<params.size();i++)
        {
            params.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
            double result=(Integer.parseInt(params.get(i).get("borrow_count").toString())*10000/sum)*100.0/10000;
            params.get(i).put("borrow_rate",result+"%");
        }
        Map<String,Object> r=new HashMap<String, Object>();
        r.put("total",(counts/dgm.getRows())+1+"");
        r.put("rows",params);
        r.put("record",counts);
        r.put("page", dgm.getPage());
        return r;
    }

    /**
     * 馆藏统计
     * @param dgm
     * @param
     * @param school_id
     * @return
     */
    public Map<String,Object> catalogList(DataGridModel dgm, LibraryCatalog libraryCatalog, String school_id)
    {
        String sql=null;
        StringBuffer condition=new StringBuffer();
        if(libraryCatalog.getLoginDate()!=null&&!libraryCatalog.getLoginDate().equals(""))condition.append(" and DATE_FORMAT(book_login.buy_date,'%Y-%m-%d')='"+libraryCatalog.getLoginDate()+"'");
        if(libraryCatalog.getPublishDate()!=null&&!libraryCatalog.getPublishDate().equals(""))condition.append(" and DATE_FORMAT(book_catalog.publishdate,'%Y-%m-%d')='"+libraryCatalog.getPublishDate()+"'");
        if(libraryCatalog.getType()!=null&&!libraryCatalog.getType().equals(""))
        {
            String tesql="select TypeId from TypeName_TypeCode where TypeName='"+libraryCatalog.getType()+"'";
            System.out.println(tesql);
            Map<String,?> param=new HashMap<>();
            Map<String,?> re=util.getMap(tesql,param);
            String code=re.get("TypeId")+"";
            condition.append(" and book_catalog.booktype='"+code+"'");
        }
        int sum=0;
        sql="select sum(book_login.buy_num) from book_login,book_catalog where '1'='1' and book_login.catalog_id=book_catalog.catalog_id"+condition;
        Map<String, ?> param = new HashMap<String, Object>();
        List<Map<String, Object>> params=new ArrayList<Map<String, Object>>();
        sum=util.getObjCount(sql);
        System.out.println(sum);
        sql="select book_catalog.booktype,sum(book_login.buy_num) as sum,sum(book_login.buy_amount_price) as sum_price,TypeName_TypeCode.TypeName as type_name from book_catalog,book_login,TypeName_TypeCode where book_catalog.booktype=TypeName_TypeCode.TypeId and  book_login.catalog_id=book_catalog.catalog_id "+condition;
        if(school_id!=null&&!school_id.equals("0"))
        {
            sql+=" and book.school_id='"+school_id+"'";
        }
        sql+=" group by book_catalog.booktype";
        String pageQuerySql=sql;
        int count=util.getMapList(sql,(Object[])null).size();
        System.out.println(dgm.getSort());
        if(!dgm.getSort().equals("all")) pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),dgm.getRows());
        System.out.println(pageQuerySql);
        params=util.getMapList(pageQuerySql,param);
        for(int i=0;i<params.size();i++)
        {
            params.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
            double result=(Integer.parseInt(params.get(i).get("sum").toString())*10000/sum)*100.0/10000;
            params.get(i).put("buy_rate",result+"%");
        }
        Map<String,Object> r=new HashMap<String, Object>();
        r.put("total",(count/(dgm.getRows()==0?1:dgm.getRows()))+1+"");
        r.put("rows",params);
        r.put("record",count);
        r.put("page", dgm.getPage());
        return r;
    }
    public List<Map<String,Object>> getAllType(String show)
    {
        String sql="select TypeId,TypeName from TypeName_TypeCode where TypeName like '"+show+"%'";
        System.out.println(sql);
        return util.getMapList(sql, (Object[]) null);
    }
}