package com.simple.BookManage.Controller.Excel;

import com.simple.BookManage.RequestBeans.BookInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by lovesyxfuffy on 2016/6/3.
 */
public class ResolveXls {
    public static ArrayList<BookInfo> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        BookInfo bookInfo = null;
        ArrayList<BookInfo> list = new ArrayList<BookInfo>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                bookInfo = new BookInfo();
                // 循环列Cell
                // 0 id 1 bookcode 2 have_vol 3 seriesname 4 write 5 translator 6 bookname 7 bookab 8 isbn 9 publishcom 10 booklanguage
                //11 booktype 12 publishdate 13 editiontimes 14 printtimes 15 booksize 16 bookbind 17 bookpagenum 18 bookprice 19 is_refbook
                //20 is_journal 21 operator_id 22 operator_name 23 getdate
                // for (int cellNum = 0; cellNum <=4; cellNum++) {
                Field fields[]= bookInfo.getClass().getDeclaredFields();
                int index=0;
                for (Field f:fields) {
                    try {
                        f.setAccessible(true);
                        if(f.getType()==String.class)
                            f.set(bookInfo,getValue(hssfRow.getCell(index++)));
                        else
                            f.set(bookInfo,Integer.parseInt(getValue(hssfRow.getCell(index++))));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            list.add(bookInfo);
        }
        return list;
    }
    public static ArrayList<BookInfo> readXlsx(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        BookInfo bookInfo = null;
        ArrayList<BookInfo> list = new ArrayList<BookInfo>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {

            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }

                bookInfo = new BookInfo();
                // 循环列Cell
                // 0 id 1 bookcode 2 have_vol 3 seriesname 4 write 5 translator 6 bookname 7 bookab 8 isbn 9 publishcom 10 booklanguage
                //11 booktype 12 publishdate 13 editiontimes 14 printtimes 15 booksize 16 bookbind 17 bookpagenum 18 bookprice 19 is_refbook
                //20 is_journal 21 operator_id 22 operator_name 23 getdate
                // for (int cellNum = 0; cellNum <=4; cellNum++) {
                Field fields[]= bookInfo.getClass().getDeclaredFields();
                int index=0;
                for (Field f:fields) {
                    try {
                        f.setAccessible(true);
                        if(f.getType()==String.class)
                            f.set(bookInfo,getValue(xssfRow.getCell(0)));
                        else
                            f.set(bookInfo,Integer.parseInt(getValue(xssfRow.getCell(index++))));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(bookInfo.getBookab());
            list.add(bookInfo);
        }
        return list;
    }
    private static   String getValue(XSSFCell xssfRow) {

        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            String a;
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf((int)xssfRow.getNumericCellValue());
        } else {

            return String.valueOf(xssfRow.getStringCellValue());

        }
    }

    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf((int)hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
