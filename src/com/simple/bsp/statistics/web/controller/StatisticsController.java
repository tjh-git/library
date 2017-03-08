package com.simple.bsp.statistics.web.controller;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.statistics.form.LibraryCatalog;
import com.simple.bsp.statistics.form.StatisticsForm;
import com.simple.bsp.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/11.
 */
@Controller
public class StatisticsController
{
    @Autowired
    private StatisticsService statisticsService;

    /**
     * 流通率统计
     * @param dgm
     * @param statisticsForm
     * @return
     */
    @RequestMapping(value = "/statistics/circulate/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> list(DataGridModel dgm, StatisticsForm statisticsForm, HttpSession session)
    {
        Object temp=session.getAttribute("school_id");
        String school_id=null;
        if(temp!=null)school_id=temp+"";
        return statisticsService.list(dgm,statisticsForm,school_id);
    }

    /**
     *图书借阅率统计
     * @param dgm
     * @param statisticsForm
     * @return
     */
    @RequestMapping(value = "/statistics/circulate/getType",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> typeList(DataGridModel dgm, StatisticsForm statisticsForm, HttpSession session)
    {
        Object temp=session.getAttribute("school_id");
        String school_id=null;
        if(temp!=null)school_id=temp.toString();
        return statisticsService.typeList(dgm,statisticsForm,school_id);
    }

    /**
     * 馆藏统计
     * @param dgm
     * @param
     * @param session
     * @return
     */
    @RequestMapping(value = "/statistics/circulate/catalogList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> catalogList(DataGridModel dgm, LibraryCatalog libraryCatalog, HttpSession session)
    {
        Object temp=session.getAttribute("school_id");
        String school_id=null;
        if(temp!=null)school_id=temp.toString();
        return statisticsService.catalogList(dgm,libraryCatalog,school_id);
    }
    /**
     * 获取所有类型
     */
    @RequestMapping(value = "/statistics/circulate/getAllType",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getAllType(String show)
    {
        System.out.println(show);
        return statisticsService.getAllType(show);
    }
}
