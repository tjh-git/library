package com.simple.bsp.properties.web.controller;

import com.simple.bsp.properties.form.SchoolAddForm;
import com.simple.bsp.properties.service.RegisterSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 17854 on 2016/7/2.
 */
@Controller
public class RegisterSchoolController
{
    @Autowired
    private RegisterSchoolService registerSchoolService;
    @RequestMapping(value = "/properties/school/add")
    @ResponseBody
    public Map<String,String> add(SchoolAddForm schoolAddForm)
    {
        Map<String, String> map = new HashMap<String, String>();
        try {
            if(registerSchoolService.add(schoolAddForm) > 0){
                map.put("mes", "添加学校成功");
            }else{
                map.put("mes", "添加学校失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "添加学校失败");
        }
        return map;
    }
}
