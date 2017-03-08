package com.simple.bsp.borrow.dao;

import com.simple.bsp.borrow.form.BorrowForm;
import com.simple.bsp.borrow.po.Borrow;
import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.properties.web.controller.GetProper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 17854 on 2016/6/4.
 */
@Repository("borrowDao")
public class BorrowDao
{
    @Autowired
    private DBUtil util;

    /**
     * 查找该书的借阅历史和当前借阅
     */
    public Map<String,Object> getOneAll(String code,int rows,int page,HttpSession session)
    {
        Map<String,Object> ret=new HashMap<>();
        updata(session);
        String sql="select borrow.book_id,borrow.user as borrowerCode,borrow.borrowTime,borrow.returnTime,borrow.b_status as isOut,borrow.fine,vip.name as borrowerName,book_catalog.bookname,vip.money from borrow,vip,book_catalog,book,book_login where  borrow.user=vip.readCard and  book.book_id=borrow.book_id and  book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id and borrow.book_id="+code ;
        List<Map<String, Object>> result= new ArrayList<>();
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, page,
                rows);
        result=util.getMapList(pageQuerySql,(Object[])null);
        sql="select count(1) from borrow where borrow.book_id='"+code+"'";
        int count=util.getObjCount(sql);
        for(int i=0;i<result.size();i++)
        {
            Map<String, Object> one=result.get(i);
            one.put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
            if(one.get("isOut").toString().equals(Borrow.NOT_RETUEN+""))one.put("isOut","未归还");else
            if(one.get("isOut").toString().equals(Borrow.OVERDUE+""))one.put("isOut","已逾期！");else
            if(one.get("isOut").toString().equals(Borrow.HAS_RETUEN+""))one.put("isOut","已归还");
            one.put("count",count);
        }
        ret.put("total",(count/rows)+1+"");
        ret.put("rows",result);
        ret.put("record",count);
        ret.put("page",page);
        return ret;

    }
    /**
     * 根据图书编号查询图书情况
     * @param code
     * @return
     */
    public Map<String,Object> search(String code,HttpSession session)//根据图书编号查询图书情况
    {
        updata(session);
        String sql = "select borrow_status as isOut,borrow_count as borrowNum from book where book_id=" + code;//查询书籍是否被借阅；
        Map<String, Object> borrow = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        List<Map<String, Object>> status=null;
        try
        {
            status = util.getMapList(sql, params);
            if (status.size() == 0) {         //未找到该图书信息
                borrow.put("isOut", Borrow.NOT_FIND_BOOK+"");
                return borrow;
            }
            if (status.get(0).get("isOut").toString().equals(Borrow.BOOK_IN+"")) {
                borrow.put("isOut", Borrow.BOOK_IN+"");
                return borrow;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return borrow;
        }
        borrow.put("bookCode",code);//书籍的唯一标识
        borrow.putAll(status.get(0));
        sql = "select user as borrowerCode,borrowTime,returnTime,b_status as isOut,fine from borrow where book_id=" + code;//查询是谁借的书并且查询当前的借阅情况（逾期与否）
        status = util.getMapList(sql, params);
        if(status.size()>0)
        {
            borrow.putAll(status.get(status.size() - 1));
            String readCard = status.get(status.size() - 1).get("borrowerCode").toString();
            sql = "select name as borrowerName,money from vip where readCard=" + readCard;//查询借书用户的名字
            status = util.getMapList(sql, params);
            borrow.putAll(status.get(0));
        }
        sql="select bookname as bookName from book_catalog,book,book_login where book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id and book.book_id="+code;
        status = util.getMapList(sql, params);
        System.out.println(status);
        borrow.putAll(status.get(0));
        return borrow;
    }

    /**
     * 根据会员编号查询书籍
     * @param dgm
     * @param borrowForm
     * @return
     */
    public Map<String,Object> getList(String row_id,DataGridModel dgm, BorrowForm borrowForm,HttpSession session)//根据会员编号查询书籍
    {
        updata(session);
        // 查询结果Map
        Map<String, Object> result = new HashMap<String, Object>(2);
        // 获取记录数
        StringBuffer sumSql = new StringBuffer();
        String condition=borrowForm.getEnable()!=0?" where '1'='1' and borrow.b_status="+borrowForm.getEnable():"  where '1'='1'";//组装查询条件
        condition+=(borrowForm.getCode().length()==0?"":" and borrow.user like '%"+borrowForm.getCode()+"%'");
        sumSql.append("select count(1) from borrow "+condition);
      /*  sumSql.append(borrowForm.getEnable()==0?"select count(1) from borrow where borrow.user="+borrowForm.getCode():"select count(1) from borrow,book where borrow.user="+borrowForm.getCode()+" and book.book_id=borrow.book_id and borrow.b_status="+borrowForm.getEnable());//查询条数*/
        // 获取结果集
        String quSql = "select borrow.id,duration as needReturn,user as borrowerCode,borrow.book_id as bookCode,borrowTime,returnTime,b_status as status,fine,bookname as bookName,name,cardType,money from borrow,book,book_login,book_catalog,vip "+condition+" and borrow.book_id=book.book_id"+" and borrow.user=vip.readCard and book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id ";
        if(row_id!=null)quSql+="and borrow.id="+row_id;
        // 组装查询条件
        StringBuffer sqlSb = new StringBuffer();
        Map<String, Object> params = new HashMap<String, Object>();
        // 组装排序规则
        String orderString = "";
        if (StringUtils.isNotBlank(dgm.getSort())) {
            orderString = " order by " + "borrowTime" +  " desc" ;
        }
        // 组装分页定义
        String sql = quSql + sqlSb.toString() + orderString;
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
                dgm.getRows());
        System.out.println(pageQuerySql);
        int count=util.getMapList(sql,(Object[])null).size();
        List<Map<String,Object>> data=util.getMapList(pageQuerySql, params);
        for(int i=0;i<data.size();i++) {
            data.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
            if (data.get(i).get("status").toString().equals("1"))
                data.get(i).put("status", "已还");
            else if (data.get(i).get("status").toString().equals("2"))
                data.get(i).put("status", "未还");
            else if (data.get(i).get("status").toString().equals("3"))
                data.get(i).put("status", "逾期");
        }
        // 绑定查询结果('total'和'rows'名称不能修改)
        result.put("total",(count/dgm.getRows())+1+"");
        result.put("rows",data);
        result.put("record",count);
        result.put("page",dgm.getPage());
        System.out.println(result);
        return result;
    }

    /**
     * 还书
     * @param code 欲还书籍
     * @throws Exception
     */
    public void returnOneBook(String code,HttpSession session)throws Exception//还书
    {
        updata(session);
        try
        {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql="select user,fine from borrow where book_id="+code+" and b_status<>"+ Borrow.HAS_RETUEN;
        Map<String,?> obj=util.getMap(sql,params);
        String user=obj.get("user").toString();
        String fine=obj.get("fine").toString();
        sql="select money from vip where readCard="+user;
        obj=util.getMap(sql,params);
        if(Double.parseDouble(obj.get("money").toString())<Double.parseDouble(fine))
        {
            throw new ClassNotFoundException();
        }
        sql="update borrow set b_status="+ Borrow.HAS_RETUEN+",returnTime='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"',fine=0 where book_id="+code+" and b_status<>"+ Borrow.HAS_RETUEN;
        util.editObject(sql,null);
        sql="update vip set money=money-"+fine+" where readCard='"+user+"'";
        util.editObject(sql,null);
        sql="update book set borrow_status="+ Borrow.BOOK_IN+" where book_id="+code;
        util.editObject(sql,null);
        sql="insert into oppration (time,action,user,book) value('"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','归还',"+user+","+code+")";
        util.editObject(sql,null);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 扫描是否需要更新罚款
     */
    public void updata(HttpSession session)
    {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        GetProper bean = (GetProper) context .getBean("getpro");
        System.out.println(bean.getProperties("t_overtime_fine",session).get(0)+"*******");
        List<String> teaL=bean.getProperties("t_isFine",session);
        String tea=teaL.size()==0?"on":teaL.get(0);
        List<String> stdL=bean.getProperties("s_isFine",session);
        String std=stdL.size()==0?"on":stdL.get(0);
        if(tea.equals("on"))
        {
            String sql = "update borrow,vip set fine=fine+DATEDIFF(curdate(),finedate)*" + Double.parseDouble(bean.getProperties("t_overtime_fine",session).get(0)) + ",finedate=curdate(),b_status=" + Borrow.OVERDUE + " where vip.readCard=borrow.user and vip.cardType='教师' and finedate<>curdate() and duration<curdate() and b_status<>" + Borrow.HAS_RETUEN;
            util.updateObject(sql);
        }
        if(std.equals("on")) {
            String sql = "update borrow,vip set fine=fine+DATEDIFF(curdate(),finedate)*" + Double.parseDouble(bean.getProperties("s_overtime_fine",session).get(0)) + ",finedate=curdate(),b_status=" + Borrow.OVERDUE + " where vip.readCard=borrow.user and vip.cardType='学生' and finedate<>curdate() and duration<curdate() and b_status<>" + Borrow.HAS_RETUEN;
            util.updateObject(sql);
        }
    }
    /**
     * 借书
     * @param readCard 借阅证号
     * @return
     */
    public Map<String,String> borrowBook(String readCard,HttpSession session)//借书
    {
        int a=12;
        int b=2;
        System.out.println(a>>b);
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        GetProper bean = (GetProper) context .getBean("getpro");
        updata(session);
        Map<String, String> result = new HashMap<String, String>();
        try {
            result.put("readCard", readCard);
            Map<String, Object> params = new HashMap<String, Object>();
            String sql = "select deadLine,name,cardType from vip where readCard=" + readCard;
            Map<String, ?> m = util.getMap(sql, params);
            System.out.println(m);
            if(m==null)
            {
                result.put("signal", Borrow.NO_USER+"");
                return result;
            }
            String deadLine = m.get("deadLine").toString();
            String userName = m.get("name").toString();
            int max_borrow;
            if(m.get("cardType").equals("教师"))max_borrow=Integer.parseInt(bean.getProperties("t_max_borrow",session).get(0));else
                max_borrow=Integer.parseInt(bean.getProperties("s_max_borrow",session).get(0));
            result.put("userName", userName);
            if (compare_date(deadLine, new SimpleDateFormat("yyyy-MM-dd").format(new Date())) == -1) {
                result.put("signal", Borrow.READ_CARD_OVER_DEADLINE + "");
                return result;//借阅证过期
            }
            sql = "select count(1) from borrow where user=" + readCard + " and b_status=" + Borrow.NOT_RETUEN;
            int count = util.getObjCount(sql);
            if (count >= max_borrow) {
                result.put("signal", Borrow.OVER_MAX_BORROW + "");
                return result;//超过最大的借书数量
            }
            sql = "select count(1) from borrow where user=" + readCard + " and b_status=" + Borrow.OVERDUE;
            count = util.getObjCount(sql);
            if (count > 0) {
                result.put("signal", Borrow.HAS_OVERDUE + "");
                return result;//存在逾期未还的书籍
            }
            result.put("signal", Borrow.CAN_BORROW + "");
            return result;//可以借书
        }catch (Exception e)
        {
            e.printStackTrace();
            result.put("signal", Borrow.NO_USER+"");
            return result;
        }
    }

    /**
     * 比较两个日期
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取截止日期
     * @return
     */
    public String getDeadLine(String readCard,HttpSession session)
    {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        GetProper bean = (GetProper) context .getBean("getpro");
        long timestamp = System.currentTimeMillis();
        long max_length=Long.parseLong(readCard.equals("教师")?bean.getProperties("t_one_length",session).get(0):bean.getProperties("s_one_length",session).get(0));
        timestamp+= (max_length*24l*3600l*1000l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String __date = sdf.format(new Date(timestamp));
        System.out.println(__date);
        return __date;

    }

    /**
     * 借阅一本书籍
     * @param readCard
     * @param bookCode
     * @return
     */
    public int borrowOneBook(String readCard, String bookCode,HttpSession session)
    {
        String status=borrowBook(readCard,session).get("signal");
        if(!status.equals(Borrow.CAN_BORROW+""))return Integer.parseInt(status);
        String s=search(bookCode,session).get("isOut").toString();
        if(!s.equals("0"))return Borrow.BOOK_OUT;
        String sql="select cardType from vip where readCard="+readCard;
        Map<String,?> result=new HashMap<String, String>();
        Map<String, Object> params = new HashMap<String, Object>();
        result=util.getMap(sql,params);
        String cardType=result.get("cardType").toString();
        sql="select teacher_touch,student_touch from book where book_id="+bookCode;
        result=util.getMap(sql,params);
        String teacher_touch=result.get("teacher_touch").toString();
        String student_touch=result.get("student_touch").toString();
        if(cardType.equals("教师")&&teacher_touch.equals("0"))return Borrow.CANNOT_BORROW;
        if(cardType.equals("学生")&&student_touch.equals("0"))return Borrow.CANNOT_BORROW;
        sql="update book set borrow_status="+ Borrow.BOOK_OUT+",borrow_count=borrow_count+1 where book_id="+bookCode;
        util.updateObject(sql);
        String data=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String deadLine=getDeadLine(cardType,session);
        sql="insert into borrow(user, book_id, borrowTime, b_status,duration,finedate) values("+readCard+","+bookCode+",'"+data+"',"+ Borrow.NOT_RETUEN+","+"'"+deadLine+"'"+","+"'"+deadLine+"'"+")";
        util.editObject(sql,null);
        sql="insert into oppration (time,action,user,book) value('"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','借阅',"+readCard+","+bookCode+")";
        util.editObject(sql,null);
        return Borrow.CAN_BORROW;
    }

    /**
     * 得到逾期未还的列表
     * @param dgm
     * @param model
     * @return
     */
    public Map<String,Object> getUrgeList(DataGridModel dgm, String model,HttpSession session)
    {
        updata(session);
        StringBuffer sumSql = new StringBuffer();
        System.out.println(model);
        String condition = ((model==null||model.length()==0)?" and vip.readCard=borrow.user and borrow.book_id=book.book_id":" and vip.readCard=borrow.user and borrow.book_id=book.book_id and vip.cardType='"+model+"'");
        sumSql.append("select count(1) from borrow,vip,book where b_status="+ Borrow.OVERDUE+" "+condition);
        String quSql="select borrow.id,user as user_readCard,borrow.book_id as book_code,borrowTime,duration as deadLine,fine as user_fine,name as user_name,cardType as user_cardType,bookname as book_name from borrow,book,book_login,vip,book_catalog where borrow.b_status="+ Borrow.OVERDUE+" and borrow.user=vip.readCard and book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id "+condition;
        String orderString = "";
        if (StringUtils.isNotBlank(dgm.getSort())) {
            orderString = " order by \"" + dgm.getSort() + "\" " ;
        }
        // 组装分页定义
        String sql = quSql + orderString;
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
                dgm.getRows());
        int count=util.getObjCount(sumSql.toString());
        // 绑定查询结果('total'和'rows'名称不能修改)
        Map<String, Object> result = new HashMap<String, Object>(2);
        Map<String, Object> params = new HashMap<String, Object>();
        System.out.println(pageQuerySql);
        List<Map<String,Object>> rows=util.getMapList(pageQuerySql,(Object[])null);
        for(int i=0;i<rows.size();i++)
        {
            rows.get(i).put("myac","<div onmouseover=\"this.style.cursor='pointer'\" onclick='setTimeout(function(){$(\"#view_grid-table\").click()},20)' class='ace-icon fa fa-search-plus grey'></div>");
        }
        result.put("rows",rows);
        result.put("total",(count/dgm.getRows())+1+"");
        result.put("record",count);
        result.put("page", dgm.getPage());
        return result;
    }
    /**
     * 打印催还单
     * @param id
     * @return
     */
    public Map<String,?> print(String id,HttpSession session)
    {
        updata(session);
        Map<String, Object> params = new HashMap<String, Object>();
        String quSql="select user as user_readCard,borrow.book_id as book_code,borrowTime,duration as deadLine,fine as user_fine,name as user_name,cardType as user_cardType,bookname as book_name from borrow,book,book_login,vip,book_catalog where borrow.b_status="+ Borrow.OVERDUE+" and borrow.user=vip.readCard and borrow.book_id=book.book_id and book.order_id=book_login.order_id and book_login.catalog_id=book_catalog.catalog_id and borrow.id="+id;
        System.out.println(quSql);
        return util.getMap(quSql, params);
    }
}