package com.simple.BookManage.Service.Impl;

import com.simple.BookManage.DAO.CommentDao;
import com.simple.BookManage.RequestBeans.Comment;
import com.simple.BookManage.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lovesyxfuffy on 2016/6/11.
 */
@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao dao;

    @Override
    public int addComment(Comment comment) {

       // comment.setComment(comment.getComment().replace("\r\n","<br/>").replace("\r","<br/>").replace("\n","<br/>"));
        return dao.addComment(comment);
    }

    @Override
    public Object getComments(int page,int about) {
        return dao.getComments(page,about);
    }

    @Override
    public Object getOneComment(int id) {
        return dao.getoneComment(id);
    }
}
