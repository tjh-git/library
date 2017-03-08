package com.simple.bsp.borrow.service;

import com.simple.bsp.borrow.dao.BorrowDao;
import com.simple.bsp.borrow.form.BorrowForm;
import com.simple.bsp.common.util.DataGridModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/4.
 */
@Service("borrowService")
public class BorrowService
{
    @Autowired
    private BorrowDao borrowDao;
    public Map<String,Object> getOneAll(String code, String rows, String page, HttpSession session)
    {
        return borrowDao.getOneAll(code,Integer.parseInt(rows),Integer.parseInt(page),session);
    }
    public Map<String,Object> search(String code,HttpSession session)
    {
        return borrowDao.search(code,session);
    }

    public Map<String,Object> getList(String row_id,DataGridModel dgm, BorrowForm borrowForm,HttpSession session)
    {
        return borrowDao.getList(row_id,dgm,borrowForm,session);
    }

    public void returnOneBook(String code,HttpSession session)throws Exception
    {
        borrowDao.returnOneBook(code,session);
    }
    public Map<String,String> borrowBook(String readCard,HttpSession session)
    {
        return borrowDao.borrowBook(readCard,session);
    }

    public int borrowOneBook(String readCard, String bookCode,HttpSession session)
    {
        return borrowDao.borrowOneBook(readCard,bookCode,session);
    }

    public Map<String,Object> getUrgeList(DataGridModel dgm, String model,HttpSession session)
    {
        return borrowDao.getUrgeList(dgm,model,session);
    }

    public Map<String,?> print(String id,HttpSession session)
    {
        return borrowDao.print(id,session);
    }
}
