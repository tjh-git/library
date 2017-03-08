package com.simple.website.po;

/**
 * 新闻PO
 * @author Zhang
 *
 */
public class News 
{
	/**
	 * 新闻ID
	 */
	String newsId;
	/**
	 * 新闻标题
	 */
	String newsTitle;
	/**
	 * 新闻副标题
	 */
	String newsSubTitle;
	/**
	 * 新闻内容
	 */
	String newsContent;
	/**
	 * 新闻类型 
	 * 1：领导讲话
	 * 2：新闻动态
	 * 3：工作通知
	 * 4：信息公开
	 * 5：政策法规
	 * 6：常用下载
	 * 7：机构设置
	 */
	String newsType;
	/**
	 * 新闻作者
	 */
	String newsAuthor;
	/**
	 * 新闻发布时间
	 */
	String newsPublishTime;
	/**
	 * 新闻上传人(总是记录最后一个)
	 */
	String newsModifier;
	/**
	 * 新闻上传时间(总是记录最后一个)
	 */
	String newsModifyTime;
	/**
	 * 新闻删除人
	 */
	String newsDeleter;
	/**
	 * 新闻删除时间 
	 */
	String newsDeleteTime;
	/**
	 * 是否删除 0否1是
	 */
	String newsIsDelete;
	/**
	 * 新闻访问次数
	 */
	String newsTotalAccessCount;
	/**
	 * 是否已经审核 0否1是
	 */
	String newsIsChecked;
	/**
	 * 审核人
	 */
	String newsChecker;
	/**
	 * 审核时间
	 */
	String newsCheckedTime;
	/**
	 * 新闻是否置顶 0否1是
	 */
	String newsIsTop;
	/**
	 * 新闻是否是链接标题 0否1是
	 */
	String newsIsLinkTitle;
	/**
	 * 新闻链接Url
	 */
	String newsLinkUrl; 
	
	/**
	 * 附件新闻 0 否 1 是
	 */
	String newsIsAccessory;
	
	/**
	 * 附件URL
	 */
	String newsAccessoryUrl;
	/**
	 * 单位
	 */
	
	String newsCompany;
	
	/**
	 * 是否IP限制 0否1是
	 */
	String newsIsIPLimit; 
	/**
	 * 是否登录限制 0否1是
	 */
	String newsIsLogin; 
	/**
	 * 是否图片新闻 0否1是
	 */
	String newsIsImageNews;
	/**
	 * 图片新闻标题图片
	 */
	String newsImageUrl;
	/**
	 * 内容图片存放地址
	 */
	String newsImageUrl1;
	String newsImageUrl2;
	String newsImageUrl3;
	String newsImageUrl4;
	String newsImageUrl5;
	String newsImageUrl6;
	String newsImageUrl7;
	String newsImageUrl8;
	String newsImageUrl9;
	/**
	 * 内容附件存放地址
	 */
	String newsFilesUrl1;
	String newsFilesUrl2;
	String newsFilesUrl3;
	String newsFilesUrl4;
	String newsFilesUrl5;
	String newsFilesUrl6;
	String newsFilesUrl7;
	String newsFilesUrl8;
	String newsFilesUrl9;
	
