package com.simple.BookManage.DAO;

import com.simple.BookManage.RequestBeans.BookInfo;
import com.simple.BookManage.RequestBeans.BookLogin;
import com.simple.BookManage.RequestBeans.Login_Catalog;
import com.simple.bsp.common.util.DataGridModel;

import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/6.
 */
public interface BookLoginDao {
    int SaveBookLogin(BookLogin bookLogin);

    int getLastOrder();

    Map<String, Object> getBookLoginPage(DataGridModel dgm, Login_Catalog bookLogin);


}
