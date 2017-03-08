package com.simple.BookManage.DAO;

import com.simple.BookManage.RequestBeans.Book;
import com.simple.BookManage.RequestBeans.Book_Catalog;
import com.simple.bsp.common.util.DataGridModel;

import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/6.
 */
public interface BookDao {
    public int SaveBook(Book b);
    public Map<String,Object> getBookList(DataGridModel dgm, Book_Catalog book);
    public Map<String,Object> getBookbyName(String bookname);
}
