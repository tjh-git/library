package com.simple.bsp.borrow.web.controllor;

import com.simple.bsp.borrow.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/4.
 */
@Controller
public class BorrowController
{
    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "/borrow/books",method = RequestMethod.GET)
    public String list(Model model)
    {
        return "/bsp/borrow/books/book";
    }
    @RequestMapping(value = "/borrow/books/bookShow",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showBook(String code, String rows, String page, HttpSession httpSession)
    {
        Map<String, Object> borrow=null;
        try {
            borrow = new HashMap<>();
            borrow =  borrowService.getOneAll(code,rows,page,httpSession);
         //   if(borrow.get("isOut").toString().equals(Borrow.NOT_RETUEN+""))borrow.put("isOut","未归还");else
          //  if(borrow.get("isOut").toString().equals(Borrow.OVERDUE+""))
          //  borrow.put("isOut","已逾期！");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return borrow;
    }
}
