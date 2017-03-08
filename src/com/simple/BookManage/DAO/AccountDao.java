package com.simple.BookManage.DAO;

import java.util.List;

/**
 * Created by lovesyxfuffy on 2016/7/2.
 */
public interface AccountDao {
    public boolean login(String username,String password);
    public Object getUser(String username, String password);
}
