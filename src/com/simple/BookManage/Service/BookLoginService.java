package com.simple.BookManage.Service;

import com.simple.BookManage.RequestBeans.Book;
import com.simple.BookManage.RequestBeans.BookLogin;
import com.simple.BookManage.RequestBeans.Book_Catalog;
import com.simple.BookManage.RequestBeans.Login_Catalog;
import com.simple.bsp.common.util.DataGridModel;

import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/4.
 */
public interface BookLoginService {
    String BookLoginSave(Book book, BookLogin bookLogin,int schoolId);
    Map<String, Object> getBookLoginPage(DataGridModel dgm, Login_Catalog bookLogin);
    Map<String,Object> getBookList(DataGridModel dgm, Book_Catalog book_catalog);
    Map<String,Object> getBookByName(String bookName);
}
