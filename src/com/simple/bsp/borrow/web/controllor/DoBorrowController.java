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
import java.util.Map;

/**
 * Created by 17854 on 2016/6/6.
 */
@Controller
public class DoBorrowController
{
    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "/borrow/borrow",method = RequestMethod.GET)
    public String show(Model model)
    {
        return "/bsp/borrow/borrow/borrow";
    }
    @RequestMapping(value = "/borrow/borrow/borrowBook",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> show (String readCard,HttpSession session)
    {

        Map<String, String> status = null;
        status = borrowService.borrowBook(readCard,session);
        return status;
    }
    @RequestMapping(value = "/borrow/borrow/borrowOneBook",method = RequestMethod.POST)
    @ResponseBody
    public String borrowOneBook(String readCard, String bookCode, HttpSession session)
    {
        try
        {
            int status=borrowService.borrowOneBook(readCard,bookCode,session);
            if(status!= Borrow.CAN_BORROW)return "借书失败";
            return "借书成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "借书失败";
        }
    }
}
