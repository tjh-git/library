package com.simple.BookManage.Service;

import com.simple.BookManage.RequestBeans.Comment;

/**
 * Created by lovesyxfuffy on 2016/6/11.
 */
public interface CommentService {
    public int addComment(Comment comment);
    public Object getComments(int page,int about);
    public Object getOneComment(int id);
}
