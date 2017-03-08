package com.simple.bsp.borrow.web.controllor;

import com.simple.bsp.borrow.service.BorrowService;
import com.simple.bsp.common.util.DataGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/6.
 */
@Controller
public class UrgeController
{
    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "/borrow/urge",method = RequestMethod.GET)
    public String show(Model model)
    {
        return "/bsp/borrow/urge/urge";
    }
    @RequestMapping(value = "/borrow/urge/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showBooks(DataGridModel dgm, String model,HttpSession session)
    {
            return borrowService.getUrgeList(dgm,model,session);
    }
    @RequestMapping(value = "/borrow/urge/printit",method = RequestMethod.GET)
    public String printit(HttpServletRequest request, String id,HttpSession session)
    {
        Map<String,?> result=borrowService.print(id,session);
        System.out.println(result);
        request.setAttribute("result",result);
        return "/bsp/borrow_new/print/paint";
    }
}
