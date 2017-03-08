package com.simple.bsp.vip.web.controller;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.vip.po.Student;
import com.simple.bsp.vip.service.StudentService;
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
 * Created by 17854 on 2016/6/1.
 */
@Controller
public class StudentController
{
    @Autowired
    private StudentService studentService;
    @RequestMapping(value="/vip/student/addStudent",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addStudent(Student student, HttpSession session)
    {
        Map<String, String> map = new HashMap<String, String>();
        try {
            if(studentService.addStudent(student,session) > 0){
                map.put("mes", "添加学生成功");
            }else{
                map.put("mes", "添加学生失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "添加学生失败");
        }
        return map;
    }
    @RequestMapping(value="/vip/student",method= RequestMethod.GET)
    public String list(Model model)
    {
        return "bsp/vip/student/student";
    }
    @RequestMapping(value="/vip/student/studentList",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(DataGridModel dgm, Student student)
    {
        return studentService.getPageList(dgm, student);
    }
    @RequestMapping(value="/vip/student/addPopWin",method= RequestMethod.GET)
    public String popWin4Add(){
        return "bsp/vip/student/addPopWin";
    }
    @RequestMapping(value="/vip/student/updatePopWin",method = RequestMethod.GET)
    public String updateWin()
    {
        return "bsp/vip/student/updatePopWin";
    }
    @RequestMapping(value = "/vip/student/updateStudent",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateStudent(Student student,HttpSession session){
        student.setOperator(session.getAttribute("user_name")+"");
        System.out.println(session.getAttribute("user_name")+"");
        Map<String, String> map = new HashMap<String, String>();
        try
        {
            if(studentService.updateStudent(student) > 0){
                map.put("mes", "更新学生信息成功");
            }else{
                map.put("mes", "更新学生信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "更新学生信息失败");
        }
        return map;
    }
    @RequestMapping(value = "/vip/student/delStudents",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delStudents(@RequestParam("readCard") List<String> studentIdList)
    {
        Map<String, String> map = new HashMap<String, String>();
        try {
            int[] result = studentService.deleteStudentrBatch(studentIdList);
            int sum = 0;
            for(int j = 0; j < result.length; j ++){
                sum += result[j];
            }
            if(sum == studentIdList.size()){
                map.put("mes", "删除成功["+(sum)+"]条用户记录");
            }else{
                map.put("mes", "删除成功["+(sum)+"]条用户记录");
                //map.put("mes", "删除学生失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "删除学生失败");
        }
        return map;//重定向
    }
    @RequestMapping(value = "/vip/student/addStudentBatch",method = RequestMethod.POST)
    public String addMuch(MultipartFile file1, String fileName, HttpServletRequest request, HttpSession session)
    {
        int num=0;
        try
        {
            num=studentService.addStudentBatch(file1.getInputStream(), fileName,session);
        }catch (IOException e)
        {
            return "/bsp/vip_new/student/uploadFail";
        }
        request.setAttribute("success",num);
        return "/bsp/vip_new/student/uploadSuccess";
    }
    @RequestMapping(value = "/vip/student/addIframe",method = RequestMethod.GET)
    public String toIframe()
    {
        return "/bsp/vip/student/addIframe";
    }
    @RequestMapping(value = "/vip/student/printit",method = RequestMethod.GET)
    public String printIt(int id, HttpServletRequest request)
    {
        Map<String,String> map=new HashMap<String, String>();
        Student student=studentService.getStudent(id);

        request.setAttribute("student",student);
        return "/bsp/vip_new/student/print";
    }
}
