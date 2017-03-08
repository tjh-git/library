package com.simple.BookManage.RequestBeans;

/**
 * Created by lovesyxfuffy on 2016/6/3.
 */
public class BookInfo {

    //出版社信息 常量
    //馆藏位置 常量
    static public int HAVE_VOL_NO=-1;
    static public int HAVE_VOL_YES=1;
    static public int IS_REF_NO=-1;
    static public int IS_REF_YES=1;
    static public int IS_JOURNAL_YES=1;
    static public int IS_JOURNAL_NO=-1;
    String bookcode;
    String bookname;
    int have_vol;
    String bookab;
    String seriesname;
    String writer;
    String translator;
    String publishcom;
    String booklanguage;
    String bookbind;
    int bookpagenum;
    double bookprice;

    String seriesab;
    String second_writer;
    String other_putup;
    String together_putup;
    String booktype;
    String book_position;
    String is_journal;
    String operator_id;
    String operator_name;
    String getdate;

    String publishdate;
    int editiontimes;
    int printtimes;
    String booksize;

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    int catalog_id;

    public void setIs_refbook(String is_refbook) {
        this.is_refbook = is_refbook;
    }

    public void setIs_journal(String is_journal) {
        this.is_journal = is_journal;
    }

    public String getIs_refbook() {
        return is_refbook;
    }

    public String getIs_journal() {
        return is_journal;
    }

    String is_refbook;

    public String getSeriesab() {
        return seriesab;
    }

    public String getBook_position() {
        return book_position;
    }

    public void setBook_position(String book_position) {
        this.book_position = book_position;
    }

    public void setSeriesab(String seriesab) {
        this.seriesab = seriesab;
    }

    public String getSecond_writer() {
        return second_writer;
    }

    public void setSecond_writer(String second_writer) {
        this.second_writer = second_writer;
    }

    public String getOther_putup() {
        return other_putup;
    }

    public void setOther_putup(String other_putup) {
        this.other_putup = other_putup;
    }

    public String getTogether_putup() {
        return together_putup;
    }

    public void setTogether_putup(String together_putup) {
        this.together_putup = together_putup;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }



    public String getBooksize() {
        return booksize;
    }

    public void setBooksize(String booksize) {
        this.booksize = booksize;
    }


    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getBookcode() {
        return bookcode;
    }

    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getHave_vol() {
        return have_vol;
    }

    public void setHave_vol(int have_vol) {
        this.have_vol = have_vol;
    }

    public String getBookab() {
        return bookab;
    }

    public void setBookab(String bookab) {
        this.bookab = bookab;
    }

    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublishcom() {
        return publishcom;
    }

    public void setPublishcom(String publishcom) {
        this.publishcom = publishcom;
    }

    public String getBooklanguage() {
        return booklanguage;
    }

    public void setBooklanguage(String booklanguage) {
        this.booklanguage = booklanguage;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public int getEditiontimes() {
        return editiontimes;
    }

    public void setEditiontimes(int editiontimes) {
        this.editiontimes = editiontimes;
    }

    public int getPrinttimes() {
        return printtimes;
    }

    public void setPrinttimes(int printtimes) {
        this.printtimes = printtimes;
    }


    public String getBookbind() {
        return bookbind;
    }

    public void setBookbind(String bookbind) {
        this.bookbind = bookbind;
    }

    public int getBookpagenum() {
        return bookpagenum;
    }

    public void setBookpagenum(int bookpagenum) {
        this.bookpagenum = bookpagenum;
    }

    public double getBookprice() {
        return bookprice;
    }

    public void setBookprice(double bookprice) {
        this.bookprice = bookprice;
    }

    public String getGetdate() {
        return getdate;
    }

    public void setGetdate(String getdate) {
        this.getdate = getdate;
    }
}
