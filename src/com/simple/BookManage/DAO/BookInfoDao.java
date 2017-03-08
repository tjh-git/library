package com.simple.BookManage.DAO;

import com.simple.BookManage.RequestBeans.BookInfo;
import com.simple.bsp.common.util.DataGridModel;

import java.util.List;
import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/2.
 */
public interface BookInfoDao {
    Map<String, Object> getBookInfoPage(DataGridModel dgm, BookInfo bookInfo);

    boolean saveBookInfo(BookInfo bookInfo);

    int[] delBookInfo(List<String> bookcodelist);

    Map<String,?> getOne(String code);

    boolean update(BookInfo bookInfo);
}
