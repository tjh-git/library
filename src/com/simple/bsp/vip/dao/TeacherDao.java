package com.simple.bsp.vip.dao;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.SqlUtil;
import com.simple.bsp.org.po.OrgDesc;
import com.simple.bsp.security.po.PubUsers;
import com.simple.bsp.vip.po.Teacher;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/5/31.
 */
@SuppressWarnings("deprecation")
@Repository("teacherDao")
public class TeacherDao
{
    @Autowired
    private DBUtil util;
    public int addTeacher(Teacher teacher, HttpSession session)
    {
        String school_id= session.getAttribute("school_id")+"";
        String user_name= session.getAttribute("user_name")+"";
        teacher.setOperator(user_name);
        teacher.setSchool_id(school_id);
        String sql = "insert into  vip (readCard, name, IDCard, gender, cardType, addTime, status,teachID, teachClass,tel,qq, email,operator,school_id) values (:readCard, :name, :IDCard, :gender, :cardType, :addTime, :status,:teachID,:teachClass,:tel, :qq, :email,:operator,:school_id)";
        System.out.println(teacher.getName()+"  "+teacher.getCardType()+"  "+teacher.getReadCard());
        return util.editObject(sql, teacher);
    }

    public Map<String,Object> getPageList(DataGridModel dgm, Teacher teacher)
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
         sumSql.append("select count(1) from vip where cardType='教师'");
          // 获取结果集
         String quSql = "select * from vip where cardType='教师'";
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
         String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
                 dgm.getRows());
         // 绑定查询结果('total'和'rows'名称不能修改)
         result.put("total", util.getObjCount(sumSql.toString()));
         result.put("rows", util.getMapList(pageQuerySql, params));
         return result;
    }

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    public int updateTeacher(Teacher teacher)
    {
        StringBuffer sqlSb = new StringBuffer(
                "update vip set readCard=:readCard, name=:name, IDCard=:IDCard, gender=:gender, cardType=:cardType,addTime=:addTime, status=:status,teachID=:teachID,teachClass=:teachClass,tel=:tel,qq=:qq,email=:email ");
        sqlSb.append(" where id=:id");
        return util.editObject(sqlSb.toString(),teacher);
    }
    /**
     * 批量删除教师
     *
     * @param idList
     * @return
     */
    public int[] delTeacherBatch(List<String> idList) {
        String sql = "delete from vip where readCard=? and cardType='教师'";
        return util.batchDelete(sql, idList);
    }

    public Teacher get(int id)
    {
        String sql="select * from vip where id=?";
        Object[] objs={id};
        Teacher teacher= (Teacher) util.getObject(sql,objs,Teacher.class);
        return teacher;
    }
}
