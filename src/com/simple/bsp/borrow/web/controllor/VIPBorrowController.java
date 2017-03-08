package com.simple.bsp.borrow.web.controllor;

import com.simple.bsp.borrow.form.BorrowForm;
import com.simple.bsp.borrow.service.BorrowService;
import com.simple.bsp.common.util.DataGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/5.
 */
@Controller
public class VIPBorrowController
{
    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "/borrow/vip",method = RequestMethod.GET)
    public String list(Model model)
    {
        return "/bsp/borrow/vips/vip";
    }
    @RequestMapping(value = "/borrow/vip/bookShow",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showBooks(DataGridModel dgm, BorrowForm borrowForm,HttpSession session)
    {
        Map<String,Object> map=borrowService.getList(null,dgm,borrowForm,session);
        return map;
    }
    @RequestMapping(value = "/borrow/vip/bookShow",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> showBooks(String row_id,DataGridModel dgm, BorrowForm borrowForm,HttpSession session)
    {
        Map<String,Object> map=borrowService.getList(row_id,dgm,borrowForm,session);
        return map;
    }
}
