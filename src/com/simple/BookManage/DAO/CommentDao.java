package com.simple.BookManage.DAO;

import com.simple.BookManage.RequestBeans.Comment;

import java.util.List;

/**
 * Created by lovesyxfuffy on 2016/6/11.
 */
public interface CommentDao {
    public int addComment(Comment comment);
    public Object getComments(int page,int about);
    public Object getoneComment(int id);
}
