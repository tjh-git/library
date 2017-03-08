package com.simple.bsp.properties.web.controller;

import com.simple.bsp.properties.service.PublisherSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by 17854 on 2016/7/2.
 */
@Controller
public class PublisherController
{
    @Autowired
    private PublisherSetService publisherSetService;
    @RequestMapping(value = "/properties/publisher/getall",method = RequestMethod.POST)
    @ResponseBody
    public List<String> getAll(HttpSession session)
    {
        return publisherSetService.getProperties("book_publisher",session.getAttribute("school_id")+"");
    }
    @RequestMapping(value = "/properties/publisher/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(String list,HttpSession session)
    {
        return publisherSetService.add("book_publisher",list.substring(1,list.length()),session.getAttribute("school_id")+"");
    }
}
