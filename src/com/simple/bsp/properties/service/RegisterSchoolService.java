package com.simple.bsp.properties.service;

import com.simple.bsp.properties.dao.RegisterSchoolDao;
import com.simple.bsp.properties.form.SchoolAddForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 17854 on 2016/7/2.
 */
@Service("registerSchoolService")
public class RegisterSchoolService
{
    @Autowired
    private RegisterSchoolDao registerSchoolDao;
    public int add(SchoolAddForm schoolAddForm)
    {
        return registerSchoolDao.add(schoolAddForm);
    }
}
