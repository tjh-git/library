package com.simple.BookManage.Controller;

import com.simple.BookManage.Service.CommentService;
import com.simple.BookManage.Service.Impl.JString;
import com.simple.bsp.common.util.DateTool;
import com.simple.bsp.security.po.PubUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import com.simple.BookManage.RequestBeans.Comment;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lovesyxfuffy on 2016/6/11.
 */
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment/commentlist", method = RequestMethod.GET)
    public ModelAndView commentList(@RequestParam(value="page",required=false)String page,@RequestParam(value="aboutbookid",required=false) String about){
        int pagenum=1;
        int aboutbookid=-1;
        if(about!=null)
            aboutbookid= Integer.parseInt(about);
        if(page!=null)
            pagenum=Integer.parseInt(page);
        ModelAndView modelAndView = new ModelAndView("Comment/BlogList/blank");
        modelAndView.addObject("result", commentService.getComments(pagenum,aboutbookid));
        return modelAndView;
    }
    @RequestMapping(value = "/comment/onecomment", method = RequestMethod.GET)
    public ModelAndView getOnecomment(int id){

        ModelAndView modelAndView = new ModelAndView("Comment/Blog/blank");
        modelAndView.addObject("result",commentService.getOneComment(id));
        return modelAndView;
    }
    @RequestMapping(value = "/comment/getcommentlist", method = RequestMethod.POST)
    @ResponseBody
    public Object getcommentList(@RequestParam(value="page",required=false)String page,@RequestParam(value="aboutbookid",required=false) int aboutbookid){
        int pagenum=1;
        if(page!=null)
            pagenum=Integer.parseInt(page);

        return commentService.getComments(pagenum,aboutbookid);
    }
    @RequestMapping(value = "/comment/add",method = RequestMethod.POST)
    public ModelAndView addComment(HttpServletRequest request, Comment comment, MultipartFile image, HttpSession session) throws IOException {
        if (!image.isEmpty()) {
            // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  )

            String filePath = request.getSession().getServletContext()
                    .getRealPath("/") + "/WEB-INF/resources/" + image.getOriginalFilename();
            File saveDir = new File(filePath);
            if (!saveDir.getParentFile().exists())
                saveDir.getParentFile().mkdirs();

            // 转存文件
            image.transferTo(saveDir);
            comment.setCover_path("/resources/"+image.getOriginalFilename());
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = (String) session.getAttribute("user_id");
            String userName=(String)session.getAttribute("username");
            comment.setUser_id(userId);
            comment.setUser_name(userName);
            comment.setWrite_date(DateTool.getCurrentTimeMillisAsString("yyyy-MM-dd"));
            comment.setComment_count(0);
            commentService.addComment(comment);
        }
        return new ModelAndView("redirect:/comment/commentlist");
    }
    @RequestMapping(value = "/comment/readstar",method = RequestMethod.GET)
    public String readStar()
    {
        return "Comment/readStar/blank";
    }
}
