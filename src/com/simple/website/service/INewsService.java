package com.simple.website.service;

import java.util.List;
import java.util.Map;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.website.po.News;
import com.simple.website.po.PageBean;


public interface INewsService 
{
	/**
	 * 添加新闻
	 * @param news
	 * @return
	 */
	boolean addNews(News news);
	/**
	 * 更新新闻
	 * @param news
	 * @return
	 */
	boolean updateNews(News news);
	/**
	 * 删除新闻
	 * @param newsId
	 * @return
	 */
	boolean deleteNews(String newsId);
	
	/**
	 * 根据ID获取新闻
	 * @param newsId
	 * @return
	 */
	List<Map<String,Object>> getNewsWithId(String newsId);
	
	/**
	 * 根据ID获取新闻
	 * @param newsId
	 * @return
	 */
	News getNewsWithIdEx(String newsId);
	
	/**
	 * 查找新闻
	 * @param newsType 新闻类型 null 不包含该选择项
	 * @param newsIsDelete 新闻是否已经删除 1 删除 0 未删除 null 不包含该选择项
	 * @param newsIsChecked 新闻时候已经审核 1 审核 0 未审核 null 不包含该选择项
	 * @param newsIsTop 新闻是否置顶 1 置顶 0 未置顶 null 不包含该选择项
	 * @param newsIsLinkTitle 新闻是否是链接新闻 1 是 0 否 null 不包含该选择项
	 * @param newsIsIPLimit 新闻是否是IP限制新闻 1 是 0 否 null 不包含该选择项
	 * @param newsIsLogin 新闻是否是登陆限制新闻 1 是 0 否 null 不包含该选择项
	 * @param newsIsImageNews 新闻是否是图片新闻 1 是 0 否 null 不包含该选择项
	 * @param publishStartTime 新闻发布开始时间 null 不包含该选择项 时间格式yyyyMMddHHmmss
	 * @param publishEndTime 新闻发布结束时间 null 不包含该选择项 时间格式yyyyMMddHHmmss
	 * @return
	 */
	PageBean getNewsWithPara(News news,PageBean pageBean);
	
	/**
	 * 根据newsList获得一系列的PageBean
	 * @param newsList
	 * @param pageBean
	 * @return
	 */
	PageBean getNewsWithParaList(List<News>newsList, PageBean pageBean);
	
	/**
	 * 根据newsList获得一系列的PageBean
	 * @param newsList
	 * @param pageBean
	 * @return
	 */
	PageBean getNewsWithParaList(PageBean pageBean,List<Integer>newsList,String newsIsImageNews);
	
	/**
	 * 根据newsType获得PageBean
	 * @param newsType
	 * @param pageBean
	 * @return
	 */
	PageBean getNewsWithParaList(PageBean pageBean,String newsType,String newsIsImageNews);
	/**
	 * 根据newsList获得一系列的PageBean
	 * @param newsList
	 * @param pageBean
	 * @return
	 */
	PageBean getNewsWithParaList(PageBean pageBean,List<Integer>newsList);
	
	/**
	 * 查找所有新闻动态
	 * @param news 新闻
	 * @return
	 */
	PageBean getAllNewsWithPara(News news,PageBean pageBean);
	
	/**
	 * 查找所有的会议纪要
	 * @param news 新闻
	 * @return
	 */
	PageBean getAllMeetingWithPara(News news,PageBean pageBean);
	
	/**
	 * 查找新闻
	 * @param newsType 新闻类型 null 不包含该选择项
	 * @param newsIsDelete 新闻是否已经删除 1 删除 0 未删除 null 不包含该选择项
	 * @param newsIsChecked 新闻时候已经审核 1 审核 0 未审核 null 不包含该选择项
	 * @param newsIsTop 新闻是否置顶 true 置顶 1 未置顶 0 不包含该选择项
	 * @param newsIsLinkTitle 新闻是否是链接新闻 1 是 0 否 null 不包含该选择项
	 * @param newsIsIPLimit 新闻是否是IP限制新闻 1 是 0 否 null 不包含该选择项
	 * @param newsIsLogin 新闻是否是登陆限制新闻 1 是 0 否 null 不包含该选择项
	 * @param newsIsImageNews 新闻是否是图片新闻 1 是 0 否 null 不包含该选择项
	 * @param publishStartTime 新闻发布开始时间 null 不包含该选择项 时间格式yyyyMMddHHmmss
	 * @param publishEndTime 新闻发布结束时间 null 不包含该选择项 时间格式yyyyMMddHHmmss
	 * @return
	 */
	Map<String, Object> getNewsWithPara(
			DataGridModel dgm,
			News news);
	
	/**
	 * 获得内部通知或者会议纪要
	 */
	Map<String, Object> getNewsWithType(DataGridModel dgm,String newsType,News news);
	
	/**
	 * 添加访问次数
	 * @param newsId
	 * @return
	 */
	boolean addAccesssCount(String newsId);
	/**
	 * 更改审核状态 true 已经审核 false 未审核
	 * @param newsId
	 * @param isChecked
	 * @return
	 */
	boolean setCheckedStatus(String newsId,String isChecked);
	
	/*
	 * 根据机构ID获得所属机构下所有用户信息
	 */
	List<Map<String,Object>> getUserListFromOrgId(String orgId);
	
	/**
	 * 清除字符串里所有html标签
	 */
	String removeAllHtmlTag(String content);
	//根据新闻类型查询出前N条数据
	
}
