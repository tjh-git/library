package com.simple.bsp.properties.dao;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.properties.form.PropertiesForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 17854 on 2016/7/13.
 */
@SuppressWarnings("deprecation")
@Repository("setPropertiesDao")
public class SetPropertiesDao
{
    @Autowired
    private DBUtil util;
    public String setProperties(String sqlInsert, String sqlUpdate, String school_id, PropertiesForm propertiesForm)
    {
        String sql="select count(1) from properties where school_id="+school_id;
        int  count=util.getObjCount(sql);
        if(count==0)util.editObject(sqlInsert,propertiesForm);else
        util.updateObject(sqlUpdate);
        return "更新参数成功";
    }
    public String getOneProperty(String key, String school_id)
    {
        String sql="select * from properties where school_id="+school_id;
        return util.getMap(sql.replaceAll("\\*",key),(Object[])null).get(key)+"";
    }
}
