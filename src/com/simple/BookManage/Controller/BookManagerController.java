package com.simple.BookManage.Controller;

import com.simple.BookManage.Controller.Excel.ResolveXls;
import com.simple.BookManage.DAO.BookInfoDao;
import com.simple.BookManage.RequestBeans.BookInfo;
import com.simple.BookManage.Service.BookInfoService;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookManagerController {
    @Autowired
    private BookInfoService bs;
    @Autowired
    private BookInfoDao bd;

    @RequestMapping(value = "/book/bookInfo", method = RequestMethod.GET)
    public String bookInfo(Model model, HttpServletRequest req){
        return "BookManager/bookInfo";
    }
    @RequestMapping(value = "/book/addBookInfoPopWin", method = RequestMethod.GET)
    public String Test(Model model, HttpServletRequest req){
        return "BookManager/addBookInfoPopWin";
    }

    @RequestMapping(value = "/book/fileUpload",method=RequestMethod.GET)
    public String fileUpload(){
        return "BookManager/fileUpload";
    }
    @RequestMapping(value="/book/getBookList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getBookList(BookInfo bookInfo, DataGridModel dgm,HttpServletRequest req) {
        Map<String, Object> map = bs.getBookInfoPage(dgm, bookInfo);
        System.out.println("***"+map);
        return map;
    }
    @RequestMapping(value = "/book/uploadXLS",method = RequestMethod.POST)
    public String getDataFromXLS(HttpServletRequest request,MultipartFile uploadFile){
        if (!uploadFile.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  )

                String filePath = request.getSession().getServletContext()
                        .getRealPath("/") + "upload/" + uploadFile.getOriginalFilename();
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                uploadFile.transferTo(saveDir);
                String Filename=uploadFile.getOriginalFilename();
                String fileType=Filename.substring(Filename.lastIndexOf('.')+1);
                ArrayList<BookInfo> list=null;
                if("xlsx".equals(fileType)){
                    list= ResolveXls.readXlsx(saveDir.getAbsolutePath());
                }
                else if("xls".equals(fileType)){
                    list=ResolveXls.readXls(saveDir.getAbsolutePath());
                }
                else{
                    return "BookManager/upLoadinfo/uploadFail";
                }
                for (BookInfo b:list) {
                    try{
                        System.out.println(b.getBookcode()+" aaaaa");
                    b.setPublishdate(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd HH:mm:ss"));
                    b.setGetdate(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd HH:mm:ss"));
                        boolean f=bs.saveBookInfo(b);
                        if(!f){
                            return "BookManager/upLoadinfo/uploadFail";
                        }
                    }catch (Exception e){
                       return "BookManager/upLoadinfo/uploadFail";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "BookManager/upLoadinfo/uploadFail";
            }
        }
         return "BookManager/upLoadinfo/uploadSuccess";
    }
    @RequestMapping(value="/book/addBookInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addBookInfo(BookInfo bookInfo, HttpServletRequest req,HttpSession session){
        String userId = String.valueOf(session.getAttribute("user_id"));
        String userName=(String)session.getAttribute("username");
        bookInfo.setOperator_id(userId);
        bookInfo.setOperator_name(userName);
        bookInfo.setGetdate(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd HH:mm:ss"));
        if(bookInfo.getIs_journal()==null)bookInfo.setIs_journal("否");
        if(bookInfo.getIs_refbook()==null)bookInfo.setIs_refbook("否");
        boolean f=bs.saveBookInfo(bookInfo);
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (f) {
                map.put("mes", "图书编目录入成功");
            } else {
                map.put("mes", "图书编目录入失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "图书编目录入失败");
        }
        return map;
    }
    @RequestMapping(value = "/book/updateBookInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updata(BookInfo bookInfo, HttpServletRequest req,HttpSession session)
    {
        System.out.println(bookInfo);
        String userId = String.valueOf(session.getAttribute("user_id"));
        String userName=(String)session.getAttribute("username");
        bookInfo.setOperator_id(userId);
        bookInfo.setOperator_name(userName);
        bookInfo.setGetdate(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd HH:mm:ss"));
        if(bookInfo.getIs_journal()==null)bookInfo.setIs_journal("否");
        if(bookInfo.getIs_refbook()==null)bookInfo.setIs_refbook("否");
        boolean f=bs.updateBookInfo(bookInfo);
        Map<String, String> map = new HashMap<String, String>();
        System.out.println(f);
        try {
            if (f) {
                map.put("mes", "图书编目更新成功");
            } else {
                map.put("mes", "图书编目更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "图书编目更新失败");
        }
        System.out.println(map);
        return map;
    }
    @RequestMapping(value = "/bookLogin/editBookInfo",method = RequestMethod.GET)
    public String editIt(String code,HttpServletRequest request)
    {
        Map<String,?> catalog=bs.getOne(code);
        request.setAttribute("catalog",catalog);
        System.out.println(catalog);
        return "/BookManager_new/BookCatalogue_add/blank";

    }
    @RequestMapping(value="/bookLogin/delBookInfo",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delBookInfo(@RequestParam("id") List<String> idList){

        Map<String, String> map = new HashMap<String, String>();
        try {
            int[] result = bs.delBookInfo(idList);
            int sum = 0;
            for(int j = 0; j < result.length; j ++){
                sum += result[j];
            }
            map.put("mes", "删除成功["+sum+"]条记录");

        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "删除失败");
        }
        return map;//重定向
    }
    @RequestMapping(value = "/book/search",method = RequestMethod.GET)
    public ModelAndView booksearch(@RequestParam(value = "bookname")String bookname){

        return null;
    }
}
