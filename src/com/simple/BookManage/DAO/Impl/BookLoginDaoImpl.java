package com.simple.BookManage.DAO.Impl;

import com.simple.BookManage.DAO.BookLoginDao;
import com.simple.BookManage.RequestBeans.BookLogin;
import com.simple.BookManage.RequestBeans.Login_Catalog;
import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/6.
 */
@Repository("BookLoginDao")
public class BookLoginDaoImpl implements BookLoginDao {


    @Autowired
    DBUtil util;

    @Override
    public int SaveBookLogin(BookLogin bookLogin) {
        String sql = "insert into book_login (catalog_id,book_from,book_position,book_buy_price,buy_num,buy_amount_price,operator_id,operator_name,buy_date) "
                + "values (:catalog_id, :book_from, :book_position,:book_buy_price, :buy_num, :buy_amount_price, :operator_id, :operator_name, :buy_date)";
        return util.editObject(sql, bookLogin);
    }

    @Override
    public int getLastOrder() {
        return util.getLastIndex("select LAST_INSERT_ID()");
    }

    @Override
    public Map<String, Object> getBookLoginPage(DataGridModel dgm, Login_Catalog bookLogin) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        // 获取记录数
        StringBuffer sumSql = new StringBuffer();
        sumSql.append("select count(1) from book_login,book_catalog where book_login.catalog_id=book_catalog.catalog_id ");

        // 获取结果集
        String quSql = "select * "
                + " from book_login,book_catalog where book_login.catalog_id=book_catalog.catalog_id ";

        // 组装查询条件
        StringBuffer sqlSb = new StringBuffer();
        Map<String, Object> params = new HashMap<>();

//        // 点击查询按钮时组装查询语句
        if (!("".equals(bookLogin.getBookcode()))&&bookLogin.getBookcode() != null) {
            sumSql.append(" and book_catalog.bookcode=").append(bookLogin.getBookcode());
            quSql += " and book_catalog.bookcode=" + bookLogin.getBookcode();
        }
        if (!("".equals(bookLogin.getBookname()))&&!(bookLogin.getBookname()==null)){
            sumSql.append(" and book_catalog.bookname= '").append(bookLogin.getBookname()).append("' ");
            quSql += " and book_catalog.bookname= '" + bookLogin.getBookname() + "' ";
        }
        if (!("".equals(bookLogin.getBookab()))&&!(bookLogin.getBookab()==null)){
            sumSql.append(" and book_catalog.bookab= '").append(bookLogin.getBookab()).append("' ");
            quSql += " and book_catalog.bookab= '" + bookLogin.getBookab() + "' ";
        }
        if (!("".equals(bookLogin.getWriter()))&&!(bookLogin.getWriter()==null)){
            sumSql.append(" and book_catalog.writer= '").append(bookLogin.getWriter()).append("' ");
            quSql += " and book_catalog.writer= '" + bookLogin.getWriter() + "' ";
        }


        // 组织机构过滤

        // 组装排序规则
        String orderString = " order by book_login.order_id desc";

        // 组装分页定义
        String sql = quSql + sqlSb.toString() + orderString;
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
                dgm.getRows());

        // 绑定查询结果('total'和'rows'名称不能修改)
        result.put("records", util.getObjCount(sumSql.toString()));
        result.put("page",dgm.getPage());
        result.put("total",util.getObjCount(sumSql.toString())%10==0?util.getObjCount(sumSql.toString())/10:util.getObjCount(sumSql.toString())/10+1);
        List<Map<String,Object>> rows=util.getMapList(pageQuerySql,(Object[])null);
        System.out.println(pageQuerySql);
        for(int i=0;i<rows.size();i++)
        {
            rows.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
        }
        result.put("rows", rows);
        return result;
    }


}
