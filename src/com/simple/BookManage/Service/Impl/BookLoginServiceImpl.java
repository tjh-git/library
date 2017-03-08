package com.simple.BookManage.Service.Impl;

import com.simple.BookManage.DAO.BookDao;
import com.simple.BookManage.DAO.BookLoginDao;
import com.simple.BookManage.RequestBeans.Book;
import com.simple.BookManage.RequestBeans.BookLogin;
import com.simple.BookManage.RequestBeans.Book_Catalog;
import com.simple.BookManage.RequestBeans.Login_Catalog;
import com.simple.BookManage.Service.BookLoginService;
import com.simple.bsp.common.util.DataGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/4.
 */
@Service("BookLoginService")
public class BookLoginServiceImpl implements BookLoginService{

    @Autowired
    BookDao bookDao;

    @Autowired
    BookLoginDao bookLoginDao;

    @Override
    public String BookLoginSave(Book book, BookLogin bookLogin,int schoolId) {
        bookLoginDao.SaveBookLogin(bookLogin);
        int index=bookLoginDao.getLastOrder();
        int booknum=bookLogin.getBuy_num();

        for(int i=0;i<booknum;i++){
            book.setCatalog_id(bookLogin.getCatalog_id());
            book.setOrder_id(index);
            book.setBook_pos_code(bookLogin.getStart_pos());
            book.setSchool_id(schoolId);
            bookDao.SaveBook(book);
        }
        return "1";
    }
    public Map<String, Object> getBookLoginPage(DataGridModel dgm, Login_Catalog bookLogin){
        return bookLoginDao.getBookLoginPage(dgm,bookLogin);
    }

    @Override
    public Map<String, Object> getBookList(DataGridModel dgm, Book_Catalog book_catalog) {
        return bookDao.getBookList(dgm,book_catalog);
    }

    @Override
    public Map<String,Object> getBookByName(String bookName){
        return bookDao.getBookbyName(bookName);
    }
}
