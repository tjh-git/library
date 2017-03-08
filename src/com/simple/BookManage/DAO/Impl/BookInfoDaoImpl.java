package com.simple.BookManage.DAO.Impl;

import com.simple.BookManage.RequestBeans.BookInfo;
import com.simple.BookManage.DAO.BookInfoDao;
import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by lovesyxfuffy on 2016/6/2.
 */
@Repository("BookInfoDao")
public class BookInfoDaoImpl implements BookInfoDao {
    public static final String html=
            "<div style=\"margin-left:8px;\"><div title=\"编辑所选记录\" style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_9787301271957\" onclick=\"edit()\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\">" + "<span class=\"ui-icon ui-icon-pencil\"></span></div><div title=\"删除所选记录\" style=\"float:left;\" class=\"ui-pg-div ui-inline-del\" id=\"jDeleteButton_9787301271957\" onclick=\"jQuery.fn.fmatter.rowactions.call(this,'del');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\"><span class=\"ui-icon ui-icon-trash\"></span></div></div>";
   //         "<div>ss</div><div>ss</div>";
    @Autowired
    DBUtil util;
    @Override
    public Map<String, Object> getBookInfoPage(DataGridModel dgm, BookInfo bookInfo) {
        Map<String, Object> result = new HashMap<String, Object>(2);



        // 获取记录数
        StringBuffer sumSql = new StringBuffer();
        sumSql.append("select count(1) from book_catalog bc where 1=1 ");

        // 获取结果集
        String quSql = "select bc.* "
                + " from book_catalog bc "
                + " where 1=1 ";

        // 组装查询条件
        StringBuffer sqlSb = new StringBuffer();
        Map<String, Object> params = new HashMap<>();

        // 点击查询按钮时组装查询语句
        if (bookInfo.getCatalog_id() != 0) {
            sumSql.append(" and bc.catalog_id=").append(bookInfo.getCatalog_id());
            quSql += " and bc.catalog_id=" + bookInfo.getCatalog_id();
        }
        if (bookInfo.getBookcode() != null&&bookInfo.getBookcode().length()!=0) {
            sumSql.append(" and bc.bookcode like '%").append(bookInfo.getBookcode()+"%'");
            quSql += " and bc.bookcode like '%" + bookInfo.getBookcode()+"%'";
        }
        if (bookInfo.getPrinttimes() != 0) {
            sumSql.append(" and bc.printtimes=").append(bookInfo.getPrinttimes());
            quSql += " and bc.printtimes=" + bookInfo.getPrinttimes();
        }
        if (!("".equals(bookInfo.getBookname()))&&!(bookInfo.getBookname()==null)){
            sumSql.append(" and bc.bookname like '%").append(bookInfo.getBookname()).append("%' ");
            quSql += " and bc.bookname like '%" + bookInfo.getBookname() + "%' ";
        }
        if (!("".equals(bookInfo.getBookab()))&&!(bookInfo.getBookab()==null)){
            sumSql.append(" and bc.bookab= '").append(bookInfo.getBookab()).append("' ");
            quSql += " and bc.bookab= '" + bookInfo.getBookab() + "' ";
        }
        if (!("".equals(bookInfo.getPublishcom()))&&!(bookInfo.getPublishcom()==null)){
            sumSql.append(" and bc.publishcom= '").append(bookInfo.getPublishcom()).append("' ");
            quSql += " and  bc.publishcom= '" + bookInfo.getPublishcom() + "' ";
        }

        // 组织机构过滤

        // 组装排序规则
        String orderString = " order by bc.bookcode desc ";

        // 组装分页定义
        String sql = quSql + sqlSb.toString() + orderString;
        String pageQuerySql=sql;
        if(dgm.getPage()!=0&&dgm.getRows()!=0){
           pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
                    dgm.getRows());
        }

        // 绑定查询结果('total'和'rows'名称不能修改)
        result.put("records", util.getObjCount(sumSql.toString()));
        result.put("page",dgm.getPage());
        result.put("total",util.getObjCount(sumSql.toString())%10==0?util.getObjCount(sumSql.toString())/10:util.getObjCount(sumSql.toString())/10+1);
        System.out.println(sumSql);
        System.out.println(sql);
        System.out.println(sqlSb+"  ");
        List<Map<String,Object>> rows=util.getMapList(pageQuerySql,(Object[])null);
        System.out.println(rows+"  *****************");
        System.out.println("*****************************************");
        for(int i=0;i<rows.size();i++)
        {
            rows.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
            rows.get(i).put("edit",html);
        }
        result.put("rows", rows);
        result.put("id","bookcode");
        return result;
    }

    @Override
    public boolean saveBookInfo(BookInfo bookInfo) {
        String sql = "insert into book_catalog (bookname,seriesab,second_writer,other_putup, together_putup,bookcode, bookab, have_vol, seriesname, writer, translator,  publishcom, booklanguage, booktype,"+
                " publishdate, editiontimes,printtimes,booksize,bookbind,bookpagenum,bookprice,is_refbook,is_journal,operator_id,operator_name,getdate) "
                + "values (:bookname,:seriesab,:second_writer,:other_putup, :together_putup, :bookcode, :bookab, :have_vol, :seriesname, :writer, :translator, :publishcom, :booklanguage, :booktype," +
                ":publishdate, :editiontimes,:printtimes,:booksize,:bookbind,:bookpagenum,:bookprice,:is_refbook,:is_journal,:operator_id,:operator_name,:getdate)";
        return util.editObject(sql, bookInfo)>0;
    }

    @Override
    public int[] delBookInfo(List<String> bookcodelist) {
        String sql = "delete from book_catalog where bookcode = ?";
        return util.batchDelete(sql, bookcodelist);
    }

    @Override
    public Map<String, ?> getOne(String code)
    {
        String sql="select bc.*  from book_catalog bc where bc.bookcode='"+code+"'";
        return util.getMap(sql,(Object[])null);
    }

    @Override
    public boolean update(BookInfo bookInfo)
    {
        String sql="update book_catalog set bookname=:bookname,seriesab=:seriesab,second_writer=:second_writer,other_putup=:other_putup, together_putup=:together_putup,bookcode=:bookcode, bookab=:bookab, have_vol=:have_vol, seriesname=:seriesname, writer=:writer, translator=:translator,  publishcom=:publishcom, booklanguage=:booklanguage, booktype=:booktype,"+
                " publishdate=:publishdate, editiontimes=:editiontimes,printtimes=:printtimes,booksize=:booksize,bookbind=:bookbind,bookpagenum=:bookpagenum,bookprice=:bookprice,is_refbook=:is_refbook,is_journal=:is_journal,operator_id=:operator_id,operator_name=:operator_name,getdate=:getdate where catalog_id=:catalog_id";
               try {
                   System.out.println(bookInfo.getPublishcom()+ "  " +bookInfo.getCatalog_id());
                   util.editObject(sql,bookInfo);
               }catch (Exception e)
               {
                   e.printStackTrace();
                   return false;
               }
               return true;
    }
}
