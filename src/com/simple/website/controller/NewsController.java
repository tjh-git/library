package com.simple.website.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.website.po.News;
import com.simple.website.service.INewsService;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.org.service.OrgService;
import com.simple.bsp.security.po.PubUsers;
import com.simple.bsp.user.service.UserService;
import com.simple.bsp.usernewstype.service.NewsTypeService;

import sun.misc.BASE64Decoder;
@Controller
public class NewsController {
	
	@Autowired
	private INewsService newsService;
	@Autowired
	NewsTypeService newsTypeService;
	@Autowired
	UserService userService;
	@Autowired
	OrgService orgService;
	/**
	 * 通过菜单进入新闻管理界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deployNews",method=RequestMethod.GET)
    public String list(Model model ,HttpServletRequest request){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			String orgId = ((PubUsers)principal).getUserOrg();
			request.setAttribute("userNewsNameList", newsService.getUserListFromOrgId(orgId));
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
		request.setAttribute("today",df.format(new Date()));
		request.setAttribute("newsTypeList", list);
		
        return "bsp/news/news";
    }
	
	
	
	
	@RequestMapping(value="/deployNews1",method=RequestMethod.GET)
    public String list1(Model model ,HttpServletRequest request){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
		request.setAttribute("today",df.format(new Date()));
		request.setAttribute("newsTypeList", list);
		
        return "bsp/news/news1";
    }
	
	@RequestMapping(value="/deployNews2",method=RequestMethod.GET)
    public String list2(Model model ,HttpServletRequest request){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
		request.setAttribute("today",df.format(new Date()));
		request.setAttribute("newsTypeList", list);
		
        return "bsp/news/news2";
    }
	
	
	
	/**
	 * 通过菜单进入新闻审核界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/newsCheck",method=RequestMethod.GET)
    public String listCheck(Model model ,HttpServletRequest request){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		request.setAttribute("newsTypeList", list);
		
        return "bsp/news/newsCheck";
    }
	
	
	/**
	 * 添加新闻信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value="/news4Add2",method=RequestMethod.GET)
    public String news4Add2(HttpServletRequest request ){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
		request.setAttribute("today",df.format(new Date()));
		request.setAttribute("publishToday",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		request.setAttribute("newsTypeList", list);
        return "bsp/news/news4Add2";
    }
	@RequestMapping(value="/news/news4Add",method=RequestMethod.GET)
    public String news4Add(HttpServletRequest request ){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
		request.setAttribute("today",df.format(new Date()));
		request.setAttribute("publishToday",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		request.setAttribute("newsTypeList", list);
        return "bsp/news/news4Add";
    }
	
	
	/**
	 * 修改新闻信息页面
	 * @return
	 */
	@RequestMapping(value="/news/news4Update",method=RequestMethod.GET)
	public String news4update(HttpServletRequest request,@RequestParam("sno") String sno){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		request.setAttribute("newsTypeList", list);
		List<Map<String,Object>> newsList=newsService.getNewsWithId(sno);
		if(newsList.size()>0){
			request.setAttribute("news", newsList.get(0));
		}
		return "bsp/news/news4Update";
	}
	
	/**
	 * 修改新闻信息页面
	 * @return
	 */
	@RequestMapping(value="/news/newsCheck4Update",method=RequestMethod.GET)
	public String newsCheck4Update(HttpServletRequest request,@RequestParam("sno") String sno){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		request.setAttribute("newsTypeList", list);
		List<Map<String,Object>> newsList=newsService.getNewsWithId(sno);
		if(newsList.size()>0){
			request.setAttribute("news", newsList.get(0));
		}
		return "bsp/news/newsCheckUpdate";
	}
	
	/**
	 * 预览新闻信息页面
	 * @return
	 */
	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String preview(HttpServletRequest request,@RequestParam("sno") String sno){
		List<Map<String,Object>> newsList=newsService.getNewsWithId(sno);
		if(newsList.size()>0){
			request.setAttribute("news", newsList.get(0));
		}
		return "bsp/news/preview";
	}
	
	
	/**
	 * 预览新闻明细页面
	 * @return
	 */
	@RequestMapping(value="/news/detail",method=RequestMethod.GET)
	public String detail(HttpServletRequest request,@RequestParam("sno") String sno){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		request.setAttribute("newsTypeList", list);
		List<Map<String,Object>> newsList=newsService.getNewsWithId(sno);
		if(newsList.size()>0){
			request.setAttribute("news", newsList.get(0));
		}
		return "bsp/news/newsDetail";
	}
	
	/**
	 * 默认分页查询新闻信息
	 * @param dgm
	 * @param news
	 * @return
	 */
	@RequestMapping(value="/news/queryNewsList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsList(DataGridModel dgm, News news){

		if(news.getNewsIsChecked()==null)
		{
			news.setNewsIsChecked("3");
		}
		return newsService.getNewsWithPara(dgm,news);
	}
	
