package com.simple.bsp.vip.service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.vip.dao.StudentDao;
import com.simple.bsp.vip.po.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/1.
 */
@Service("studentService")
public class StudentService
{
    @Autowired
    private StudentDao studentDao;

    /**
     * 添加学生
     * @param student
     * @param session
     * @return
     */
    public int addStudent(Student student, HttpSession session)
    {
        return studentDao.addStudnet(student,session);
    }

    /**
     * 获取所有的学生列表
     * @param dgm
     * @param student
     * @return
     */
    public Map<String, Object> getPageList(DataGridModel dgm, Student student){
        return studentDao.getPageList(dgm,student);
    }
    /**
     * 更新学生的信息
     */
    public int updateStudent(Student student)
    {
        return studentDao.updateStudent(student);
    }
    /**
     * 批量删除学生
     */
    public int[] deleteStudentrBatch(List<String> list)
    {
        return studentDao.delStudentBatch(list);
    }
    /**
     * 批量添加学生
     * @param in
     */
    public int addStudentBatch(InputStream in, String name,HttpSession session) throws IOException {
        if(name.substring(name.length()-2,name.length()).equals("sx"))
        {
            return readExcel7u(in,session);
        }
        else return readExcel7d(in,session);
    }
    /**
     * 处理2007之后的版本
     */
    public int readExcel7u(InputStream in, HttpSession session)
    {
        int sum=0;
        try {
            XSSFWorkbook wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);
            boolean read=false;
            String[] students=new String[20];
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
                            students[++index]=cell.getBooleanCellValue()+"";
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            //先看是否是日期格式
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                students[++index]=new SimpleDateFormat("yyyy-MM-dd").format(new Date(cell.getDateCellValue().toString()));
                            } else {
                                //读取数字
                                students[++index]=(int)cell.getNumericCellValue()+"";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            //读取公式
                            students[++index]=cell.getCellFormula().toString();
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            //读取String
                            students[++index]=cell.getRichStringCellValue().toString();
                            break;
                    }
                }
                Student student=new Student();
                student.setReadCard(students[0]);
                student.setName(students[1]);
                student.setIDCard(students[2]);
                student.setGender(students[3]);
                student.setCardType(students[4]);
                student.setAddTime(students[5]);
                student.setStatus(students[6]);
                student.setStuID(students[7]);
                student.setGrade(students[8]);
                student.setClazz(students[9]);
                student.setDeadLine(students[10]);
                studentDao.addStudnet(student, session);
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
    public int readExcel7d(InputStream in, HttpSession session) throws IOException {
        int sum=0;
        try {
            HSSFWorkbook wb = new HSSFWorkbook(in);
            String[] students=new String[20];
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
                            students[++index]=cell.getBooleanCellValue()+"";
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            //先看是否是日期格式
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                students[++index]=new SimpleDateFormat("yyyy-MM-dd").format(new Date(cell.getDateCellValue().toString()));
                            } else {
                                //读取数字
                                students[++index]=(int)cell.getNumericCellValue()+"";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            //读取公式
                            students[++index]=cell.getCellFormula().toString();
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            //读取String
                            students[++index]=cell.getRichStringCellValue().toString();
                            break;
                    }
                }
                Student student=new Student();
                student.setReadCard(students[0]);
                student.setName(students[1]);
                student.setIDCard(students[2]);
                student.setGender(students[3]);
                student.setCardType(students[4]);
                student.setAddTime(students[5]);
                student.setStatus(students[6]);
                student.setStuID(students[7]);
                student.setGrade(students[8]);
                student.setClazz(students[9]);
                student.setDeadLine(students[10]);
                studentDao.addStudnet(student, session);
                index=-1;
                sum++;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            throw  e;
        }
        return sum;
    }
    public Student getStudent(int id)
    {
        return studentDao.get(id);
    }
}
