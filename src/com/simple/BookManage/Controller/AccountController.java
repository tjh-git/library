package com.simple.BookManage.Controller;

import com.simple.BookManage.RequestBeans.Operator;
import com.simple.BookManage.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lovesyxfuffy on 2016/7/2.
 */
@Controller
public class AccountController {
    @Autowired
    AccountService service;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login_index(){
        return "BookManager_new/Login/login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        Object result=service.login(username,password);
        if(result==null){

            return "fail";
        }
        else {
            Operator opt=(Operator)result;
            session.setAttribute("school_id",opt.getSchool_id());
            session.setAttribute("username",opt.getUsername());
            session.setAttribute("user_id",opt.getUser_id());
            return "success";
        }
    }
}
