package com.simple.website.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DBUtil;
import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.org.dao.OrgDao;
import com.simple.bsp.org.po.Org;
import com.simple.bsp.security.po.PubUsers;
import com.simple.website.po.News;
import com.simple.website.po.PageBean;
import com.simple.website.service.INewsService;

@Service("newsService")
public class NewsServiceImpl implements INewsService {

	@Autowired
	private DBUtil util;

	@Autowired
	private OrgDao orgDao;

	@Override
	public boolean addNews(News news) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			news.setNewsId(NextID.getNextID("news"));

			PubUsers user = (PubUsers) principal;

			SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMddHHmmss");
			String strTime = dateTime.format(new Date());

			if (news.getNewsTitle() == null)
				return false;

			if (news.getNewsSubTitle() == null)
				news.setNewsSubTitle("");

			if (news.getNewsContent() == null)
				return false;

			if (news.getNewsType() == null)
				return false;

			if (news.getNewsAuthor() == null || news.getNewsAuthor().equals("")) {
				Org userOrg = orgDao.getOrgByOrgId(user.getUserOrg());
				news.setNewsAuthor("【发布人：" + user.getUserName() + "  来自：" + userOrg.getOrgName() + "】");
			}

			if (news.getNewsPublishTime() == null || news.getNewsPublishTime().equals("")) {
				SimpleDateFormat dateTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String strTime1 = dateTime1.format(new Date());
				news.setNewsPublishTime(strTime1);
			}

			if (news.getNewsCompany() == null)
				news.setNewsCompany("");

			news.setNewsModifier(user.getUserAccount());
			news.setNewsModifyTime(strTime);

			if (news.getNewsDeleter() == null)
				news.setNewsDeleter("");

			if (news.getNewsDeleteTime() == null)
				news.setNewsDeleteTime("");

			news.setNewsIsDelete("0");

			news.setNewsTotalAccessCount("0");

			if (news.getNewsIsChecked() == null)
				news.setNewsIsChecked("0");

			if (news.getNewsChecker() == null)
				news.setNewsChecker("");

			if (news.getNewsCheckedTime() == null)
				news.setNewsCheckedTime("");

			if (news.getNewsIsTop() == null)
				news.setNewsIsTop("0");

			if (news.getNewsIsTop() == "1") {
				String strSQL = "update sdu_news set news_is_top='0' where news_is_top='1'";
				util.editObject(strSQL, new Object[] {});
			}

			if (news.getNewsIsLinkTitle() == null)
				news.setNewsIsLinkTitle("0");

			if (news.getNewsLinkUrl() == null)
				if (news.getNewsIsLinkTitle().equals("1"))
					return false;
				else
					news.setNewsLinkUrl("");

			if (news.getNewsIsAccessory() == null)
				news.setNewsIsAccessory("0");

			if (news.getNewsAccessoryUrl() == null)
				if (news.getNewsIsAccessory().equals("1"))
					return false;
				else
					news.setNewsAccessoryUrl("");

			if (news.getNewsIsIPLimit() == null)
				news.setNewsIsIPLimit("0");

			if (news.getNewsIsLogin() == null)
				news.setNewsIsLogin("0");

			if (news.getNewsIsImageNews() == null)
				news.setNewsIsImageNews("0");

			if (news.getNewsImageUrl() == null)
				if (news.getNewsIsImageNews().equals("1"))
					return false;
				else
					news.setNewsImageUrl("");

			if (news.getNewsImageUrl1() == null)
				news.setNewsImageUrl1("");
			if (news.getNewsImageUrl2() == null)
				news.setNewsImageUrl2("");
			if (news.getNewsImageUrl3() == null)
				news.setNewsImageUrl3("");
			if (news.getNewsImageUrl4() == null)
				news.setNewsImageUrl4("");
			if (news.getNewsImageUrl5() == null)
				news.setNewsImageUrl5("");
			if (news.getNewsImageUrl6() == null)
				news.setNewsImageUrl6("");
			if (news.getNewsImageUrl7() == null)
				news.setNewsImageUrl7("");
			if (news.getNewsImageUrl8() == null)
				news.setNewsImageUrl8("");
			if (news.getNewsImageUrl9() == null)
				news.setNewsImageUrl9("");

			if (news.getNewsFilesUrl1() == null)
				news.setNewsFilesUrl1("");
			if (news.getNewsFilesUrl2() == null)
				news.setNewsFilesUrl2("");
			if (news.getNewsFilesUrl3() == null)
				news.setNewsFilesUrl3("");
			if (news.getNewsFilesUrl4() == null)
				news.setNewsFilesUrl4("");
			if (news.getNewsFilesUrl5() == null)
				news.setNewsFilesUrl5("");
			if (news.getNewsFilesUrl6() == null)
				news.setNewsFilesUrl6("");
			if (news.getNewsFilesUrl7() == null)
				news.setNewsFilesUrl7("");
			if (news.getNewsFilesUrl8() == null)
				news.setNewsFilesUrl8("");
			if (news.getNewsFilesUrl9() == null)
				news.setNewsFilesUrl9("");

			StringBuffer sqlSb = new StringBuffer("insert into sdu_news (");
			sqlSb.append(" news_id, ");
			sqlSb.append(" news_title, ");
			sqlSb.append(" news_sub_title, ");
			sqlSb.append(" news_content, ");
			sqlSb.append(" news_type, ");
			sqlSb.append(" news_author, ");
			sqlSb.append(" news_publish_time, ");
			sqlSb.append(" news_company, ");
			sqlSb.append(" news_modifier, ");
			sqlSb.append(" news_modify_time, ");
			sqlSb.append(" news_deleter, ");
			sqlSb.append(" news_delete_time, ");
			sqlSb.append(" news_is_delete, ");
			sqlSb.append(" news_total_access_count, ");
			sqlSb.append(" news_is_checked, ");
			sqlSb.append(" news_checker, ");
			sqlSb.append(" news_checked_time, ");
			sqlSb.append(" news_is_top, ");
			sqlSb.append(" news_is_link_title, ");
			sqlSb.append(" news_link_url, ");
			sqlSb.append(" news_is_accessory, ");
			sqlSb.append(" news_accessory_url, ");
			sqlSb.append(" news_is_ip_limit, ");
			sqlSb.append(" news_is_login, ");
			sqlSb.append(" news_is_image_news, ");
			sqlSb.append(" news_image_url, ");
			sqlSb.append(" news_image_url1, ");
			sqlSb.append(" news_image_url2, ");
			sqlSb.append(" news_image_url3, ");
			sqlSb.append(" news_image_url4, ");
			sqlSb.append(" news_image_url5, ");
			sqlSb.append(" news_image_url6, ");
			sqlSb.append(" news_image_url7, ");
			sqlSb.append(" news_image_url8, ");
			sqlSb.append(" news_image_url9, ");
			sqlSb.append(" news_files_url1, ");
			sqlSb.append(" news_files_url2, ");
			sqlSb.append(" news_files_url3, ");
			sqlSb.append(" news_files_url4, ");
			sqlSb.append(" news_files_url5, ");
			sqlSb.append(" news_files_url6, ");
			sqlSb.append(" news_files_url7, ");
			sqlSb.append(" news_files_url8, ");
			sqlSb.append(" news_files_url9 ");
			sqlSb.append(" ) values ( ");
			sqlSb.append(" :newsId, ");
			sqlSb.append(" :newsTitle, ");
			sqlSb.append(" :newsSubTitle, ");
			sqlSb.append(" :newsContent, ");
			sqlSb.append(" :newsType, ");
			sqlSb.append(" :newsAuthor, ");
			sqlSb.append(" :newsPublishTime, ");
			sqlSb.append(" :newsCompany, ");
			sqlSb.append(" :newsModifier, ");
			sqlSb.append(" :newsModifyTime, ");
			sqlSb.append(" :newsDeleter, ");
			sqlSb.append(" :newsDeleteTime, ");
			sqlSb.append(" :newsIsDelete, ");
			sqlSb.append(" :newsTotalAccessCount, ");
			sqlSb.append(" :newsIsChecked, ");
			sqlSb.append(" :newsChecker, ");
			sqlSb.append(" :newsCheckedTime, ");
			sqlSb.append(" :newsIsTop, ");
			sqlSb.append(" :newsIsLinkTitle, ");
			sqlSb.append(" :newsLinkUrl, ");
			sqlSb.append(" :newsIsAccessory, ");
			sqlSb.append(" :newsAccessoryUrl, ");
			sqlSb.append(" :newsIsIPLimit, ");
			sqlSb.append(" :newsIsLogin, ");
			sqlSb.append(" :newsIsImageNews, ");
			sqlSb.append(" :newsImageUrl, ");
			sqlSb.append(" :newsImageUrl1, ");
			sqlSb.append(" :newsImageUrl2, ");
			sqlSb.append(" :newsImageUrl3, ");
			sqlSb.append(" :newsImageUrl4, ");
			sqlSb.append(" :newsImageUrl5, ");
			sqlSb.append(" :newsImageUrl6, ");
			sqlSb.append(" :newsImageUrl7, ");
			sqlSb.append(" :newsImageUrl8, ");
			sqlSb.append(" :newsImageUrl9, ");
			sqlSb.append(" :newsFilesUrl1, ");
			sqlSb.append(" :newsFilesUrl2, ");
			sqlSb.append(" :newsFilesUrl3, ");
			sqlSb.append(" :newsFilesUrl4, ");
			sqlSb.append(" :newsFilesUrl5, ");
			sqlSb.append(" :newsFilesUrl6, ");
			sqlSb.append(" :newsFilesUrl7, ");
			sqlSb.append(" :newsFilesUrl8, ");
			sqlSb.append(" :newsFilesUrl9 ");
			sqlSb.append(" )");

