package com.simple.bsp.borrow.web.controllor;
import com.simple.bsp.borrow.po.Borrow;
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
 * Created by 17854 on 2016/6/5.
 */
@Controller
public class ReturnBookController
{
    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "/borrow/return",method = RequestMethod.GET)
    public String list(Model model){return "/bsp/borrow/return/return";}
    @RequestMapping(value = "/borrow/return/returnBook",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> showBook(String code, HttpSession session)
    {
        Map<String, Object> borrow = new HashMap<String, Object>();
        try {
            borrow = borrowService.search(code,session);
            if(borrow.get("isOut").toString().equals(Borrow.NOT_RETUEN+""))borrow.put("isOut","未归还");else
            if(borrow.get("isOut").toString().equals(Borrow.OVERDUE+""))
                borrow.put("isOut","已逾期！");
            if(borrow.get("isOut").toString().equals(Borrow.BOOK_IN+"")||borrow.get("isOut").toString().equals(Borrow.NOT_FIND_BOOK+""))
            {
                borrow.put("bookCode",code);
                borrow.put("fine",0);
            }
            return borrow;
        }catch(Exception e)
        {
            e.printStackTrace();
            return borrow;
        }
    }
    @RequestMapping(value = "/borrow/return/returnOneBook",method = RequestMethod.POST)
    @ResponseBody
    public String returnOneBook(String code,HttpSession session)
    {
        try
        {
            borrowService.returnOneBook(code,session);
            return "1";
        }catch (Exception e)
        {
            return "-1";
        }
    }
}