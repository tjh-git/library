package com.simple.bsp.properties.web.controller;

import com.simple.bsp.properties.form.SchoolAddForm;
import com.simple.bsp.properties.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 17854 on 2016/7/11.
 */
@Controller
public class SchoolsController
{
    @Autowired
    private SchoolService schoolService;
    @RequestMapping(value = "/statistics/schools/getSchools",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(String page,String rows,HttpSession session,SchoolAddForm schoolAddForm)
    {
        String id = session.getAttribute("school_id")+"";
        if(!id.equals("0"))return null;
        return schoolService.getList(page,rows,schoolAddForm);
    }
    @RequestMapping(value = "/statistics/schools/realeditSchools",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(SchoolAddForm schoolAddForm, HttpSession session)
    {
        String school_id = session.getAttribute("school_id")+"";
        Map<String,String > result=new HashMap<>();
        if(!school_id.equals("0"))
        {
            result.put("mes","更新失败");
            return result;
        }
        try{
         schoolService.edit(schoolAddForm);
        }catch (Exception e)
        {
            e.printStackTrace();
            result.put("mes","更新失败");
        }
        result.put("mes","更新成功");
        return result;
    }
    @RequestMapping(value = "/statistics/schools/editSchools",method = RequestMethod.POST)
    @ResponseBody
    public int edit(SchoolAddForm schoolAddForm,String oper,HttpSession session,String id)
    {
        String school_id = session.getAttribute("school_id")+"";
        if(!school_id.equals("0"))return 0;
        if(oper.equals("edit"))
        {
            return schoolService.edit(schoolAddForm);
        }else
        return schoolService.delete(id);
    }
    @RequestMapping(value = "/statistics/schools/update" ,method = RequestMethod.GET)
    public String update(String school_id, HttpServletRequest request)
    {
        Map<String,String> school= (Map<String, String>) schoolService.getOne(school_id);
        System.out.println(school);
        request.setAttribute("school",school);
        return "bsp/properties/school/blank";
    }
}
