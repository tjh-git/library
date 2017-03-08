package com.simple.bsp.properties.web.controller;

import com.simple.bsp.properties.service.PropertiesSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17854 on 2016/7/13.
 */
@Component("getpro")
public class GetProper
{
    @Autowired
    private PropertiesSetService propertiesSetService;
    public List<String> getProperties(String key, HttpSession session)
    {
        String s=propertiesSetService.getOneProperty(key,session.getAttribute("school_id")+"");
        if(s==null)return new ArrayList<>();
        String split=null;
        if(!key.equals("book_publisher"))split=", ";else split=",";
        String[] ss=s.split(split);
        List<String> result=new ArrayList<String>();
        for(int i=0;i<ss.length;i++)
        {
            if(ss[i].length()!=0)result.add(ss[i]);
        }
        return result;
    }
}
