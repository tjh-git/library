package com.simple.BookManage.Controller;

import com.simple.BookManage.RequestBeans.Book;
import com.simple.BookManage.RequestBeans.BookLogin;
import com.simple.BookManage.RequestBeans.Book_Catalog;
import com.simple.BookManage.RequestBeans.Login_Catalog;
import com.simple.BookManage.Service.BookLoginService;
import com.simple.BookManage.Service.BookInfoService;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/5.
 */
@Controller
public class BookLoginController {
    @Autowired
    BookLoginService bookLoginService;
    @Autowired
    BookInfoService bookservice;

    @RequestMapping(value = "/booklogin/info",method = RequestMethod.GET)
    public String BookLogin(){
        return "BookManager_new/BookLogin/blank";
    }

    @RequestMapping(value = "/booklogin/cataloglist",method = RequestMethod.GET)
    public String Booktest(){
        return "BookManager_new/BookCatalogue/blank";
    }

    @RequestMapping(value = "/booklogin/add",method = RequestMethod.GET)
    public String BookInfoAdd(){
        return "BookManager_new/BookCatalogue_add/blank";
    }

    @RequestMapping(value = "/booklogin/bookloginlist",method=RequestMethod.GET)
    public String BookList(){
        return "BookManager_new/BookLoginList/blank";
    }

    @RequestMapping(value = "/booklogin/booklist",method = RequestMethod.GET)
    public String BookLoginPopWin(){
        return "BookManager_new/BookList/blank";
    }

    @RequestMapping(value = "/booklogin/getLoginList")
    @ResponseBody
    public Map<String,Object> getLoginList(Login_Catalog bookLogin, DataGridModel dgm){
        System.out.println(bookLogin.getBookname());
        Map m=bookLoginService.getBookLoginPage(dgm,bookLogin);
        return m;
    }
    @RequestMapping(value = "/booklogin/addBookLoginInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addBookLogin(Book book, BookLogin bookLogin, HttpSession session){
        String userId = String.valueOf(session.getAttribute("user_id"));
        String userName=(String)session.getAttribute("username");
        int schoolId=(Integer) session.getAttribute("school_id");
        bookLogin.setOperator_name(userName);
        bookLogin.setOperator_id(userId);
        bookLogin.setBuy_amount_price(bookLogin.getBuy_num()*bookLogin.getBook_buy_price());
        bookLogin.setBuy_date(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd HH:mm:ss"));


        Map<String, String> map = new HashMap<String, String>();
        try {
            bookLoginService.BookLoginSave(book,bookLogin,schoolId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "订单信息录入失败");
            return map;
        }
        map.put("mes", "订单信息录入成功");
        return map;
    }
    @RequestMapping(value = "bookLogin/getBookList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getBookList(DataGridModel dgm, Book_Catalog book_catalog){
        System.out.println(book_catalog.getWriter()+"   "+book_catalog.getBookcode()+"   "+book_catalog.getBookname());
        return bookLoginService.getBookList(dgm,book_catalog);
    }
}
