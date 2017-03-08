package com.simple.BookManage.Service;

import com.simple.BookManage.RequestBeans.BookInfo;
import com.simple.bsp.common.util.DataGridModel;

import java.util.List;
import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/2.
 */
public interface BookInfoService {
    public Map<String, Object> getBookInfoPage(DataGridModel dgm,
                                                 BookInfo bookInfo);
    public boolean saveBookInfo(BookInfo bookInfo);
    public int[] delBookInfo(List<String> bookcodelist);
    Map<String,?> getOne(String code);
    boolean updateBookInfo(BookInfo bookInfo);
}
