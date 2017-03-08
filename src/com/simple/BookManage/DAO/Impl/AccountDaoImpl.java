package com.simple.BookManage.DAO.Impl;

import com.simple.BookManage.DAO.AccountDao;
import com.simple.BookManage.RequestBeans.Book;
import com.simple.BookManage.RequestBeans.Operator;
import com.simple.bsp.common.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lovesyxfuffy on 2016/7/2.
 */
@Repository("AccountDao")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    DBUtil dbUtil;

    @Override
    public boolean login(String username, String password) {
        int c=dbUtil.getObjCount("select count(user_id) from operator where username='"+username+"' and password='"+password+"'");
        System.out.println("select count(1) from operator where username='"+username+"' and password='"+password+"'");
        return c==1;
    }

    @Override
    public Object getUser(String username, String password) {

        return dbUtil.getObject("select * from operator where username='"+username+"' and password='"+password+"'",new HashMap<String, Object>(),Operator.class);
    }
}
