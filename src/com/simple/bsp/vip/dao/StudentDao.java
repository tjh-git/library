package com.simple.bsp.vip.dao;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import com.simple.bsp.vip.po.Student;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/1.
 */
@SuppressWarnings("deprecation")
@Repository("studentDao")
public class StudentDao
{
    @Autowired
    private DBUtil util;
    public int addStudnet(Student student, HttpSession session)
    {
        String school_id= session.getAttribute("school_id")+"";
        String user_name= session.getAttribute("user_name")+"";
        student.setOperator(user_name);
        student.setSchool_id(school_id);
        String sql = "insert into  vip (readCard, name, IDCard, gender, cardType, addTime, status,stuID,deadLine,clazz,grade,operator,school_id) values (:readCard, :name, :IDCard, :gender,:cardType, :addTime, :status,:stuID,:deadLine,:clazz,:grade,:operator,:school_id)";
        return util.editObject(sql, student);
    }
    public Map<String,Object> getPageList(DataGridModel dgm, Student student)
    {
        // 查询结果Map
        Map<String, Object> result = new HashMap<String, Object>(2);
        OrgDesc orgDesc = null;
        // 获取当前登录用户
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (principal instanceof PubUsers) {
            // 根据登录用户的机构编号(userOrg)获取机构描述对象
            orgDesc = util.getOrgDescByOrgId(((PubUsers) principal)
                    .getUserOrg());
        }
        // 获取记录数
        StringBuffer sumSql = new StringBuffer();
        sumSql.append("select count(1) from vip where cardType='学生'");
        // 获取结果集
        String quSql = "select * from vip where cardType='学生'";
        // 组装查询条件
        StringBuffer sqlSb = new StringBuffer();
        Map<String, Object> params = new HashMap<String, Object>();
        // 组装排序规则
        String orderString = "";
        if (StringUtils.isNotBlank(dgm.getSort())) {
            orderString = " order by \"" + dgm.getSort() + "\" " + dgm.getOrder();
        }
        // 组装分页定义
        String sql = quSql + sqlSb.toString() + orderString;
        int count=util.getMapList(sql,(Object[])null).size();
        String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
        // 绑定查询结果('total'和'rows'名称不能修改)
        result.put("total",(count/dgm.getRows())+1+"");
        result.put("rows",util.getMapList(pageQuerySql, params));
        result.put("record",count);
        result.put("page", dgm.getPage());
        return result;
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    public int updateStudent(Student student)
    {
        StringBuffer sqlSb = new StringBuffer(
                "update vip set readCard=:readCard, name=:name, IDCard=:IDCard, gender=:gender, cardType=:cardType,addTime=:addTime, status=:status,stuID=:stuID,deadLine=:deadLine,grade=:grade,clazz=:clazz");
        sqlSb.append(" where id=:id");
        System.out.println(sqlSb.toString());
        return util.editObject(sqlSb.toString(),student);
    }
    /**
     * 批量删除学生
     *
     * @param idList
     * @return
     */
    public int[] delStudentBatch(List<String> idList) {
        String sql = "delete from vip where readCard = ? and cardType='学生'";
        return util.batchDelete(sql, idList);
    }

    public Student get(int id)
    {
        String sql="select * from vip where id=?";
        Object[] objs={id};
        Student student= (Student) util.getObject(sql,objs,Student.class);
        return student;
    }
}
