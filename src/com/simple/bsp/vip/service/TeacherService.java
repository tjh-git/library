package com.simple.bsp.vip.service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.vip.dao.TeacherDao;
import com.simple.bsp.vip.po.Teacher;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/5/31.
 */
@Service("teacherService")
public class TeacherService
{
    @Autowired
    private TeacherDao teacherDao;
    /**
     * 添加教师
     */
    public int addTeacher(Teacher teacher, HttpSession session)
    {
        return teacherDao.addTeacher(teacher,session);
    }

    /**
     *
     * @param dgm
     * @param teacher
     * @return 获取所有教师的信息
     */
    public Map<String, Object> getPageList(DataGridModel dgm, Teacher teacher){
        return teacherDao.getPageList(dgm,teacher);
    }
    /**
     * 更新教师的信息
     */
    public int updateTeacher(Teacher teacher)
    {
        return teacherDao.updateTeacher(teacher);
    }
    /**
     * 批量删除教师
     */
    public int[] deleteTeacherBatch(List<String> list)
    {
        return teacherDao.delTeacherBatch(list);
    }

    /**
     * 批量添加教师
     * @param in
     * @param session
     */
    public int addTeacherBatch(InputStream in, String name, HttpSession session)
    {
        if(name.substring(name.length()-2,name.length()).equals("sx"))
        {
           return readExcel7u(in,session);
        }
        else return readExcel7d(in,session);
    }
    /**
     * 处理2007之后的版本
     */
    public int readExcel7u(InputStream in,HttpSession session)
    {
        int sum=0;
        try {
            XSSFWorkbook wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);
            boolean read=false;
            String[] teachers=new String[20];
            int index=-1;
            for (Iterator ite = sheet.rowIterator(); ite.hasNext(); ) {
                XSSFRow row = (XSSFRow) ite.next();
                if(!read)
                {
                    read=true;
                    continue;
                }
                for (Iterator itet = row.cellIterator(); itet.hasNext(); )
                {
                    XSSFCell cell = (XSSFCell) itet.next();
                    switch (cell.getCellType())
                    {
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            //得到Boolean对象的方法
                            teachers[++index]=cell.getBooleanCellValue()+"";
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            //先看是否是日期格式
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                teachers[++index]=new SimpleDateFormat("yyyy-MM-dd").format(new Date(cell.getDateCellValue().toString()));
                            } else {
                                //读取数字
                                teachers[++index]=(int)cell.getNumericCellValue()+"";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            //读取公式
                            teachers[++index]=cell.getCellFormula().toString();
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            //读取String
                            teachers[++index]=cell.getRichStringCellValue().toString();
                            break;
                    }
                }
                Teacher teacher=new Teacher();
                teacher.setReadCard(teachers[0]);
                teacher.setName(teachers[1]);
                teacher.setIDCard(teachers[2]);
                teacher.setGender(teachers[3]);
                teacher.setCardType(teachers[4]);
                teacher.setAddTime(teachers[5]);
                teacher.setStatus(teachers[6]);
                teacher.setTeachID(teachers[7]);
                teacher.setTeachClass(teachers[8]);
                teacher.setTel(teachers[9]);
                teacher.setQq(teachers[10]);
                teacher.setEmail(teachers[11]);
                System.out.println(teacher.getAddTime());
                teacherDao.addTeacher(teacher, session);
                index=-1;
                sum++;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return sum;
    }
    /**
     * 处理2007之前的版本
     */
    public int readExcel7d(InputStream in,HttpSession session)
    {
        int sum=0;
        try {
            HSSFWorkbook wb = new HSSFWorkbook(in);
            String[] teachers=new String[20];
            int index=-1;
            HSSFSheet sheet = wb.getSheetAt(0);
            boolean read=false;
            for (Iterator ite = sheet.rowIterator(); ite.hasNext(); ) {
                HSSFRow row = (HSSFRow) ite.next();
                if(!read)
                {
                    read=true;
                    continue;
                }
                for (Iterator itet = row.cellIterator(); itet.hasNext(); )
                {
                    HSSFCell cell = (HSSFCell) itet.next();
                    switch (cell.getCellType())
                    {
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            //得到Boolean对象的方法
                            teachers[++index]=cell.getBooleanCellValue()+"";
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            //先看是否是日期格式
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                teachers[++index]=new SimpleDateFormat("yyyy-MM-dd").format(new Date(cell.getDateCellValue().toString()));
                            } else {
                                //读取数字
                                teachers[++index]=(int)cell.getNumericCellValue()+"";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            //读取公式
                            teachers[++index]=cell.getCellFormula().toString();
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            //读取String
                            teachers[++index]=cell.getRichStringCellValue().toString();
                            break;
                    }
                }
                Teacher teacher=new Teacher();
                teacher.setReadCard(teachers[0]);
                teacher.setName(teachers[1]);
                teacher.setIDCard(teachers[2]);
                teacher.setGender(teachers[3]);
                teacher.setCardType(teachers[4]);
                teacher.setAddTime(teachers[5]);
                teacher.setStatus(teachers[6]);
                teacher.setTeachID(teachers[7]);
                teacher.setTeachClass(teachers[8]);
                teacher.setTel(teachers[9]);
                teacher.setQq(teachers[10]);
                teacher.setEmail(teachers[11]);
                teacherDao.addTeacher(teacher, session);
                index=-1;
                sum++;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return sum;
    }

    public Teacher getTeacher(int id)
    {
        return teacherDao.get(id);
    }
}
