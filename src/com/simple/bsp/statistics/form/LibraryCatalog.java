package com.simple.bsp.statistics.form;

/**
 * Created by 17854 on 2016/7/2.
 */
public class LibraryCatalog
{
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    private String type=null;//图书类型
    private String publishDate=null;//出版时间
    private String loginDate=null;//录入时间

}
