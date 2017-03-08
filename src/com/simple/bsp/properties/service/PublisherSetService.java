package com.simple.bsp.properties.service;

import com.simple.bsp.properties.dao.PublisherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 17854 on 2016/7/13.
 */
@Service("publisherSetService")
public class PublisherSetService
{
    @Autowired
    PublisherDao publisherDao;
    public List<String> getProperties(String book_publisher, String school_id)
    {
        return publisherDao.getProperties(book_publisher,school_id);
    }
    public String add(String book_publisher, String substring, String school_id)
    {
        return publisherDao.add(book_publisher,substring,school_id);
    }
}
