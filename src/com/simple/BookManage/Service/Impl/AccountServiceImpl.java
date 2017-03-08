package com.simple.BookManage.Service.Impl;

import com.simple.BookManage.DAO.AccountDao;
import com.simple.BookManage.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lovesyxfuffy on 2016/7/2.
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao dao;

    @Override
    public Object login(String username, String password) {

        if(dao.login(username,password)){
            System.out.println("have");
            return dao.getUser(username,password);
        }
        else{
            System.out.println("not have");
            return null;
        }
    }
}
