package com.simple.bsp.properties.service;

import com.simple.bsp.properties.dao.RegisterSchoolDao;
import com.simple.bsp.properties.dao.SetPropertiesDao;
import com.simple.bsp.properties.form.PropertiesForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 17854 on 2016/6/10.
 */
@Service("propertiesSetService")
public class PropertiesSetService
{
    @Autowired
    private SetPropertiesDao setPropertiesDao;
    public String setProperties(PropertiesForm propertiesForm, String school_id)
    {
        String sqlInsert = "insert into properties(school_id,$,$,$,$,$,$,$,$,$,$,$,$,$,$,$,$,$,$) values(&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&,:&)";
        sqlInsert=sqlInsert.replaceFirst("&",school_id);
        String sqlUpdate = "update properties set $=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=&,$=& where school_id="+school_id;
        Class form = propertiesForm.getClass();
        Field[] fields = form.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            if (fields[i].getName().contains("def") || fields[i].getName().equals("book_publisher")) continue;
            String getter = "get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1, fields[i].getName().length());
            String result = null;
            try {
                Method m = form.getMethod(getter, new Class[]{});
                result = (String) m.invoke(propertiesForm, new Object[]{});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return "参数更新失败";
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                return "参数更新失败";
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return "参数更新失败";
            }
            if (fields[i].getName().equals("s_isFine") && result == null) {
                result = "off";
            }
            if (fields[i].getName().equals("t_isFine") && result == null) {
                result = "off";
            }
            sqlInsert=sqlInsert.replaceFirst("\\$",fields[i].getName()).replaceFirst("&",fields[i].getName());
            sqlUpdate=sqlUpdate.replaceFirst("\\$=&",fields[i].getName()+"='"+result+"'");
        }
        return setPropertiesDao.setProperties(sqlInsert,sqlUpdate,school_id,propertiesForm);
    }
    public String getOneProperty(String key, String school_id)
    {
        return setPropertiesDao.getOneProperty(key,school_id);
    }
}