			if (util.editObject(sqlSb.toString(), news) > 0)
				return true;

		}
		return false;
	}

	@Override
	public boolean updateNews(News news) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers) principal;

			SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMddHHmmss");
			String strTime = dateTime.format(new Date());

			List<Map<String, Object>> oldNews = getNewsWithId(news.getNewsId());
			if (oldNews == null || oldNews.size() < 1)
				return false;

			if (news.getNewsTitle() == null)
				news.setNewsTitle(oldNews.get(0).get("newsTitle").toString());

			if (news.getNewsSubTitle() == null)
				news.setNewsSubTitle(oldNews.get(0).get("newsSubTitle").toString());

			if (news.getNewsContent() == null)
				news.setNewsContent(oldNews.get(0).get("newsContent").toString());

			if (news.getNewsType() == null)
				news.setNewsType(oldNews.get(0).get("newsType").toString());

			if (news.getNewsAuthor() == null)
				news.setNewsAuthor(oldNews.get(0).get("newsAuthor").toString());

			if (news.getNewsPublishTime() == null || news.getNewsPublishTime().equals("")) {
				SimpleDateFormat dateTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String strTime1 = dateTime1.format(new Date());
				news.setNewsPublishTime(strTime1);
			}

			if (news.getNewsCompany() == null)
				news.setNewsCompany(oldNews.get(0).get("newsCompany").toString());

			// news.setNewsModifier(user.getUserAccount());
			// news.setNewsModifyTime(strTime);

			if (news.getNewsDeleter() == null)
				news.setNewsDeleter(oldNews.get(0).get("newsDeleter").toString());

			if (news.getNewsDeleteTime() == null)
				news.setNewsDeleteTime(oldNews.get(0).get("newsDeleteTime").toString());

			if (news.getNewsIsDelete() == null)
				news.setNewsIsDelete(oldNews.get(0).get("newsIsDelete").toString());

			if (news.getNewsTotalAccessCount() == null)
				news.setNewsTotalAccessCount(oldNews.get(0).get("newsTotalAccessCount").toString());

			if (news.getNewsIsChecked() == null)
				news.setNewsIsChecked(oldNews.get(0).get("newsIsChecked").toString());

			if (news.getNewsChecker() == null)
				news.setNewsChecker(oldNews.get(0).get("newsChecker").toString());

			if (news.getNewsCheckedTime() == null)
				news.setNewsCheckedTime(oldNews.get(0).get("newsCheckedTime").toString());

			if (news.getNewsIsTop() == null) {
				if (news.getNewsIsTop() == "1") {
					String strSQL = "update sdu_news set news_is_top='0' where news_is_top='1'";
					util.editObject(strSQL, new Object[] {});
				}
				news.setNewsIsTop(oldNews.get(0).get("newsIsTop").toString());
			}

			if (news.getNewsIsLinkTitle() == null)
				news.setNewsIsLinkTitle(oldNews.get(0).get("newsIsLinkTitle").toString());

			if (news.getNewsLinkUrl() == null)
				if (news.getNewsIsLinkTitle().equals("1"))
					return false;
				else
					news.setNewsLinkUrl(oldNews.get(0).get("newsLinkUrl").toString());

			if (news.getNewsIsAccessory() == null)
				news.setNewsIsAccessory(oldNews.get(0).get("newsIsAccessory").toString());

			if (news.getNewsAccessoryUrl() == null)
				if (news.getNewsIsAccessory().equals("1"))
					return false;
				else
					news.setNewsAccessoryUrl(oldNews.get(0).get("newsAccessoryUrl").toString());

			if (news.getNewsIsIPLimit() == null)
				news.setNewsIsIPLimit(oldNews.get(0).get("newsIsIPLimit").toString());

			if (news.getNewsIsLogin() == null)
				news.setNewsIsLogin(oldNews.get(0).get("newsIsLogin").toString());

			if (news.getNewsIsImageNews() == null)
				news.setNewsIsImageNews(oldNews.get(0).get("newsIsImageNews").toString());

			if (news.getNewsImageUrl() == null)
				if (news.getNewsIsImageNews().equals("1"))
					return false;
				else
					news.setNewsImageUrl(oldNews.get(0).get("newsImageUrl").toString());

			if (news.getNewsImageUrl1() == null)
				news.setNewsImageUrl1(oldNews.get(0).get("newsImageUrl1").toString());
			if (news.getNewsImageUrl2() == null)
				news.setNewsImageUrl2(oldNews.get(0).get("newsImageUrl2").toString());
			if (news.getNewsImageUrl3() == null)
				news.setNewsImageUrl3(oldNews.get(0).get("newsImageUrl3").toString());
			if (news.getNewsImageUrl4() == null)
				news.setNewsImageUrl4(oldNews.get(0).get("newsImageUrl4").toString());
			if (news.getNewsImageUrl5() == null)
				news.setNewsImageUrl5(oldNews.get(0).get("newsImageUrl5").toString());
			if (news.getNewsImageUrl6() == null)
				news.setNewsImageUrl6(oldNews.get(0).get("newsImageUrl6").toString());
			if (news.getNewsImageUrl7() == null)
				news.setNewsImageUrl7(oldNews.get(0).get("newsImageUrl7").toString());
			if (news.getNewsImageUrl8() == null)
				news.setNewsImageUrl8(oldNews.get(0).get("newsImageUrl8").toString());
			if (news.getNewsImageUrl9() == null)
				news.setNewsImageUrl9(oldNews.get(0).get("newsImageUrl9").toString());

			if (news.getNewsFilesUrl1() == null)
				news.setNewsFilesUrl1(oldNews.get(0).get("newsFilesUrl1").toString());
			if (news.getNewsFilesUrl2() == null)
				news.setNewsFilesUrl2(oldNews.get(0).get("newsFilesUrl2").toString());
			if (news.getNewsFilesUrl3() == null)
				news.setNewsFilesUrl3(oldNews.get(0).get("newsFilesUrl3").toString());
			if (news.getNewsFilesUrl4() == null)
				news.setNewsFilesUrl4(oldNews.get(0).get("newsFilesUrl4").toString());
			if (news.getNewsFilesUrl5() == null)
				news.setNewsFilesUrl5(oldNews.get(0).get("newsFilesUrl5").toString());
			if (news.getNewsFilesUrl6() == null)
				news.setNewsFilesUrl6(oldNews.get(0).get("newsFilesUrl6").toString());
			if (news.getNewsFilesUrl7() == null)
				news.setNewsFilesUrl7(oldNews.get(0).get("newsFilesUrl7").toString());
			if (news.getNewsFilesUrl8() == null)
				news.setNewsFilesUrl8(oldNews.get(0).get("newsFilesUrl8").toString());
			if (news.getNewsFilesUrl9() == null)
				news.setNewsFilesUrl9(oldNews.get(0).get("newsFilesUrl9").toString());

			StringBuffer sqlSb = new StringBuffer("update sdu_news set ");
			sqlSb.append(" news_title = :newsTitle, ");
			sqlSb.append(" news_sub_title = :newsSubTitle, ");
			sqlSb.append(" news_content = :newsContent, ");
			sqlSb.append(" news_type = :newsType, ");
			sqlSb.append(" news_author = :newsAuthor, ");
			sqlSb.append(" news_publish_time = :newsPublishTime, ");
			sqlSb.append(" news_company = :newsCompany, ");
			sqlSb.append(" news_deleter = :newsDeleter, ");
			sqlSb.append(" news_delete_time = :newsDeleteTime, ");
			sqlSb.append(" news_is_delete = :newsIsDelete, ");
			sqlSb.append(" news_total_access_count = :newsTotalAccessCount, ");
			sqlSb.append(" news_is_checked = :newsIsChecked, ");
			sqlSb.append(" news_checker = :newsChecker, ");
			sqlSb.append(" news_checked_time = :newsCheckedTime, ");
			sqlSb.append(" news_is_top = :newsIsTop, ");
			sqlSb.append(" news_is_link_title = :newsIsLinkTitle, ");
			sqlSb.append(" news_link_url = :newsLinkUrl, ");
			sqlSb.append(" news_is_accessory = :newsIsAccessory, ");
			sqlSb.append(" news_accessory_url = :newsAccessoryUrl, ");
			sqlSb.append(" news_is_ip_limit = :newsIsIPLimit, ");
			sqlSb.append(" news_is_login = :newsIsLogin, ");
			sqlSb.append(" news_is_image_news = :newsIsImageNews, ");
			sqlSb.append(" news_image_url = :newsImageUrl, ");
			sqlSb.append(" news_image_url1 = :newsImageUrl1, ");
			sqlSb.append(" news_image_url2 = :newsImageUrl2, ");
			sqlSb.append(" news_image_url3 = :newsImageUrl3, ");
			sqlSb.append(" news_image_url4 = :newsImageUrl4, ");
			sqlSb.append(" news_image_url5 = :newsImageUrl5, ");
			sqlSb.append(" news_image_url6 = :newsImageUrl6, ");
			sqlSb.append(" news_image_url7 = :newsImageUrl7, ");
			sqlSb.append(" news_image_url8 = :newsImageUrl8, ");
			sqlSb.append(" news_image_url9 = :newsImageUrl9, ");
			sqlSb.append(" news_files_url1 = :newsFilesUrl1, ");
			sqlSb.append(" news_files_url2 = :newsFilesUrl2, ");
			sqlSb.append(" news_files_url3 = :newsFilesUrl3, ");
			sqlSb.append(" news_files_url4 = :newsFilesUrl4, ");
			sqlSb.append(" news_files_url5 = :newsFilesUrl5, ");
			sqlSb.append(" news_files_url6 = :newsFilesUrl6, ");
			sqlSb.append(" news_files_url7 = :newsFilesUrl7, ");
			sqlSb.append(" news_files_url8 = :newsFilesUrl8, ");
			sqlSb.append(" news_files_url9 = :newsFilesUrl9 ");
			sqlSb.append(" where news_id = :newsId");
			if (util.editObject(sqlSb.toString(), news) > 0)
				return true;
		}
		return false;
	}

	@Override
	public boolean deleteNews(String newsId) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers) principal;
			SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMddHHmmss");
			String strTime = dateTime.format(new Date());

			String sql = "update sdu_news set  news_is_delete='1', " + " news_deleter = '" + user.getUserAccount()
					+ "'," + " news_delete_time = '" + strTime + "' " + " where news_id = '" + newsId + "'";
			if (util.editObject(sql, new Object[] {}) > 0)
				return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> getNewsWithId(String newsId) {
		// TODO Auto-generated method stub
		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" news_id as newsId, ");
		sqlSb.append(" news_title as newsTitle, ");
		sqlSb.append(" news_sub_title as newsSubTitle, ");
		sqlSb.append(" news_content as newsContent, ");
		sqlSb.append(" news_type as newsType, ");
		sqlSb.append(" news_author as newsAuthor, ");
		sqlSb.append(" news_publish_time as newsPublishTime, ");
		sqlSb.append(" news_company as newsCompany, ");
		sqlSb.append(" news_modifier as newsModifier, ");
		sqlSb.append(" news_modify_time as newsModifyTime, ");
		sqlSb.append(" news_deleter as newsDeleter, ");
		sqlSb.append(" news_delete_time as newsDeleteTime, ");
		sqlSb.append(" news_is_delete as newsIsDelete, ");
		sqlSb.append(" news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" news_is_checked as newsIsChecked, ");
		sqlSb.append(" news_checker as newsChecker, ");
		sqlSb.append(" news_checked_time as newsCheckedTime, ");
		sqlSb.append(" news_is_top as newsIsTop, ");
		sqlSb.append(" news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" news_link_url as newsLinkUrl, ");
		sqlSb.append(" news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" news_is_login as newsIsLogin, ");
		sqlSb.append(" news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" news_image_url as newsImageUrl, ");
		sqlSb.append(" news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news ");
		sqlSb.append(" where news_id ='" + newsId + "'");
		return util.getMapList(sqlSb.toString(), new Object[] {});
	}

	@Override
	public boolean addAccesssCount(String newsId) {
		// TODO Auto-generated method stub
		String sql = "select news_total_access_count from sdu_news where news_id='" + newsId + "'";
		List<Map<String, Object>> newsList = util.getMapList(sql, new Object[] {});
		if (newsList != null && newsList.size() > 0) {
			String account = newsList.get(0).get("news_total_access_count").toString();
			int nAccount = Integer.parseInt(account);
			nAccount++;
			sql = "update sdu_news set news_total_access_count='" + nAccount + "' where news_id='" + newsId + "'";
			if (util.updateObject(sql) > 0)
				return true;
		}
		return false;
	}

	@Override
	public boolean setCheckedStatus(String newsId, String isChecked) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers) principal;
			SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMddHHmmss");
			String strTime = dateTime.format(new Date());

			StringBuffer sqlSb = new StringBuffer("update sdu_news set ");
			sqlSb.append(" news_is_checked = '" + isChecked + "', ");
			sqlSb.append(" news_checker = '" + user.getUserAccount() + "', ");
			sqlSb.append(" news_checked_time = '" + strTime + "' ");
			sqlSb.append(" where news_id='" + newsId + "'");
			if (util.updateObject(sqlSb.toString()) > 0)
				return true;
		}

		return false;
	}

	@Override
	public Map<String, Object> getNewsWithPara(DataGridModel dgm, News news) {
		// TODO Auto-generated method stub
		// 查询结果Map
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers) principal;
			String strNewType = "select news_type_code from user_news_type where user_id='" + user.getUserId() + "'";

			Map<String, Object> result = new HashMap<String, Object>(2);

			StringBuffer sumSql = new StringBuffer();
			sumSql.append("select count(1) from sdu_news a,pub_sys_param b where 1=1 and a.news_type=b.param_value ");

			StringBuffer sqlSb = new StringBuffer("select ");
			sqlSb.append(" a.news_id as newsId, ");
			sqlSb.append(" a.news_title as newsTitle, ");
			sqlSb.append(" a.news_sub_title as newsSubTitle, ");
			// sqlSb.append( " a.news_content as newsContent, ");
			sqlSb.append(" b.param_value as newsType, ");
			sqlSb.append(" b.param_name as newsTypeName, ");
			sqlSb.append(" a.news_author as newsAuthor, ");
			sqlSb.append(" a.news_publish_time as newsPublishTime, ");
			sqlSb.append(" a.news_company as newsCompany, ");
			// sqlSb.append( " a.news_modifier as newsModifier, ");
			// sqlSb.append( " a.news_modify_time as newsModifyTime, ");
			sqlSb.append(" a.news_deleter as newsDeleter, ");
			// sqlSb.append( " a.news_delete_time as newsDeleteTime, ");
			// sqlSb.append( " a.news_is_delete as newsIsDelete, ");
			// sqlSb.append( " a.news_total_access_count as
			// newsTotalAccessCount, ");
			sqlSb.append(" a.news_is_checked as newsIsChecked, ");
			// sqlSb.append( " a.news_checker as newsChecker, ");
			// sqlSb.append( " a.news_checked_time as newsCheckedTime, ");
			// sqlSb.append( " a.news_is_top as newsIsTop, ");
			sqlSb.append(" a.news_is_link_title as newsIsLinkTitle, ");
			sqlSb.append(" a.news_link_url as newsLinkUrl, ");
			sqlSb.append(" a.news_is_accessory as newsIsAccessory, ");
			sqlSb.append(" a.news_accessory_url as newsAccessoryUrl, ");
			// sqlSb.append( " a.news_is_ip_limit as newsIsIPLimit, ");
			// sqlSb.append( " a.news_is_login as newsIsLogin, ");
			sqlSb.append(" a.news_is_image_news as newsIsImageNews ");
			/*
			 * sqlSb.append( " a.news_image_url as newsImageUrl, ");
			 * sqlSb.append( " a.news_image_url1 as newsImageUrl1, ");
			 * sqlSb.append( " a.news_image_url2 as newsImageUrl2, ");
			 * sqlSb.append( " a.news_image_url3 as newsImageUrl3, ");
			 * sqlSb.append( " a.news_image_url4 as newsImageUrl4, ");
			 * sqlSb.append( " a.news_image_url5 as newsImageUrl5, ");
			 * sqlSb.append( " a.news_image_url6 as newsImageUrl6, ");
			 * sqlSb.append( " a.news_image_url7 as newsImageUrl7, ");
			 * sqlSb.append( " a.news_image_url8 as newsImageUrl8, ");
			 * sqlSb.append( " a.news_image_url9 as newsImageUrl9, ");
			 * sqlSb.append( " a.news_files_url1 as newsFilesUrl1, ");
			 * sqlSb.append( " a.news_files_url2 as newsFilesUrl2, ");
			 * sqlSb.append( " a.news_files_url3 as newsFilesUrl3, ");
			 * sqlSb.append( " a.news_files_url4 as newsFilesUrl4, ");
			 * sqlSb.append( " a.news_files_url5 as newsFilesUrl5, ");
			 * sqlSb.append( " a.news_files_url6 as newsFilesUrl6, ");
			 * sqlSb.append( " a.news_files_url7 as newsFilesUrl7, ");
			 * sqlSb.append( " a.news_files_url8 as newsFilesUrl8, ");
			 * sqlSb.append( " a.news_files_url9 as newsFilesUrl9 ");
			 */
			sqlSb.append(" from sdu_news a, pub_sys_param b ");
			sqlSb.append(" where b.param_value = a.news_type");

			if (news.getNewsTitle() != null && !news.getNewsTitle().equals("")) {
				sqlSb.append(" and a.news_title like '%" + news.getNewsTitle() + "%'");
				sumSql.append(" and news_title like '%" + news.getNewsTitle() + "%'");
			}

			if (news.getNewsType() != null && !news.getNewsType().equals("")) {
				sqlSb.append(" and a.news_type='" + news.getNewsType() + "'");
				sumSql.append(" and news_type='" + news.getNewsType() + "'");
			}

			if (news.getNewsModifier() != null && !news.getNewsModifier().equals("")) {
				sqlSb.append(" and a.news_modifier = '" + news.getNewsModifier() + "'");
				sumSql.append(" and news_modifier = '" + news.getNewsModifier() + "'");
			}

			news.setNewsIsDelete("0");
			sqlSb.append(" and a.news_is_delete='0'");
			sumSql.append(" and news_is_delete='0'");

			if (news.getNewsIsChecked() != null) {
				if (!news.getNewsIsChecked().equals("3")) {
					sqlSb.append(" and a.news_is_checked='" + news.getNewsIsChecked() + "'");
					sumSql.append(" and news_is_checked='" + news.getNewsIsChecked() + "'");
				}
			} else {
				sqlSb.append(" and a.news_is_checked!='1'");
				sumSql.append(" and news_is_checked!='1'");
			}

			if (news.getNewsIsTop() != null && !news.getNewsIsTop().equals("")) {
				sqlSb.append(" and a.news_is_top='" + news.getNewsIsTop() + "'");
				sumSql.append(" and news_is_top='" + news.getNewsIsTop() + "'");
			}
			if (news.getNewsIsLinkTitle() != null && !news.getNewsIsLinkTitle().equals("")) {
				sqlSb.append(" and a.news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
				sumSql.append(" and news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
			}

			if (news.getNewsIsAccessory() != null && !news.getNewsIsAccessory().equals("")) {
				sqlSb.append(" and a.news_is_accessory='" + news.getNewsIsAccessory() + "'");
				sumSql.append(" and news_is_accessory='" + news.getNewsIsAccessory() + "'");
			}

			if (news.getNewsIsIPLimit() != null && !news.getNewsIsIPLimit().equals("")) {
				sqlSb.append(" and a.news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
				sumSql.append(" and news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
			}
			if (news.getNewsIsLogin() != null && !news.getNewsIsLogin().equals("")) {
				sqlSb.append(" and a.news_is_login='" + news.getNewsIsLogin() + "'");
				sumSql.append(" and news_is_login='" + news.getNewsIsLogin() + "'");
			}
			if (news.getNewsIsImageNews() != null && !news.getNewsIsImageNews().equals("")) {
				sqlSb.append(" and a.news_is_image_news='" + news.getNewsIsImageNews() + "'");
				sumSql.append(" and news_is_image_news='" + news.getNewsIsImageNews() + "'");
			}
			if (news.getPublishStartTime() != null && !news.getPublishStartTime().equals("")) {
				sqlSb.append(
						" and UNIX_TIMESTAMP(str_to_date(a.news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s')) ");
				sumSql.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			}
			if (news.getPublishEndTime() != null && !news.getPublishEndTime().equals("")) {
				sqlSb.append(
						" and UNIX_TIMESTAMP(str_to_date(a.news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
				sumSql.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			}

			// 选择机构
			String orgDesc = "select id from pub_org_desc where org_id in (select user_org from pub_users where user_id='"
					+ user.getUserId() + "')";
			List<Map<String, Object>> listOrgDesc = util.getMapList(orgDesc, new Object[] {});
			if (listOrgDesc != null && listOrgDesc.size() > 0) {
				orgDesc = listOrgDesc.get(0).get("id").toString();
				sqlSb.append(" and a.news_modifier in (select d.user_account from pub_users d " + "where d.user_org in "
						+ "(select e.org_id from pub_org_desc e where e.id like '" + orgDesc + "%'))");
				sumSql.append(" and news_modifier in (select user_account from pub_users " + "where user_org in "
						+ "(select org_id from pub_org_desc where id like '" + orgDesc + "%'))");
			}

//			sqlSb.append(" and a.news_type in (select c.news_type_code from user_news_type c where c.user_id='"
//					+ user.getUserId() + "')");
//			sumSql.append(" and news_type in (select news_type_code from user_news_type where user_id='"
//					+ user.getUserId() + "')");

			// 组装排序规则
			String orderString = "";
			if (StringUtils.isNotBlank(dgm.getSort())) {
				orderString = " order by a.news_publish_time desc ";
			} else {
				orderString = " order by news_publish_time desc ";
			}
			Map<String, Object> params = new HashMap<String, Object>();
			// 组装分页定义
			String pageString = " limit " + (dgm.getPage() - 1) * dgm.getRows() + ", " + dgm.getRows();

			// 绑定查询结果('total'和'rows'名称不能修改)
			result.put("total", util.getObjCount(sumSql.toString()));
			result.put("rows", util.getMapList(sqlSb.toString() + orderString + pageString, params));
			return result;
		}
		return null;
	}

	@Override
	public Map<String, Object> getNewsWithType(DataGridModel dgm, String newsType, News news) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers) principal;
			String strNewType = "select news_type_code from user_news_type where user_id='" + user.getUserId() + "'";

			Map<String, Object> result = new HashMap<String, Object>(2);

			StringBuffer sumSql = new StringBuffer();
			sumSql.append("select count(1) from sdu_news a,pub_sys_param b where 1=1 and a.news_type=b.param_value ");

			StringBuffer sqlSb = new StringBuffer("select ");
			sqlSb.append(" a.news_id as newsId, ");
			sqlSb.append(" a.news_title as newsTitle, ");
			sqlSb.append(" a.news_author as newsAuthor, ");
			sqlSb.append(" a.news_publish_time as newsPublishTime, ");
			sqlSb.append(" where b.param_value = a.news_type");

			if (news.getNewsTitle() != null && !news.getNewsTitle().equals("")) {
				sqlSb.append(" and a.news_title like '%" + news.getNewsTitle() + "%'");
				sumSql.append(" and news_title like '%" + news.getNewsTitle() + "%'");
			}

			sqlSb.append(" and a.news_is_delete='0'");
			sumSql.append(" and news_is_delete='0'");

			sqlSb.append(" and a.news_is_checked='1'");
			sumSql.append(" and news_is_checked='1'");

			if (news.getPublishStartTime() != null && !news.getPublishStartTime().equals("")) {
				sqlSb.append(
						" and UNIX_TIMESTAMP(str_to_date(a.news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s')) ");
				sumSql.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			}
			if (news.getPublishEndTime() != null && !news.getPublishEndTime().equals("")) {
				sqlSb.append(
						" and UNIX_TIMESTAMP(str_to_date(a.news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
				sumSql.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			}

			sqlSb.append(" and a.news_type='" + newsType + "' ");
			sumSql.append(" and news_type='" + newsType + "' ");

			// 组装排序规则
			String orderString = "";
			if (StringUtils.isNotBlank(dgm.getSort())) {
				orderString = " order by a.news_publish_time desc ";
			} else {
				orderString = " order by news_publish_time desc ";
			}
			Map<String, Object> params = new HashMap<String, Object>();
			// 组装分页定义
			String pageString = " limit " + (dgm.getPage() - 1) * dgm.getRows() + ", " + dgm.getRows();

			// 绑定查询结果('total'和'rows'名称不能修改)
			result.put("total", util.getObjCount(sumSql.toString()));
			result.put("rows", util.getMapList(sqlSb.toString() + orderString + pageString, params));
			return result;
		}
		return null;
	}

	@Override
	public PageBean getNewsWithPara(News news, PageBean pageBean) {
		// TODO Auto-generated method stub
		int currentSize = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();

		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from sdu_news a,pub_sys_param b where 1=1 and a.news_type=b.param_value ");

		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" a.news_id as newsId, ");
		sqlSb.append(" a.news_title as newsTitle, ");
		sqlSb.append(" a.news_sub_title as newsSubTitle, ");
		sqlSb.append(" a.news_content as newsContent, ");
		sqlSb.append(" a.news_type as newsType, ");
		sqlSb.append(" b.param_name as newsTypeName, ");
		sqlSb.append(" a.news_author as newsAuthor, ");
		sqlSb.append(" a.news_publish_time as newsPublishTime, ");
		sqlSb.append(" a.news_company as newsCompany, ");
		sqlSb.append(" a.news_modifier as newsModifier, ");
		sqlSb.append(" a.news_modify_time as newsModifyTime, ");
		sqlSb.append(" a.news_deleter as newsDeleter, ");
		sqlSb.append(" a.news_delete_time as newsDeleteTime, ");
		sqlSb.append(" a.news_is_delete as newsIsDelete, ");
		sqlSb.append(" a.news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" a.news_is_checked as newsIsChecked, ");
		sqlSb.append(" a.news_checker as newsChecker, ");
		sqlSb.append(" a.news_checked_time as newsCheckedTime, ");
		sqlSb.append(" a.news_is_top as newsIsTop, ");
		sqlSb.append(" a.news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" a.news_link_url as newsLinkUrl, ");
		sqlSb.append(" a.news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" a.news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" a.news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" a.news_is_login as newsIsLogin, ");
		sqlSb.append(" a.news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" a.news_image_url as newsImageUrl, ");
		sqlSb.append(" a.news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" a.news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" a.news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" a.news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" a.news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" a.news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" a.news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" a.news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" a.news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" a.news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" a.news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" a.news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" a.news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" a.news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" a.news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" a.news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" a.news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" a.news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news a, pub_sys_param b ");
		sqlSb.append(" where 1=1 and a.news_type=b.param_value ");

		if (news.getNewsTitle() != null && !news.getNewsTitle().equals("")) {
			sqlSb.append(" and a.news_title like '%" + news.getNewsTitle() + "%'");
			sumSql.append(" and news_title like '%" + news.getNewsTitle() + "%'");
		}

		if (news.getNewsType() != null) {
			sqlSb.append(" and a.news_type='" + news.getNewsType() + "'");
			sumSql.append(" and news_type='" + news.getNewsType() + "'");
		}
		news.setNewsIsDelete("0");
		sqlSb.append(" and a.news_is_delete='0'");
		sumSql.append(" and news_is_delete='0'");

		sqlSb.append(" and a.news_is_checked='1'");
		sumSql.append(" and news_is_checked='1'");

		if (news.getNewsIsTop() != null) {
			sqlSb.append(" and a.news_is_top='" + news.getNewsIsTop() + "'");
			sumSql.append(" and news_is_top='" + news.getNewsIsTop() + "'");
		}
		if (news.getNewsIsLinkTitle() != null) {
			sqlSb.append(" and a.news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
			sumSql.append(" and news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
		}
		if (news.getNewsIsAccessory() != null && !news.getNewsIsAccessory().equals("")) {
			sqlSb.append(" and a.news_is_accessory='" + news.getNewsIsAccessory() + "'");
			sumSql.append(" and news_is_accessory='" + news.getNewsIsAccessory() + "'");
		}
		if (news.getNewsIsIPLimit() != null) {
			sqlSb.append(" and a.news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
			sumSql.append(" and news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
		}
		if (news.getNewsIsLogin() != null) {
			sqlSb.append(" and a.news_is_login='" + news.getNewsIsLogin() + "'");
			sumSql.append(" and news_is_login='" + news.getNewsIsLogin() + "'");
		}
		if (news.getNewsIsImageNews() != null) {
			sqlSb.append(" and a.news_is_image_news='" + news.getNewsIsImageNews() + "'");
			sumSql.append(" and news_is_image_news='" + news.getNewsIsImageNews() + "'");
		}
		if (news.getPublishStartTime() != null && !news.getPublishStartTime().equals("")) {
			sqlSb.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s')) ");
			sumSql.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
		}
		if (news.getPublishEndTime() != null && !news.getPublishEndTime().equals("")) {
			sqlSb.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			sumSql.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
		}
		// 组装排序规则
		sqlSb.append(" order by news_publish_time desc ");

		sqlSb.append(" limit ");
		sqlSb.append((currentSize - 1) * pageSize);
		sqlSb.append(",");
		sqlSb.append(pageSize);

		pageBean.setTotalRows(util.getObjCount(sumSql.toString()));
		pageBean.setDispList((List<Map<String, Object>>) util.getMapList(sqlSb.toString(), new Object[] {}));
		return pageBean;
	}

	@Override
	public PageBean getNewsWithParaList(List<News> newsList, PageBean pageBean) {
		// TODO Auto-generated method stub
		int currentSize = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < newsList.size(); i++) {
			News news = newsList.get(i);
			StringBuffer sumSql = new StringBuffer();
			sumSql.append("select count(1) from sdu_news a,pub_sys_param b where 1=1 and a.news_type=b.param_value ");

			StringBuffer sqlSb = new StringBuffer("select ");
			sqlSb.append(" a.news_id as newsId, ");
			sqlSb.append(" a.news_title as newsTitle, ");
			sqlSb.append(" a.news_sub_title as newsSubTitle, ");
			sqlSb.append(" a.news_content as newsContent, ");
			sqlSb.append(" a.news_type as newsType, ");
			sqlSb.append(" b.param_name as newsTypeName, ");
			sqlSb.append(" a.news_author as newsAuthor, ");
			sqlSb.append(" a.news_publish_time as newsPublishTime, ");
			sqlSb.append(" a.news_company as newsCompany, ");
			sqlSb.append(" a.news_modifier as newsModifier, ");
			sqlSb.append(" a.news_modify_time as newsModifyTime, ");
			sqlSb.append(" a.news_deleter as newsDeleter, ");
			sqlSb.append(" a.news_delete_time as newsDeleteTime, ");
			sqlSb.append(" a.news_is_delete as newsIsDelete, ");
			sqlSb.append(" a.news_total_access_count as newsTotalAccessCount, ");
			sqlSb.append(" a.news_is_checked as newsIsChecked, ");
			sqlSb.append(" a.news_checker as newsChecker, ");
			sqlSb.append(" a.news_checked_time as newsCheckedTime, ");
			sqlSb.append(" a.news_is_top as newsIsTop, ");
			sqlSb.append(" a.news_is_link_title as newsIsLinkTitle, ");
			sqlSb.append(" a.news_link_url as newsLinkUrl, ");
			sqlSb.append(" a.news_is_accessory as newsIsAccessory, ");
			sqlSb.append(" a.news_accessory_url as newsAccessoryUrl, ");
			sqlSb.append(" a.news_is_ip_limit as newsIsIPLimit, ");
			sqlSb.append(" a.news_is_login as newsIsLogin, ");
			sqlSb.append(" a.news_is_image_news as newsIsImageNews, ");
			sqlSb.append(" a.news_image_url as newsImageUrl, ");
			sqlSb.append(" a.news_image_url1 as newsImageUrl1, ");
			sqlSb.append(" a.news_image_url2 as newsImageUrl2, ");
			sqlSb.append(" a.news_image_url3 as newsImageUrl3, ");
			sqlSb.append(" a.news_image_url4 as newsImageUrl4, ");
			sqlSb.append(" a.news_image_url5 as newsImageUrl5, ");
			sqlSb.append(" a.news_image_url6 as newsImageUrl6, ");
			sqlSb.append(" a.news_image_url7 as newsImageUrl7, ");
			sqlSb.append(" a.news_image_url8 as newsImageUrl8, ");
			sqlSb.append(" a.news_image_url9 as newsImageUrl9, ");
			sqlSb.append(" a.news_files_url1 as newsFilesUrl1, ");
			sqlSb.append(" a.news_files_url2 as newsFilesUrl2, ");
			sqlSb.append(" a.news_files_url3 as newsFilesUrl3, ");
			sqlSb.append(" a.news_files_url4 as newsFilesUrl4, ");
			sqlSb.append(" a.news_files_url5 as newsFilesUrl5, ");
			sqlSb.append(" a.news_files_url6 as newsFilesUrl6, ");
			sqlSb.append(" a.news_files_url7 as newsFilesUrl7, ");
			sqlSb.append(" a.news_files_url8 as newsFilesUrl8, ");
			sqlSb.append(" a.news_files_url9 as newsFilesUrl9 ");
			sqlSb.append(" from sdu_news a, pub_sys_param b ");
			sqlSb.append(" where 1=1 and a.news_type=b.param_value ");

			if (news.getNewsTitle() != null && !news.getNewsTitle().equals("")) {
				sqlSb.append(" and a.news_title like '%" + news.getNewsTitle() + "%'");
				sumSql.append(" and news_title like '%" + news.getNewsTitle() + "%'");
			}

			if (news.getNewsType() != null) {
				sqlSb.append(" and a.news_type='" + news.getNewsType() + "'");
				sumSql.append(" and news_type='" + news.getNewsType() + "'");
			}
			news.setNewsIsDelete("0");
			sqlSb.append(" and a.news_is_delete='0'");
			sumSql.append(" and news_is_delete='0'");

			sqlSb.append(" and a.news_is_checked='1'");
			sumSql.append(" and news_is_checked='1'");

			if (news.getNewsIsTop() != null) {
				sqlSb.append(" and a.news_is_top='" + news.getNewsIsTop() + "'");
				sumSql.append(" and news_is_top='" + news.getNewsIsTop() + "'");
			}
			if (news.getNewsIsLinkTitle() != null) {
				sqlSb.append(" and a.news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
				sumSql.append(" and news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
			}
			if (news.getNewsIsAccessory() != null && !news.getNewsIsAccessory().equals("")) {
				sqlSb.append(" and a.news_is_accessory='" + news.getNewsIsAccessory() + "'");
				sumSql.append(" and news_is_accessory='" + news.getNewsIsAccessory() + "'");
			}
			if (news.getNewsIsIPLimit() != null) {
				sqlSb.append(" and a.news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
				sumSql.append(" and news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
			}
			if (news.getNewsIsLogin() != null) {
				sqlSb.append(" and a.news_is_login='" + news.getNewsIsLogin() + "'");
				sumSql.append(" and news_is_login='" + news.getNewsIsLogin() + "'");
			}
			if (news.getNewsIsImageNews() != null) {
				sqlSb.append(" and a.news_is_image_news='" + news.getNewsIsImageNews() + "'");
				sumSql.append(" and news_is_image_news='" + news.getNewsIsImageNews() + "'");
			}
			if (news.getPublishStartTime() != null && !news.getPublishStartTime().equals("")) {
				sqlSb.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s')) ");
				sumSql.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			}
			if (news.getPublishEndTime() != null && !news.getPublishEndTime().equals("")) {
				sqlSb.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
				sumSql.append(
						" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
								+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			}
			sqlSb.append(" order by news_publish_time desc ");
			sqlSb.append(" limit ");
			sqlSb.append((currentSize - 1) * pageSize);
			sqlSb.append(",");
			sqlSb.append(pageSize);

			pageBean.setTotalRows(util.getObjCount(sumSql.toString()));
			List<Map<String, Object>> tmpRe = util.getMapList(sqlSb.toString(), new Object[] {});
			if (null != tmpRe)
				for (int j = 0; j < tmpRe.size(); j++) {
					result.add(tmpRe.get(j));
				}
		}
		pageBean.setDispList(result);
		return pageBean;
	}

	@Override
	public PageBean getNewsWithParaList(PageBean pageBean, List<Integer> newsList) {
		// TODO Auto-generated method stub
		int currentSize = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from sdu_news where news_type in(");

		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" a.news_id as newsId, ");
		sqlSb.append(" a.news_title as newsTitle, ");
		sqlSb.append(" a.news_sub_title as newsSubTitle, ");
		sqlSb.append(" a.news_content as newsContent, ");
		sqlSb.append(" a.news_type as newsType, ");
		sqlSb.append(" b.param_name as newsTypeName, ");
		sqlSb.append(" a.news_author as newsAuthor, ");
		sqlSb.append(" a.news_publish_time as newsPublishTime, ");
		sqlSb.append(" a.news_company as newsCompany, ");
		sqlSb.append(" a.news_modifier as newsModifier, ");
		sqlSb.append(" a.news_modify_time as newsModifyTime, ");
		sqlSb.append(" a.news_deleter as newsDeleter, ");
		sqlSb.append(" a.news_delete_time as newsDeleteTime, ");
		sqlSb.append(" a.news_is_delete as newsIsDelete, ");
		sqlSb.append(" a.news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" a.news_is_checked as newsIsChecked, ");
		sqlSb.append(" a.news_checker as newsChecker, ");
		sqlSb.append(" a.news_checked_time as newsCheckedTime, ");
		sqlSb.append(" a.news_is_top as newsIsTop, ");
		sqlSb.append(" a.news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" a.news_link_url as newsLinkUrl, ");
		sqlSb.append(" a.news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" a.news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" a.news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" a.news_is_login as newsIsLogin, ");
		sqlSb.append(" a.news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" a.news_image_url as newsImageUrl, ");
		sqlSb.append(" a.news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" a.news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" a.news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" a.news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" a.news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" a.news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" a.news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" a.news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" a.news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" a.news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" a.news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" a.news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" a.news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" a.news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" a.news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" a.news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" a.news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" a.news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news a, pub_sys_param b ");
		sqlSb.append(" where 1=1 and a.news_type=b.param_value and a.news_type in ( ");
		for (int i = 0; i < newsList.size(); i++) {
			int nPerTypeCode = newsList.get(i);
			if (i == newsList.size() - 1) {
				sqlSb.append(" '" + nPerTypeCode + "' ");
				sumSql.append(" '" + nPerTypeCode + "' ");
			} else {
				sqlSb.append(" '" + nPerTypeCode + "' ,");
				sumSql.append(" '" + nPerTypeCode + "', ");
			}
		}

		sqlSb.append(") and a.news_is_delete='0'");
		sumSql.append(") and news_is_delete='0'");

		sqlSb.append(" and a.news_is_checked='1'");
		sumSql.append(" and news_is_checked='1'");

		sqlSb.append(" order by news_publish_time desc ");
		sqlSb.append(" limit ");
		sqlSb.append((currentSize - 1) * pageSize);
		sqlSb.append(",");
		sqlSb.append(pageSize);

		pageBean.setTotalRows(util.getObjCount(sumSql.toString()));
		pageBean.setDispList((List<Map<String, Object>>) util.getMapList(sqlSb.toString(), new Object[] {}));
		return pageBean;
	}

	@Override
	public PageBean getNewsWithParaList(PageBean pageBean, List<Integer> newsList, String newsIsImageNews) {
		// TODO Auto-generated method stub
		int currentSize = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from sdu_news where news_type in(");

		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" a.news_id as newsId, ");
		sqlSb.append(" a.news_title as newsTitle, ");
		sqlSb.append(" a.news_sub_title as newsSubTitle, ");
		sqlSb.append(" a.news_content as newsContent, ");
		sqlSb.append(" a.news_type as newsType, ");
		sqlSb.append(" b.param_name as newsTypeName, ");
		sqlSb.append(" a.news_author as newsAuthor, ");
		sqlSb.append(" a.news_publish_time as newsPublishTime, ");
		sqlSb.append(" a.news_company as newsCompany, ");
		sqlSb.append(" a.news_modifier as newsModifier, ");
		sqlSb.append(" a.news_modify_time as newsModifyTime, ");
		sqlSb.append(" a.news_deleter as newsDeleter, ");
		sqlSb.append(" a.news_delete_time as newsDeleteTime, ");
		sqlSb.append(" a.news_is_delete as newsIsDelete, ");
		sqlSb.append(" a.news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" a.news_is_checked as newsIsChecked, ");
		sqlSb.append(" a.news_checker as newsChecker, ");
		sqlSb.append(" a.news_checked_time as newsCheckedTime, ");
		sqlSb.append(" a.news_is_top as newsIsTop, ");
		sqlSb.append(" a.news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" a.news_link_url as newsLinkUrl, ");
		sqlSb.append(" a.news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" a.news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" a.news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" a.news_is_login as newsIsLogin, ");
		sqlSb.append(" a.news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" a.news_image_url as newsImageUrl, ");
		sqlSb.append(" a.news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" a.news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" a.news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" a.news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" a.news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" a.news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" a.news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" a.news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" a.news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" a.news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" a.news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" a.news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" a.news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" a.news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" a.news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" a.news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" a.news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" a.news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news a, pub_sys_param b ");
		sqlSb.append(" where 1=1 and a.news_type=b.param_value and a.news_type in ( ");
		for (int i = 0; i < newsList.size(); i++) {
			int nPerTypeCode = newsList.get(i);
			if (i == newsList.size() - 1) {
				sqlSb.append(" '" + nPerTypeCode + "' ");
				sumSql.append(" '" + nPerTypeCode + "' ");
			} else {
				sqlSb.append(" '" + nPerTypeCode + "' ,");
				sumSql.append(" '" + nPerTypeCode + "', ");
			}
		}

		sqlSb.append(") and a.news_is_delete='0'");
		sumSql.append(") and news_is_delete='0'");

		sqlSb.append(" and a.news_is_checked='1'");
		sumSql.append(" and news_is_checked='1'");

		sqlSb.append(" and a.news_is_image_news='" + newsIsImageNews + "'");
		sumSql.append(" and news_is_image_news='" + newsIsImageNews + "'");
		sqlSb.append(" order by news_publish_time desc ");
		sqlSb.append(" limit ");
		sqlSb.append((currentSize - 1) * pageSize);
		sqlSb.append(",");
		sqlSb.append(pageSize);

		pageBean.setTotalRows(util.getObjCount(sumSql.toString()));
		pageBean.setDispList((List<Map<String, Object>>) util.getMapList(sqlSb.toString(), new Object[] {}));
		return pageBean;
	}

	@Override
	public PageBean getNewsWithParaList(PageBean pageBean, String newsType, String newsIsImageNews) {
		// TODO Auto-generated method stub
		int currentSize = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from sdu_news where news_type='" + newsType + "' ");

		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" a.news_id as newsId, ");
		sqlSb.append(" a.news_title as newsTitle, ");
		sqlSb.append(" a.news_sub_title as newsSubTitle, ");
		sqlSb.append(" a.news_content as newsContent, ");
		sqlSb.append(" a.news_type as newsType, ");
		sqlSb.append(" b.param_name as newsTypeName, ");
		sqlSb.append(" a.news_author as newsAuthor, ");
		sqlSb.append(" a.news_publish_time as newsPublishTime, ");
		sqlSb.append(" a.news_company as newsCompany, ");
		sqlSb.append(" a.news_modifier as newsModifier, ");
		sqlSb.append(" a.news_modify_time as newsModifyTime, ");
		sqlSb.append(" a.news_deleter as newsDeleter, ");
		sqlSb.append(" a.news_delete_time as newsDeleteTime, ");
		sqlSb.append(" a.news_is_delete as newsIsDelete, ");
		sqlSb.append(" a.news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" a.news_is_checked as newsIsChecked, ");
		sqlSb.append(" a.news_checker as newsChecker, ");
		sqlSb.append(" a.news_checked_time as newsCheckedTime, ");
		sqlSb.append(" a.news_is_top as newsIsTop, ");
		sqlSb.append(" a.news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" a.news_link_url as newsLinkUrl, ");
		sqlSb.append(" a.news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" a.news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" a.news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" a.news_is_login as newsIsLogin, ");
		sqlSb.append(" a.news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" a.news_image_url as newsImageUrl, ");
		sqlSb.append(" a.news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" a.news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" a.news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" a.news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" a.news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" a.news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" a.news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" a.news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" a.news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" a.news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" a.news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" a.news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" a.news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" a.news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" a.news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" a.news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" a.news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" a.news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news a, pub_sys_param b ");
		sqlSb.append(" where 1=1 and a.news_type=b.param_value and a.news_type ='" + newsType + "' ");

		sqlSb.append(" and a.news_is_delete='0'");
		sumSql.append(" and news_is_delete='0'");

		sqlSb.append(" and a.news_is_checked='1'");
		sumSql.append(" and news_is_checked='1'");

		sqlSb.append(" and a.news_is_image_news='" + newsIsImageNews + "'");
		sumSql.append(" and news_is_image_news='" + newsIsImageNews + "'");
		sqlSb.append(" order by news_publish_time desc ");
		sqlSb.append(" limit ");
		sqlSb.append((currentSize - 1) * pageSize);
		sqlSb.append(",");
		sqlSb.append(pageSize);

		pageBean.setTotalRows(util.getObjCount(sumSql.toString()));
		pageBean.setDispList((List<Map<String, Object>>) util.getMapList(sqlSb.toString(), new Object[] {}));
		return pageBean;
	}

	@Override
	public PageBean getAllNewsWithPara(News news, PageBean pageBean) {
		// TODO Auto-generated method stub
		int currentSize = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from sdu_news where 1=1");

		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" news_id as newsId, ");
		sqlSb.append(" news_title as newsTitle, ");
		sqlSb.append(" news_sub_title as newsSubTitle, ");
		sqlSb.append(" news_content as newsContent, ");
		sqlSb.append(" news_type as newsType, ");
		sqlSb.append(" news_author as newsAuthor, ");
		sqlSb.append(" news_publish_time as newsPublishTime, ");
		sqlSb.append(" news_company as newsCompany, ");
		sqlSb.append(" news_modifier as newsModifier, ");
		sqlSb.append(" news_modify_time as newsModifyTime, ");
		sqlSb.append(" news_deleter as newsDeleter, ");
		sqlSb.append(" news_delete_time as newsDeleteTime, ");
		sqlSb.append(" news_is_delete as newsIsDelete, ");
		sqlSb.append(" news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" news_is_checked as newsIsChecked, ");
		sqlSb.append(" news_checker as newsChecker, ");
		sqlSb.append(" news_checked_time as newsCheckedTime, ");
		sqlSb.append(" news_is_top as newsIsTop, ");
		sqlSb.append(" news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" news_link_url as newsLinkUrl, ");
		sqlSb.append(" news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" news_is_login as newsIsLogin, ");
		sqlSb.append(" news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" news_image_url as newsImageUrl, ");
		sqlSb.append(" news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news ");
		sqlSb.append(" where 1=1");

		sqlSb.append(" and news_type in ('1','2','3','4')");
		sumSql.append(" and news_type in ('1','2','3','4')");

		sqlSb.append(" and news_is_delete='0'");
		sumSql.append(" and news_is_delete='0'");

		sqlSb.append(" and news_is_checked='1'");
		sumSql.append(" and news_is_checked='1'");

		if (news.getNewsTitle() != null && !news.getNewsTitle().equals("")) {
			sqlSb.append(" and a.news_title like '%" + news.getNewsTitle() + "%'");
			sumSql.append(" and news_title like '%" + news.getNewsTitle() + "%'");
		}

		if (news.getNewsIsTop() != null) {
			sqlSb.append(" and news_is_top='" + news.getNewsIsTop() + "'");
			sumSql.append(" and news_is_top='" + news.getNewsIsTop() + "'");
		}
		if (news.getNewsIsLinkTitle() != null) {
			sqlSb.append(" and news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
			sumSql.append(" and news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
		}
		if (news.getNewsIsAccessory() != null && !news.getNewsIsAccessory().equals("")) {
			sqlSb.append(" and a.news_is_accessory='" + news.getNewsIsAccessory() + "'");
			sumSql.append(" and news_is_accessory='" + news.getNewsIsAccessory() + "'");
		}
		if (news.getNewsIsIPLimit() != null) {
			sqlSb.append(" and news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
			sumSql.append(" and news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
		}
		if (news.getNewsIsLogin() != null) {
			sqlSb.append(" and news_is_login='" + news.getNewsIsLogin() + "'");
			sumSql.append(" and news_is_login='" + news.getNewsIsLogin() + "'");
		}
		if (news.getNewsIsImageNews() != null) {
			sqlSb.append(" and news_is_image_news='" + news.getNewsIsImageNews() + "'");
			sumSql.append(" and news_is_image_news='" + news.getNewsIsImageNews() + "'");
		}
		if (news.getPublishStartTime() != null && !news.getPublishStartTime().equals("")) {
			sqlSb.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s')) ");
			sumSql.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
		}
		if (news.getPublishEndTime() != null && !news.getPublishEndTime().equals("")) {
			sqlSb.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			sumSql.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
		}
		sqlSb.append("order by news_is_top desc,news_publish_time desc ");
		sqlSb.append(" limit ");
		sqlSb.append((currentSize - 1) * pageSize);
		sqlSb.append(",");
		sqlSb.append(pageSize);

		pageBean.setTotalRows(util.getObjCount(sumSql.toString()));
		pageBean.setDispList((List<Map<String, Object>>) util.getMapList(sqlSb.toString(), new Object[] {}));
		return pageBean;
	}

	@Override
	public PageBean getAllMeetingWithPara(News news, PageBean pageBean) {
		// TODO Auto-generated method stub
		int currentSize = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from sdu_news where 1=1");

		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" news_id as newsId, ");
		sqlSb.append(" news_title as newsTitle, ");
		sqlSb.append(" news_sub_title as newsSubTitle, ");
		sqlSb.append(" news_content as newsContent, ");
		sqlSb.append(" news_type as newsType, ");
		sqlSb.append(" news_author as newsAuthor, ");
		sqlSb.append(" news_publish_time as newsPublishTime, ");
		sqlSb.append(" news_company as newsCompany, ");
		sqlSb.append(" news_modifier as newsModifier, ");
		sqlSb.append(" news_modify_time as newsModifyTime, ");
		sqlSb.append(" news_deleter as newsDeleter, ");
		sqlSb.append(" news_delete_time as newsDeleteTime, ");
		sqlSb.append(" news_is_delete as newsIsDelete, ");
		sqlSb.append(" news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" news_is_checked as newsIsChecked, ");
		sqlSb.append(" news_checker as newsChecker, ");
		sqlSb.append(" news_checked_time as newsCheckedTime, ");
		sqlSb.append(" news_is_top as newsIsTop, ");
		sqlSb.append(" news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" news_link_url as newsLinkUrl, ");
		sqlSb.append(" news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" news_is_login as newsIsLogin, ");
		sqlSb.append(" news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" news_image_url as newsImageUrl, ");
		sqlSb.append(" news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news ");
		sqlSb.append(" where 1=1");

		sqlSb.append(" and news_type in ('9','10') ");
		sumSql.append(" and news_type in ('9','10') ");

		sqlSb.append(" and news_is_delete='0'");
		sumSql.append(" and news_is_delete='0'");

		sqlSb.append(" and news_is_checked='1'");
		sumSql.append(" and news_is_checked='1'");

		if (news.getNewsTitle() != null && !news.getNewsTitle().equals("")) {
			sqlSb.append(" and a.news_title like '%" + news.getNewsTitle() + "%'");
			sumSql.append(" and news_title like '%" + news.getNewsTitle() + "%'");
		}

		if (news.getNewsIsTop() != null) {
			sqlSb.append(" and news_is_top='" + news.getNewsIsTop() + "'");
			sumSql.append(" and news_is_top='" + news.getNewsIsTop() + "'");
		}
		if (news.getNewsIsLinkTitle() != null) {
			sqlSb.append(" and news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
			sumSql.append(" and news_is_link_title='" + news.getNewsIsLinkTitle() + "'");
		}
		if (news.getNewsIsAccessory() != null && !news.getNewsIsAccessory().equals("")) {
			sqlSb.append(" and a.news_is_accessory='" + news.getNewsIsAccessory() + "'");
			sumSql.append(" and news_is_accessory='" + news.getNewsIsAccessory() + "'");
		}
		if (news.getNewsIsIPLimit() != null) {
			sqlSb.append(" and news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
			sumSql.append(" and news_is_ip_limit='" + news.getNewsIsIPLimit() + "'");
		}
		if (news.getNewsIsLogin() != null) {
			sqlSb.append(" and news_is_login='" + news.getNewsIsLogin() + "'");
			sumSql.append(" and news_is_login='" + news.getNewsIsLogin() + "'");
		}
		if (news.getNewsIsImageNews() != null) {
			sqlSb.append(" and news_is_image_news='" + news.getNewsIsImageNews() + "'");
			sumSql.append(" and news_is_image_news='" + news.getNewsIsImageNews() + "'");
		}
		if (news.getPublishStartTime() != null && !news.getPublishStartTime().equals("")) {
			sqlSb.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s')) ");
			sumSql.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))>=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishStartTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
		}
		if (news.getPublishEndTime() != null && !news.getPublishEndTime().equals("")) {
			sqlSb.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
			sumSql.append(
					" and UNIX_TIMESTAMP(str_to_date(news_publish_time,'%Y-%m-%d %H:%i:%s'))<=UNIX_TIMESTAMP(str_to_date('"
							+ news.getPublishEndTime().trim() + "','%Y-%m-%d %H:%i:%s'))");
		}
		sqlSb.append("order by news_is_top desc,news_publish_time desc ");
		sqlSb.append(" limit ");
		sqlSb.append((currentSize - 1) * pageSize);
		sqlSb.append(",");
		sqlSb.append(pageSize);

		pageBean.setTotalRows(util.getObjCount(sumSql.toString()));
		pageBean.setDispList((List<Map<String, Object>>) util.getMapList(sqlSb.toString(), new Object[] {}));
		return pageBean;
	}

	@Override
	public String removeAllHtmlTag(String content) {
		// TODO Auto-generated method stub
		// HTML文本代码
		// 读出body内里所有内容
		// String strClear=content.replaceAll( ".*?(.*?)<\\/body>", "$1");
		// 保留br标签和p标签
		// strClear=strClear.replaceAll("</?[^/?(br)|(p)][^><]*>","");
		/*
		 * 如果要保留IMG标签的话，正则表达式就是：</?[^/?(img)][^><]*> 如果想保留更多的标签，只在正则表达式改一下就可以了，
		 * </?[^/?(img)|(p)][^><]*>
		 * 这个保留(这里写的保留了img,p这两个标签)你指定的标签,其他的(包括font)全去掉, 如果你还有其他的标签想保留,直接在里面加一个
		 * |(xxx)就行了 想去掉所有的标签的话正则表达式为：</?[a-zA-Z]+[^><]*>
		 */
		content = content.replaceAll("</?[a-zA-Z]+[^><]*>", "");
		// 去除字符串中的空格,回车,换行符,制表符
		content = content.replaceAll("<a>\\s*|\t|\r|\n</a>", "");
		// 去除字符串中的空格
		return content.replaceAll("&nbsp;", "");

	}

	@Override
	public News getNewsWithIdEx(String newsId) {
		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" news_id as newsId, ");
		sqlSb.append(" news_title as newsTitle, ");
		sqlSb.append(" news_sub_title as newsSubTitle, ");
		sqlSb.append(" news_content as newsContent, ");
		sqlSb.append(" news_type as newsType, ");
		sqlSb.append(" news_author as newsAuthor, ");
		sqlSb.append(" news_publish_time as newsPublishTime, ");
		sqlSb.append(" news_company as newsCompany, ");
		sqlSb.append(" news_modifier as newsModifier, ");
		sqlSb.append(" news_modify_time as newsModifyTime, ");
		sqlSb.append(" news_deleter as newsDeleter, ");
		sqlSb.append(" news_delete_time as newsDeleteTime, ");
		sqlSb.append(" news_is_delete as newsIsDelete, ");
		sqlSb.append(" news_total_access_count as newsTotalAccessCount, ");
		sqlSb.append(" news_is_checked as newsIsChecked, ");
		sqlSb.append(" news_checker as newsChecker, ");
		sqlSb.append(" news_checked_time as newsCheckedTime, ");
		sqlSb.append(" news_is_top as newsIsTop, ");
		sqlSb.append(" news_is_link_title as newsIsLinkTitle, ");
		sqlSb.append(" news_link_url as newsLinkUrl, ");
		sqlSb.append(" news_is_accessory as newsIsAccessory, ");
		sqlSb.append(" news_accessory_url as newsAccessoryUrl, ");
		sqlSb.append(" news_is_ip_limit as newsIsIPLimit, ");
		sqlSb.append(" news_is_login as newsIsLogin, ");
		sqlSb.append(" news_is_image_news as newsIsImageNews, ");
		sqlSb.append(" news_image_url as newsImageUrl, ");
		sqlSb.append(" news_image_url1 as newsImageUrl1, ");
		sqlSb.append(" news_image_url2 as newsImageUrl2, ");
		sqlSb.append(" news_image_url3 as newsImageUrl3, ");
		sqlSb.append(" news_image_url4 as newsImageUrl4, ");
		sqlSb.append(" news_image_url5 as newsImageUrl5, ");
		sqlSb.append(" news_image_url6 as newsImageUrl6, ");
		sqlSb.append(" news_image_url7 as newsImageUrl7, ");
		sqlSb.append(" news_image_url8 as newsImageUrl8, ");
		sqlSb.append(" news_image_url9 as newsImageUrl9, ");
		sqlSb.append(" news_files_url1 as newsFilesUrl1, ");
		sqlSb.append(" news_files_url2 as newsFilesUrl2, ");
		sqlSb.append(" news_files_url3 as newsFilesUrl3, ");
		sqlSb.append(" news_files_url4 as newsFilesUrl4, ");
		sqlSb.append(" news_files_url5 as newsFilesUrl5, ");
		sqlSb.append(" news_files_url6 as newsFilesUrl6, ");
		sqlSb.append(" news_files_url7 as newsFilesUrl7, ");
		sqlSb.append(" news_files_url8 as newsFilesUrl8, ");
		sqlSb.append(" news_files_url9 as newsFilesUrl9 ");
		sqlSb.append(" from sdu_news ");
		sqlSb.append(" where news_id ='" + newsId + "'");
		util.getObject(sqlSb.toString(), new Object[] {}, News.class);
		return (News) util.getObject(sqlSb.toString(), new Object[] {}, News.class);
	}

	@Override
	public List<Map<String, Object>> getUserListFromOrgId(String orgId) {
		// TODO Auto-generated method stub
		String sql = "select id from pub_org_desc where org_id='" + orgId + "'";
		List<Map<String, Object>> tempOrgList = util.getMapList(sql, new Object[] {});
		if (tempOrgList != null && tempOrgList.size() > 0) {
			String orgDesc = tempOrgList.get(0).get("id").toString();
			sql = "SELECT user_id as userId, " + "user_account as userAccount, " + "user_name as userName "
					+ "FROM `pub_users` " + "where user_org in " + "(SELECT org_id FROM `pub_org` where org_id in "
					+ "(select org_id from pub_org_desc where id like '" + orgDesc + "%'))";
			return util.getMapList(sql, new Object[] {});
		}

		return null;
	}
}
