package com.simple.BookManage.DAO.Impl;

import com.simple.BookManage.DAO.BookDao;
import com.simple.BookManage.RequestBeans.*;
import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/6.
 */
@Repository("BookDao")
public class BookDaoImpl implements BookDao {
    @Autowired
    DBUtil util;

    @Override
    public int SaveBook(Book b) {
        System.out.println(b.getBorrow_status());
        String sql = "insert into book (order_id,book_pos_code, school_id,borrow_status, teacher_touch,student_touch, book_position,catalog_id) "
                + "values (:order_id, :book_pos_code, :school_id,:borrow_status,:teacher_touch,:student_touch, :book_position,:catalog_id)";
        return util.editObject(sql, b);
    }


    @Override
    public Map<String,Object> getBookList(DataGridModel dgm, Book_Catalog book_catalog) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        OrgDesc orgDesc = null;
        // 获取当前登录用户
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        // 获取记录数
        StringBuffer sumSql = new StringBuffer();
        sumSql.append("select count(1) from book,book_catalog where book.catalog_id=book_catalog.catalog_id ");

        // 获取结果集
        String quSql = "select * "
                + " from book,book_catalog where book.catalog_id=book_catalog.catalog_id";

        // 组装查询条件
        StringBuffer sqlSb = new StringBuffer();
        Map<String, Object> params = new HashMap<>();

        if(book_catalog.getBook_id()!=0){
            sumSql.append(" and book.book_id="+book_catalog.getBook_id());
            quSql+=" and book.book_id="+book_catalog.getBook_id();
        }
    /*    if((!"".equals(book_catalog.getBooktype()))&&book_catalog.getBooktype()!=""&&book_catalog.getBooktype()!=null){
            sumSql.append(" and book_catalog.booktype="+book_catalog.getBook_id());
            quSql+=" and book_catalog.booktype="+book_catalog.getBook_id();
        }*/
        if((!"".equals(book_catalog.getBookname()))&&book_catalog.getBookname()!=null){
            sumSql.append(" and book_catalog.bookname='"+book_catalog.getBookname()+"'");
            quSql+=" and book_catalog.bookname='"+book_catalog.getBookname()+"'";
        }
        if((!"".equals(book_catalog.getBookcode()))&&book_catalog.getBookcode()!=null){
            sumSql.append(" and book_catalog.bookcode="+book_catalog.getBookcode());
            quSql+=" and book_catalog.bookcode="+book_catalog.getBookcode();
        }
        if((!"".equals(book_catalog.getBookab()))&&(book_catalog.getBookab()!=null)){
            sumSql.append(" and book_catalog.bookab='"+book_catalog.getBookab()+"'");
            quSql+=" and book_catalog.bookab='"+book_catalog.getBookab()+"'";
        }
        if((!"".equals(book_catalog.getWriter()))&&(book_catalog.getWriter()!=null)){
            sumSql.append(" and book_catalog.writer='"+book_catalog.getWriter()+"'");
            quSql+=" and book_catalog.writer='"+book_catalog.getWriter()+"'";
        }

        String orderString = " order by book.book_id desc";
        System.out.println(sumSql);
        // 组装分页定义
        int counts= util.getObjCount(sumSql.toString());
        String sql = quSql + sqlSb.toString() + orderString;
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
                dgm.getRows());
        System.out.println(pageQuerySql);
        result.put("records",counts);
        result.put("page",dgm.getPage());
        result.put("total",util.getObjCount(sumSql.toString())%dgm.getRows()==0?util.getObjCount(sumSql.toString())/dgm.getRows():util.getObjCount(sumSql.toString())/dgm.getRows()+1);
        List<Map<String,Object>> rows=util.getMapList(pageQuerySql,(Object[])null);
        for(int i=0;i<rows.size();i++)
        {
            rows.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
        }
        result.put("rows",rows);
        result.put("id","book_id");
        return result;
    }

    @Override
    public Map<String, Object> getBookbyName(String bookname) {
        Map<String, Object> result=new HashedMap();
        String quSql = "select * "
                + " from book,book_catalog where book.catalog_id=book_catalog.catalog_id and book_catalog.bookname like'%"+bookname+"%'";
        result.put("rows",
                util.getObjList(quSql, new HashMap<String,Object>(), Book_Catalog.class));
        return result;
    }

}
