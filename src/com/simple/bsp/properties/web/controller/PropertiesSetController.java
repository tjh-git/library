package com.simple.bsp.properties.web.controller;
import com.simple.bsp.properties.form.PropertiesForm;
import com.simple.bsp.properties.service.PropertiesSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Controller
public class PropertiesSetController
{
    @Autowired
    private PropertiesSetService propertiesSetService;
    @RequestMapping(value = "/properties/getone",method = RequestMethod.POST)
    @ResponseBody
    public List<String> getone(String key,HttpSession school_id)
    {
        return getProperties(key,school_id);
    }
    @RequestMapping(value = "/properties/getall",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,List<String>> getall(HttpSession session)
    {
        Map<String,List<String>> result=new HashMap<>();
        String school_id=session.getAttribute("school_id")+"";
        Field[] fields=PropertiesForm.class.getDeclaredFields();
        for(int i=0;i<fields.length;i++)
        {
            if(fields[i].getName().contains("def")||fields[i].getName().equals("book_publisher"))continue;
            result.put(fields[i].getName(),getProperties(fields[i].getName(),session));
        }
        return result;
    }
    public static boolean contain(String[] a,String b)
    {
        for(String s:a)
        {
            if(s.equals(b))return true;
        }
        return false;
    }
    @RequestMapping(value = "/properties/setproperties",method = RequestMethod.POST)
    @ResponseBody
    public String setProperties(PropertiesForm propertiesForm,HttpSession session)
    {
        StringBuffer sb=null;
        String[] a=null;
        if(propertiesForm.getBook_size_def()!=null) {
            sb = new StringBuffer(propertiesForm.getBook_size_def());
            a = propertiesForm.getBook_size().split(", ");
            if (contain(a,sb.toString())) {
                for (String s : a) {
                    if (!s.equals(propertiesForm.getBook_size_def())) sb.append(", " + s);
                }
                propertiesForm.setBook_size(sb.toString());
            }
        }
//////////////////////////////////////////////////////////////////////////////////////////
        if(propertiesForm.getBook_code_def()!=null) {
            sb = new StringBuffer(propertiesForm.getBook_code_def());
            a = propertiesForm.getBook_code().split(", ");
            if (contain(a,sb.toString())) {
                for (String s : a) {
                    if (!s.equals(propertiesForm.getBook_code_def())) sb.append(", " + s);
                }
                propertiesForm.setBook_code(sb.toString());
            }
        }
///////////////////////////////////////////////////////////////////////////////////
        if(propertiesForm.getBook_lunguage_def()!=null) {
            sb = new StringBuffer(propertiesForm.getBook_lunguage_def());
            a = propertiesForm.getBook_lunguage().split(", ");
            if (contain(a,sb.toString())) {
                for (String s : a) {
                    if (!s.equals(propertiesForm.getBook_lunguage_def())) sb.append(", " + s);
                }
                propertiesForm.setBook_lunguage(sb.toString());
            }
        }
///////////////////////////////////////////////////////////////////////////////////////
        if(propertiesForm.getBook_layout_def()!=null) {
            sb = new StringBuffer(propertiesForm.getBook_layout_def());
            a = propertiesForm.getBook_layout().split(", ");
            if (contain(a,sb.toString())) {
                for (String s : a) {
                    if (!s.equals(propertiesForm.getBook_layout_def())) sb.append(", " + s);
                }
                propertiesForm.setBook_layout(sb.toString());
            }
        }
        return propertiesSetService.setProperties(propertiesForm,session.getAttribute("school_id")+"");
    }
    public  List<String> getProperties(String key,HttpSession session)
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