	String publishStartTime;
	String publishEndTime;
	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsSubTitle() {
		return newsSubTitle;
	}
	public void setNewsSubTitle(String newsSubTitle) {
		this.newsSubTitle = newsSubTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getNewsAuthor() {
		return newsAuthor;
	}
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	public String getNewsPublishTime() {
		return newsPublishTime;
	}
	public void setNewsPublishTime(String newsPublishTime) {
		this.newsPublishTime = newsPublishTime;
	}
	public String getNewsModifier() {
		return newsModifier;
	}
	public void setNewsModifier(String newsModifier) {
		this.newsModifier = newsModifier;
	}
	public String getNewsModifyTime() {
		return newsModifyTime;
	}
	public void setNewsModifyTime(String newsModifyTime) {
		this.newsModifyTime = newsModifyTime;
	}
	public String getNewsDeleter() {
		return newsDeleter;
	}
	public void setNewsDeleter(String newsDeleter) {
		this.newsDeleter = newsDeleter;
	}
	public String getNewsDeleteTime() {
		return newsDeleteTime;
	}
	public void setNewsDeleteTime(String newsDeleteTime) {
		this.newsDeleteTime = newsDeleteTime;
	}
	public String getNewsIsDelete() {
		return newsIsDelete;
	}
	public void setNewsIsDelete(String newsIsDelete) {
		this.newsIsDelete = newsIsDelete;
	}
	public String getNewsTotalAccessCount() {
		return newsTotalAccessCount;
	}
	public void setNewsTotalAccessCount(String newsTotalAccessCount) {
		this.newsTotalAccessCount = newsTotalAccessCount;
	}
	public String getNewsIsChecked() {
		return newsIsChecked;
	}
	public void setNewsIsChecked(String newsIsChecked) {
		this.newsIsChecked = newsIsChecked;
	}
	public String getNewsChecker() {
		return newsChecker;
	}
	public void setNewsChecker(String newsChecker) {
		this.newsChecker = newsChecker;
	}
	public String getNewsCheckedTime() {
		return newsCheckedTime;
	}
	public void setNewsCheckedTime(String newsCheckedTime) {
		this.newsCheckedTime = newsCheckedTime;
	}
	public String getNewsIsTop() {
		return newsIsTop;
	}
	public void setNewsIsTop(String newsIsTop) {
		this.newsIsTop = newsIsTop;
	}
	public String getNewsIsLinkTitle() {
		return newsIsLinkTitle;
	}
	public void setNewsIsLinkTitle(String newsIsLinkTitle) {
		this.newsIsLinkTitle = newsIsLinkTitle;
	}
	public String getNewsLinkUrl() {
		return newsLinkUrl;
	}
	public void setNewsLinkUrl(String newsLinkUrl) {
		this.newsLinkUrl = newsLinkUrl;
	}
	public String getNewsIsIPLimit() {
		return newsIsIPLimit;
	}
	public void setNewsIsIPLimit(String newsIsIPLimit) {
		this.newsIsIPLimit = newsIsIPLimit;
	}
	public String getNewsIsLogin() {
		return newsIsLogin;
	}
	public void setNewsIsLogin(String newsIsLogin) {
		this.newsIsLogin = newsIsLogin;
	}
	public String getNewsIsImageNews() {
		return newsIsImageNews;
	}
	public void setNewsIsImageNews(String newsIsImageNews) {
		this.newsIsImageNews = newsIsImageNews;
	}
	public String getNewsImageUrl() {
		return newsImageUrl;
	}
	public void setNewsImageUrl(String newsImageUrl) {
		this.newsImageUrl = newsImageUrl;
	}
	public String getNewsImageUrl1() {
		return newsImageUrl1;
	}
	public void setNewsImageUrl1(String newsImageUrl1) {
		this.newsImageUrl1 = newsImageUrl1;
	}
	public String getNewsImageUrl2() {
		return newsImageUrl2;
	}
	public void setNewsImageUrl2(String newsImageUrl2) {
		this.newsImageUrl2 = newsImageUrl2;
	}
	public String getNewsImageUrl3() {
		return newsImageUrl3;
	}
	public void setNewsImageUrl3(String newsImageUrl3) {
		this.newsImageUrl3 = newsImageUrl3;
	}
	public String getNewsImageUrl4() {
		return newsImageUrl4;
	}
	public void setNewsImageUrl4(String newsImageUrl4) {
		this.newsImageUrl4 = newsImageUrl4;
	}
	public String getNewsImageUrl5() {
		return newsImageUrl5;
	}
	public void setNewsImageUrl5(String newsImageUrl5) {
		this.newsImageUrl5 = newsImageUrl5;
	}
	public String getNewsImageUrl6() {
		return newsImageUrl6;
	}
	public void setNewsImageUrl6(String newsImageUrl6) {
		this.newsImageUrl6 = newsImageUrl6;
	}
	public String getNewsImageUrl7() {
		return newsImageUrl7;
	}
	public void setNewsImageUrl7(String newsImageUrl7) {
		this.newsImageUrl7 = newsImageUrl7;
	}
	public String getNewsImageUrl8() {
		return newsImageUrl8;
	}
	public void setNewsImageUrl8(String newsImageUrl8) {
		this.newsImageUrl8 = newsImageUrl8;
	}
	public String getNewsImageUrl9() {
		return newsImageUrl9;
	}
	public void setNewsImageUrl9(String newsImageUrl9) {
		this.newsImageUrl9 = newsImageUrl9;
	}
	public String getNewsFilesUrl1() {
		return newsFilesUrl1;
	}
	public void setNewsFilesUrl1(String newsFilesUrl1) {
		this.newsFilesUrl1 = newsFilesUrl1;
	}
	public String getNewsFilesUrl2() {
		return newsFilesUrl2;
	}
	public void setNewsFilesUrl2(String newsFilesUrl2) {
		this.newsFilesUrl2 = newsFilesUrl2;
	}
	public String getNewsFilesUrl3() {
		return newsFilesUrl3;
	}
	public void setNewsFilesUrl3(String newsFilesUrl3) {
		this.newsFilesUrl3 = newsFilesUrl3;
	}
	public String getNewsFilesUrl4() {
		return newsFilesUrl4;
	}
	public void setNewsFilesUrl4(String newsFilesUrl4) {
		this.newsFilesUrl4 = newsFilesUrl4;
	}
	public String getNewsFilesUrl5() {
		return newsFilesUrl5;
	}
	public void setNewsFilesUrl5(String newsFilesUrl5) {
		this.newsFilesUrl5 = newsFilesUrl5;
	}
	public String getNewsFilesUrl6() {
		return newsFilesUrl6;
	}
	public void setNewsFilesUrl6(String newsFilesUrl6) {
		this.newsFilesUrl6 = newsFilesUrl6;
	}
	public String getNewsFilesUrl7() {
		return newsFilesUrl7;
	}
	public void setNewsFilesUrl7(String newsFilesUrl7) {
		this.newsFilesUrl7 = newsFilesUrl7;
	}
	public String getNewsFilesUrl8() {
		return newsFilesUrl8;
	}
	public void setNewsFilesUrl8(String newsFilesUrl8) {
		this.newsFilesUrl8 = newsFilesUrl8;
	}
	public String getNewsFilesUrl9() {
		return newsFilesUrl9;
	}
	public void setNewsFilesUrl9(String newsFilesUrl9) {
		this.newsFilesUrl9 = newsFilesUrl9;
	}
	
	public String getPublishStartTime() {
		return publishStartTime;
	}
	public void setPublishStartTime(String publishStartTime) {
		this.publishStartTime = publishStartTime;
	}
	public String getPublishEndTime() {
		return publishEndTime;
	}
	public void setPublishEndTime(String publishEndTime) {
		this.publishEndTime = publishEndTime;
	}
	
	public String getNewsIsAccessory() {
		return newsIsAccessory;
	}

	public void setNewsIsAccessory(String newsIsAccessory) {
		this.newsIsAccessory = newsIsAccessory;
	}

	public String getNewsAccessoryUrl() {
		return newsAccessoryUrl;
	}

	public void setNewsAccessoryUrl(String newsAccessoryUrl) {
		this.newsAccessoryUrl = newsAccessoryUrl;
	}
	
	public String getNewsCompany() {
		return newsCompany;
	}

	public void setNewsCompany(String newsCompany) {
		this.newsCompany = newsCompany;
	}
}
