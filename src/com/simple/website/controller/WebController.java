package com.simple.website.controller;

import com.simple.bsp.common.util.HTMLUtil;
import com.simple.bsp.sysparam.service.SysParamService;
import com.simple.website.po.News;
import com.simple.website.po.PageBean;
import com.simple.website.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

	@Autowired
	private INewsService newsService;
	@Autowired
	private SysParamService sysParamService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String RootWelcome(HttpServletRequest request) throws Exception {
		return "redirect:website/index";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "website/index", method = RequestMethod.GET)
	public String websiteIndex(HttpServletRequest request, PageBean pageBean, News news) throws Exception {
	//	return "redirect:/login";
		pageBean.setCurrentPage(1);
		
		
		// 图片新闻
		news.setNewsIsImageNews("1");
		pageBean.setPageSize(3);
		List<Map<String, Object>> tpxwList = newsService.getNewsWithPara(news,
				pageBean).getDispList();
		request.setAttribute("tpxwList", tpxwList);
		// 图片新闻字段重置
		news.setNewsIsImageNews(null);		
		
		// 政务要闻
		news.setNewsType("8");
		pageBean.setPageSize(8);
		List<Map<String, Object>> zwywList = newsService.getNewsWithPara(news, pageBean).getDispList();
		request.setAttribute("zwywList", zwywList);
		// 政务要闻栏目第一条记录
		if (zwywList != null && zwywList.size() > 0) {
			Map<String, Object> zwywFirst = new HashMap<String, Object>();
			String noTagContent = zwywList.get(0).get("newsContent").toString();
			noTagContent = HTMLUtil.getTextFromHtml(noTagContent);
			zwywFirst.put("newsId", zwywList.get(0).get("newsId"));
			zwywFirst.put("newsTitle", zwywList.get(0).get("newsTitle"));
			zwywFirst.put("newsContent", noTagContent);
			request.setAttribute("zwywFirst", zwywFirst);
		}
		// 领导活动
		news.setNewsType("1");
		pageBean.setPageSize(8);
		List<Map<String, Object>> ldhdList = newsService.getNewsWithPara(news, pageBean).getDispList();
		request.setAttribute("ldhdList", ldhdList);
		// 获取领导活动前两条新闻
		List<Map<String, Object>> ldhdTopList = this.getTopNews(ldhdList, 2, 65);
		request.setAttribute("ldhdTopList", ldhdTopList);
		// 工作动态
		news.setNewsType("3");
		pageBean.setPageSize(8);
		List<Map<String, Object>> gzdtList = newsService.getNewsWithPara(news, pageBean).getDispList();
		request.setAttribute("gzdtList", gzdtList);
		// 获取工作动态前两条新闻
		List<Map<String, Object>> gzdtTopList = this.getTopNews(gzdtList, 2, 65);
		request.setAttribute("gzdtTopList", gzdtTopList);

		// 媒体报道
		news.setNewsType("4");
		pageBean.setPageSize(6);
		List<Map<String, Object>> mtbdList = newsService.getNewsWithPara(news, pageBean).getDispList();
		request.setAttribute("mtbdList", mtbdList);
		// 经验交流
		news.setNewsType("5");
		pageBean.setPageSize(6);
		List<Map<String, Object>> jyjlList = newsService.getNewsWithPara(news, pageBean).getDispList();
		request.setAttribute("jyjlList", jyjlList);
		// 先进典型
		news.setNewsType("6");
		pageBean.setPageSize(6);
		List<Map<String, Object>> xjdxList = newsService.getNewsWithPara(news, pageBean).getDispList();
		request.setAttribute("xjdxList", xjdxList);
		// 护路文化
		news.setNewsType("9");
		pageBean.setPageSize(6);
		List<Map<String, Object>> hlwhList = newsService.getNewsWithPara(news, pageBean).getDispList();
		request.setAttribute("hlwhList", hlwhList);
		return "redirect:/login";
	}

	// 根据查询出来的新闻list获取前几条新闻,newsList,n前几条,totalNum限制字数
	public List<Map<String, Object>> getTopNews(List<Map<String, Object>> newsList, int n, int totalNum) {
		if (newsList != null && newsList.size() > 0) {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int endIndex = newsList.size() > n ? n : newsList.size();
			for (int i = 0; i < endIndex; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String noTagContent = newsList.get(i).get("newsContent").toString();
				noTagContent = HTMLUtil.getTextFromHtml(noTagContent);
				map.put("newsId", newsList.get(i).get("newsId"));
				map.put("newsTitle", newsList.get(i).get("newsTitle"));
				map.put("newsContent",
						noTagContent.substring(0, noTagContent.length() > totalNum ? totalNum : noTagContent.length()));
				mapList.add(map);
			}
			return mapList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/website/commonMore", method = RequestMethod.GET)
	public String commonMore(HttpServletRequest request, @RequestParam String perTypeCode,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "currentPage", required = false) Integer currentPage, PageBean pageBean)
					throws Exception {
		if (pageSize == null || pageSize <= 0) {
			pageSize = 20;
		}
		if (currentPage == null || currentPage <= 0) {
			currentPage = 1;
		}
		String title = "";
		if (perTypeCode.equals("1")) {
			title = "领导活动";
		} else if (perTypeCode.equals("2")) {
			title = "新闻动态";
		} else if (perTypeCode.equals("3")) {
			title = "工作动态";
		} else if (perTypeCode.equals("4")) {
			title = "媒体报道";
		} else if (perTypeCode.equals("5")) {
			title = "经验交流";
		} else if (perTypeCode.equals("6")) {
			title = "先进典型";
		} else if (perTypeCode.equals("7")) {
			title = "机构概况";
		} else if (perTypeCode.equals("8")) {
			title = "政务要闻";
		} else if (perTypeCode.equals("9")) {
			title = "护路文化";
		}
		request.setAttribute("title", title);
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		News news = new News();
		news.setNewsType(perTypeCode);
		pageBean = newsService.getNewsWithPara(news, pageBean);
		Map<String, Object> newsInfo = new HashMap<String, Object>();
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("perTypeCode", perTypeCode);
		request.setAttribute("newsInfo", newsInfo);
		String navIndex = request.getParameter("navIndex");
		if (navIndex == null || navIndex.equals("")) {
			navIndex = "1";
		}
		request.setAttribute("navIndex", navIndex);
		return "website/more";
	}

	// 站内搜索
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "website/znss", method = RequestMethod.GET)
	public String znss(HttpServletRequest request, @RequestParam String key,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "currentPage", required = false) Integer currentPage, PageBean pageBean) {

		if (pageSize == null || pageSize <= 0) {
			pageSize = 20;
		}
		if (currentPage == null || currentPage <= 0) {
			currentPage = 1;
		}
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		News news = new News();
		Map<String, Object> newsInfo = new HashMap<String, Object>();

		news.setNewsTitle(key);
		pageBean = newsService.getNewsWithPara(news, pageBean);

		request.setAttribute("key", key);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("navIndex", "1");
		return "website/znss";
	}

	@RequestMapping(value = "/newsDetail", method = RequestMethod.GET)
	public String newsDetail(HttpServletRequest request, @RequestParam("sno") String sno) {
		List<Map<String, Object>> newsList = newsService.getNewsWithId(sno);
		if (newsList.size() > 0) {
			String tempAuthor = newsList.get(0).get("newsAuthor").toString();
			String tempCompany = newsList.get(0).get("newsCompany").toString();
			if (!tempCompany.equals("")) {
				tempAuthor = tempAuthor + " 【单位：" + tempCompany + "】";
				newsList.get(0).put("newsAuthor", tempAuthor);
			}
			request.setAttribute("news", newsList.get(0));

			newsService.addAccesssCount(sno);
		}
		// 增加浏览次数
		return "website/detail";
	}

	// 机构概括单独页面
	@RequestMapping(value = "website/netDescription", method = RequestMethod.GET)
	public String netDescription(HttpServletRequest request, PageBean pageBean, News news) {
		// 机构概括
		pageBean.setCurrentPage(1);
		news.setNewsType("7");
		pageBean.setPageSize(1);
		List<Map<String, Object>> jggkList = newsService.getNewsWithPara(news, pageBean).getDispList();
		if (jggkList != null && jggkList.size() > 0) {
			request.setAttribute("commonNews", jggkList.get(0));
		}
		String navIndex = request.getParameter("navIndex");
		if (navIndex == null || navIndex.equals("")) {
			navIndex = "1";
		}
		request.setAttribute("navIndex", navIndex);
		return "website/description";
	}
}
