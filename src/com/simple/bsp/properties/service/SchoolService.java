package com.simple.bsp.properties.service;

import com.simple.bsp.properties.dao.RegisterSchoolDao;
import com.simple.bsp.properties.form.SchoolAddForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 17854 on 2016/7/11.
 */
@Service("schoolService")
public class SchoolService
{
    @Autowired
    private RegisterSchoolDao registerSchoolDao;

    public Map<String,Object> getList(String page, String rows, SchoolAddForm schoolAddForm)
    {
        StringBuffer condition=new StringBuffer(" where 1=1");
        if(!schoolAddForm.getSchool_id().equals("-99999999"))condition.append(" and school_id like '%"+schoolAddForm.getSchool_id()+"%'");
        if(schoolAddForm.getSchool_name()!=null&&schoolAddForm.getSchool_name().length()!=0)condition.append(" and school_name like '%"+schoolAddForm.getSchool_name()+"%'");
        if(schoolAddForm.getSchool_pass()!=null&&schoolAddForm.getSchool_pass().length()!=0)condition.append(" and school_pass like '%"+schoolAddForm.getSchool_pass()+"%'");
        System.out.println(condition);
        return registerSchoolDao.getList(Integer.parseInt(page),Integer.parseInt(rows),condition.toString());
    }
    public int edit(SchoolAddForm schoolAddForm)
    {
        return registerSchoolDao.edit(schoolAddForm);
    }

    public int delete(String id)
    {
        String[] ids=id.split(",");
        return registerSchoolDao.delete(ids);
    }

    public Map<String,?> getOne(String school_id)
    {
        return registerSchoolDao.getOne(school_id);
    }
}
