package com.simple.bsp.properties.web.controller;

import com.simple.bsp.properties.service.PublisherSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 17854 on 2016/7/13.
 */
@Component("getPub")
public class GetPub
{
    @Autowired
    private PublisherSetService publisherSetService;
    public List<String> getAll(HttpSession session)
    {
        return publisherSetService.getProperties("book_publisher",session.getAttribute("school_id")+"");
    }
}