	@RequestMapping(value="/news/queryNewsListCheck",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsListCheck(DataGridModel dgm, News news){

		return newsService.getNewsWithPara(dgm,news);
	}
	
	@RequestMapping(value="/news/queryNewsList1",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsList1(DataGridModel dgm, News news){
		news.setNewsType("10000");
		news.setNewsIsChecked("1");
		return newsService.getNewsWithPara(dgm,news);
	}
	@RequestMapping(value="/news/queryNewsList2",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryNewsList2(DataGridModel dgm, News news){
		news.setNewsType("10001");
		news.setNewsIsChecked("1");
		return newsService.getNewsWithPara(dgm,news);
	}
	
	/**
	 * 保存新闻记录
	 * @param news
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/news/saveNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveNews(News news,HttpServletRequest request) throws Exception{
		String contentStr=news.getNewsContent();
		BASE64Decoder decoder = new BASE64Decoder();
		news.setNewsContent(new String (decoder.decodeBuffer(contentStr),"UTF-8"));
		Map<String, String> map = new HashMap<String, String>();
		if(newsService.addNews(news)){
			map.put("mes", "添加成功");
		}else{
			map.put("mes", "添加失败");
		}
		return map;
	}
	/**
	 * 删除新闻信息
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/news/delNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delNews(HttpServletRequest request,@RequestParam("sno") List<String> listSno){
		Map<String, String> map = new HashMap<String, String>();
		String sno="";
		int k=0;
		for(int i=0;i<listSno.size();i++){
			sno=listSno.get(i);
			if(newsService.deleteNews(sno)){
				k++;
			}
		}
			map.put("mes", "本次删除信息："+listSno.size()+"条成功："+k+"条");
		return map;
	}
	/**
	 * 删除新闻信息
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/news/checkNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> checkNews(HttpServletRequest request,@RequestParam("sno") List<String> listSno,
			@RequestParam("newsIsChecked") String newsIsChecked){
		Map<String, String> map = new HashMap<String, String>();
		String sno="";
		int k=0;
		for(int i=0;i<listSno.size();i++){
			sno=listSno.get(i);
			if(newsService.setCheckedStatus(sno, newsIsChecked)){
				k++;
			}
		}
			map.put("mes", "本次审核信息："+listSno.size()+"条成功："+k+"条");
		return map;
	}
	/**
	 * 更新新闻信息
	 * @param role
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/news/updateNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateNews(HttpServletRequest request,News news) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		String contentStr=news.getNewsContent();
		BASE64Decoder decoder = new BASE64Decoder();
		news.setNewsContent(new String (decoder.decodeBuffer(contentStr),"UTF-8"));
			if("1".equals(news.getNewsIsChecked())){
				news.setNewsIsChecked(null);
			}else{
				news.setNewsIsChecked("0");
			}
			if(newsService.updateNews(news)){
				map.put("mes", "更新信息成功");
			}else{
				map.put("mes", "更新信息失败");
			}
		return map;
	}
	
	// 发布新闻类消息时，上传新闻内的图片
	@RequestMapping(value = "/news/upload", method = RequestMethod.POST)
	@ResponseBody
	public void upload(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("Type") String type) throws Exception {
		String logoPathDir = "/WEB-INF/resources/upload/"+type;

		String path = request.getSession().getServletContext()
				.getRealPath(logoPathDir);

		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		sfu.setHeaderEncoding("UTF-8");

		try {
			List fileList = sfu.parseRequest(request);
			String fileName = "";
			FileItem fi;
			for (int i = 0; i < fileList.size(); i++) {
				fi = (FileItem) fileList.get(i);
				if (!fi.isFormField()) {
					fileName = fi.getName();
					if (fileName == null || "".equals(fileName.trim())) {
						continue;
					}

					// 截取后缀名
					int index = fileName.lastIndexOf('.');
					String extend = "";
					if ((index > -1) && (i < (fileName.length() - 1))) {
						extend = fileName.substring(index);
					} else {
						continue;
					}

					// 取文件名
					String picname = NextID.getNextID(type);

					File saveFile = new File(path + "//" + picname + extend);
					fi.write(saveFile);
					fileName = picname + extend;
				}
			}
			response.setCharacterEncoding("UTF-8");
			 PrintWriter out = response.getWriter();  
			String callback=request.getParameter("CKEditorFuncNum");
			StringBuffer url = request.getRequestURL();  
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
			String remotefilePath = tempContextUrl +request.getContextPath()+ "/resources/upload/"+type+"/"+ fileName;
			out.println("<script type=\"text/javascript\">");
			if("hlink".equals(type)){
				out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +remotefilePath + "','')"); 
			}else{
				out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'"+"resources/upload/"+type+"/"+ fileName + "','')"); 
			}
			
			out.println("</script>");

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("0");
		}
	}
	/**
	 * 上传图片类新闻
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/news/uploadNewsImage2", method = RequestMethod.POST)
	@ResponseBody
	public void uploadNewsImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String logoPathDir = "/WEB-INF/resources/upload/newsimage";

		String path = request.getSession().getServletContext()
				.getRealPath(logoPathDir);

		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		sfu.setHeaderEncoding("UTF-8");

		try {
			List<?> fileList = sfu.parseRequest(request);
			String fileName = "";
			FileItem fi;
			for (int i = 0; i < fileList.size(); i++) {
				fi = (FileItem) fileList.get(i);
				if (!fi.isFormField()) {
					fileName = fi.getName();
					if (fileName == null || "".equals(fileName.trim())) {
						continue;
					}

					// 截取后缀名
					int index = fileName.lastIndexOf('.');
					String extend = "";
					if ((index > -1) && (i < (fileName.length() - 1))) {
						extend = fileName.substring(index);
					} else {
						continue;
					}

					// 取文件名
					String name = NextID.getNextID("image");

					File saveFile = new File(path + "//" + name + extend);
					fi.write(saveFile);
					fileName = name + extend;
				}
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(
					"/resources/upload/newsimage/" + fileName);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("0");
		}
	}
	
	/**
	 * 上传附件类新闻
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/news/uploadNewsAccessory", method = RequestMethod.POST)
	@ResponseBody
	public void uploadNewsAccessory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String logoPathDir = "/WEB-INF/resources/upload/newsaccessory";

		String path = request.getSession().getServletContext()
				.getRealPath(logoPathDir);

		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		sfu.setHeaderEncoding("UTF-8");

		try {
			List<?> fileList = sfu.parseRequest(request);
			String fileName = "";
			FileItem fi;
			for (int i = 0; i < fileList.size(); i++) {
				fi = (FileItem) fileList.get(i);
				if (!fi.isFormField()) {
					fileName = fi.getName();
					if (fileName == null || "".equals(fileName.trim())) {
						continue;
					}

					// 截取后缀名
					int index = fileName.lastIndexOf('.');
					String extend = "";
					if ((index > -1) && (i < (fileName.length() - 1))) {
						extend = fileName.substring(index);
					} else {
						continue;
					}

					// 取文件名
					String name = NextID.getNextID("accessory");

					File saveFile = new File(path + "//" + name + extend);
					fi.write(saveFile);
					fileName = name + extend;
				}
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(
					"/resources/upload/newsaccessory/" + fileName);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("0");
		}
	}
	
	/**
	 * 进入新闻同步推送页面
	 * @return
	 */
	@RequestMapping(value="/news/synchronousPushPopwin",method=RequestMethod.GET)
	public String synchronousPushPopwin(@RequestParam("newsId") String newsId,HttpServletRequest request){
		request.setAttribute("newsId", newsId);
		return "bsp/news/synchronousPush";
	}
	
	/**
	 * 获取用户所有新闻类型
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/news/getAllNewstype",method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAllNewstype(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>>  list=new ArrayList<Map<String, Object>>();
		if(principal instanceof PubUsers) {
			String userId = ((PubUsers)principal).getUserId();
			list=newsTypeService.getAllNewsTypeByUserId(userId);
		}
		
		return list;
	}
	
	/**
	 * 同步推送
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/news/synchronousPush", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> synchronousPush(
			@RequestParam("newsId") String newsId,
			@RequestParam("newsIds") String newsIds) {

		Map<String, String> map = new HashMap<String, String>();

		List<Object[]> objList = new ArrayList<Object[]>();
		News news = newsService.getNewsWithIdEx(newsId);
		if(news!=null){
			String isLink = news.getNewsIsLinkTitle();
			String IsAccessory = news.getNewsIsAccessory();
			
			if(isLink!=null&&isLink.equals("1")){
				//链接标题
			}else{
				//非链接标题  
				if(IsAccessory!=null&&IsAccessory.equals("1")){
					//附件标题
				}else{
					//非附件标题 部署更改
					news.setNewsIsLinkTitle("1");
					news.setNewsIsAccessory("0");
					news.setNewsLinkUrl("http://www.rwsk.sdu.edu.cn/newsDetail?sno="+newsId);
					news.setNewsContent("");
				}
			}
			
			if (newsIds.length() > 0) {
				String[] ids = newsIds.split(",");
				for(int i=0;i<ids.length;i++){
					news.setNewsType(ids[i]);
					newsService.addNews(news);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				map.put("mes", "推送成功");
			}else{
				map.put("mes", "请选择新闻类型");
			}
		}else{
			map.put("mes", "推送失败");
		}
		
		return map;
	}
	
	
}
