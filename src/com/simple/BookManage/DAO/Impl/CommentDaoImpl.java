package com.simple.BookManage.DAO.Impl;

import com.simple.BookManage.DAO.CommentDao;
import com.simple.BookManage.RequestBeans.Book_Catalog;
import com.simple.BookManage.RequestBeans.Comment;
import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovesyxfuffy on 2016/6/11.
 */
@Repository("CommentDao")
public class CommentDaoImpl implements CommentDao {
    @Autowired
    DBUtil util;

    @Override
    public int addComment(Comment comment) {
        String sql = "insert into comment (user_id, user_name, write_date, comment, level, cover_path,comment_title,comment_count) "
                + "values (:user_id, :user_name, :write_date, :comment, :level, :cover_path,:comment_title,:comment_count)";
        return util.editObject(sql, comment);
    }

    @Override
    public Object getComments(int page,int about) {
        String sql="select * from comment where 1=1 limit "+(page-1)*10+",10";
        String count="select count(*) from comment where 1=1";
        Map<String, Object> result = new HashMap<String, Object>(2);
        int total=util.getObjCount(count);
        int pagenums=total%10==0?total/10:total/10+1;
        result.put("pagenum",pagenums);
        result.put("rows",util.getObjList(sql, new HashedMap(), Comment.class));
        result.put("page",page);
        return result;
    }

    @Override
    public Object getoneComment(int id) {
        String sql="select * from comment where comment_id="+id;
        return util.getObjList(sql, new HashedMap(), Comment.class);
    }
}
