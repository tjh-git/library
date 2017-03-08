package com.simple.BookManage.Service.Impl;

import com.simple.BookManage.RequestBeans.BookInfo;
import com.simple.BookManage.DAO.BookInfoDao;
import com.simple.BookManage.Service.BookInfoService;
import com.simple.bsp.common.util.DataGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/2.
 */
@Service("BookService")
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private BookInfoDao dao;


    @Override
    public Map<String, Object> getBookInfoPage(DataGridModel dgm, BookInfo bookInfo) {
        return dao.getBookInfoPage(dgm, bookInfo);
    }

    @Override
    public boolean saveBookInfo(BookInfo bookInfo) {
        return dao.saveBookInfo(bookInfo);
    }

    @Override
    public int[] delBookInfo(List<String> bookcodelist) {
        return dao.delBookInfo(bookcodelist);
    }

    @Override
    public Map<String, ?> getOne(String code) {
        return dao.getOne(code);
    }

    @Override
    public boolean updateBookInfo(BookInfo bookInfo)
    {
        return dao.update(bookInfo);
    }
}
