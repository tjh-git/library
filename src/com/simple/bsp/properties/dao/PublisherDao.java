package com.simple.bsp.properties.dao;

import com.simple.bsp.common.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17854 on 2016/7/13.
 */
@SuppressWarnings("deprecation")
@Repository("publisherDao")
public class PublisherDao
{
    @Autowired
    private DBUtil util;
    public List<String> getProperties(String book_publisher, String school_id)
    {
        String sql="select book_publisher from publisher where school_id="+school_id;
        String result=util.getMap(sql,(Object[])null).get(book_publisher)+"";
        List<String> publishers=new ArrayList<>();
        String[] ss=result.split(",");
        for(String s:ss)
        {
            if(s!=null&&!s.equals(""))publishers.add(s);
        }
        return publishers;
    }
    public String add(String book_publisher, String substring, String school_id)
    {
        System.out.println(school_id);
        String insert="insert into publisher values(-,-)";
        insert=insert.replaceFirst("-",school_id).replaceFirst("#","'"+substring+"'");
        String update=("update publisher set book_publisher='-' where school_id="+school_id).replaceFirst("-",substring);
        String sql="select count(1) from publisher where school_id="+school_id;
        int  count=util.getObjCount(sql);
        if(count==0)util.editObject(insert,null);else
        util.updateObject(update);
        return "更新参数成功";
    }
}
