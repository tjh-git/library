package com.simple.bsp.properties.dao;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.properties.form.SchoolAddForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.simple.BookManage.DAO.Impl.BookInfoDaoImpl.html;

/**
 * Created by 17854 on 2016/7/2.
 */
@SuppressWarnings("deprecation")
@Repository("registerSchoolDao")
public class RegisterSchoolDao
{
    @Autowired
    private DBUtil util;
    public int add(SchoolAddForm schoolAddForm)
    {
        Map<String,Object> result=new HashMap<>();
        String sql = "insert into  schools (school_name, school_position, school_contact, school_post, school_tel, school_pass, school_href) values (:school_name, :school_position, :school_contact, :school_post, :school_tel, :school_pass, :school_href)";
        return util.editObject(sql, schoolAddForm);
    }

    public Map<String,Object> getList(int page, int rows, String condition)
    {
        Map<String,Object> list=new HashMap<>();
        String sql="select * from schools "+condition;
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, page,
                rows);
        List<Map<String, Object>> result = util.getMapList(sql,(Object[])null);
        sql="select count(1) from schools"+condition;
        int sum=util.getObjCount(sql);
        for(int i=0;i<result.size();i++)
        {
            result.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
            result.get(i).put("edit",html);
        }
        list.put("rows", result);
        list.put("total",(sum/rows)+1+"");
        list.put("record",sum);
        list.put("page", page);
        return list;
    }
    public int edit(SchoolAddForm schoolAddForm)
    {
        String sql="update schools set school_name=:school_name, school_position=:school_position, school_contact=:school_contact, school_post=:school_post, school_tel=:school_tel,school_pass=:school_pass, school_href=:school_href where school_id=:school_id";
        return util.editObject(sql,schoolAddForm);
    }
    public int delete(String[] ids)
    {
        List<String> id=new ArrayList<>();
        for(String value:ids)
        {
            id.add(value);
        }
        String sql="delete from schools where school_id = ?";
        util.batchDelete(sql,id);
        return 0;
    }
    public Map<String,?> getOne(String school_id)
    {
        String sql="select * from schools where school_id="+school_id;
        return util.getMap(sql,(Object[])null);
    }
}
