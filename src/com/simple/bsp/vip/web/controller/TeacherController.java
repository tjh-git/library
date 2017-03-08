package com.simple.bsp.vip.web.controller;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.vip.po.Teacher;
import com.simple.bsp.vip.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/5/31.
 */
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/vip/teacher/addTeacher", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addTeacher(Teacher teacher, HttpSession session) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (teacherService.addTeacher(teacher,session) > 0) {
                map.put("mes", "添加教师成功");
            } else {
                map.put("mes", "添加教师失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "添加教师失败");
        }
        return map;
    }

    @RequestMapping(value = "/vip/teacher", method = RequestMethod.GET)
    public String list(Model model) {
        return "bsp/vip/teacher/teacher";
    }

    @RequestMapping(value = "/vip/teacher/teacherList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(DataGridModel dgm, Teacher teacher) {
        return teacherService.getPageList(dgm, teacher);
    }

    @RequestMapping(value = "/vip/teacher/addPopWin", method = RequestMethod.GET)
    public String popWin4Add() {
        return "bsp/vip/teacher/addPopWin";
    }

    @RequestMapping(value = "/vip/teacher/updatePopWin", method = RequestMethod.GET)
    public String updateWin() {
        return "bsp/vip/teacher/updatePopWin";
    }

    @RequestMapping(value = "/vip/teacher/updateTeacher", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateTeacher(Teacher teacher,HttpSession session) {
        Map<String, String> map = new HashMap<String, String>();
        teacher.setOperator(session.getAttribute("user_name")+"");
        try {
            if (teacherService.updateTeacher(teacher) > 0) {
                map.put("mes", "更新教师信息成功");
            } else {
                map.put("mes", "更新教师信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "更新教师信息失败");
        }
        return map;
    }
    @RequestMapping(value = "/vip/teacher/delTeachers", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delTeachers(@RequestParam("readCard") List<String> teacherIdList) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            int[] result = teacherService.deleteTeacherBatch(teacherIdList);
            int sum = 0;
            for (int j = 0; j < result.length; j++) {
                sum += result[j];
            }
            if (sum == teacherIdList.size()) {
                map.put("mes", "删除成功[" + (sum) + "]条用户记录");
            } else {
                //map.put("mes", "删除用户失败");
                map.put("mes", "删除成功[" + (sum) + "]条用户记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "删除用户失败");
        }
        return map;//重定向
    }
    @RequestMapping(value = "/vip/teacher/addTeacherBatch",method = RequestMethod.POST)
 //   @ResponseBody
    public String addMuch(MultipartFile file1, String fileName, HttpServletRequest request, HttpSession session)
    {
        int num=0;
        try
        {
            num=teacherService.addTeacherBatch(file1.getInputStream(), fileName,session);
            request.setAttribute("success",num);

        }catch (IOException e)
        {
            return "bsp/vip_new/teacher/uploadFail";
        }
        return "bsp/vip_new/teacher/uploadSuccess";
    }
    @RequestMapping(value = "/vip/teacher/addIframe",method = RequestMethod.GET)
    public String toIframe()
    {
        return "/bsp/vip/teacher/addIframe";
    }
    @RequestMapping(value = "/vip/teacher/printit",method = RequestMethod.GET)
    public String printIt(int id, HttpServletRequest request)
    {
        Map<String,String> map=new HashMap<String, String>();
        Teacher teacher=teacherService.getTeacher(id);
        request.setAttribute("teacher",teacher);
        return "/bsp/vip_new/teacher/print";
    }
}
