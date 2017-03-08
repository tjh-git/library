package com.simple.BookManage.RequestBeans;

/**
 * Created by lovesyxfuffy on 2016/7/3.
 */
public class Book_BookLogin extends  Book {
    int order_id;
    int catalog_id;//


    String book_from;//
    int book_buy_price;//
    int buy_num;//
    String book_position;

    public String getBook_position() {
        return book_position;
    }

    public void setBook_position(String book_position) {
        this.book_position = book_position;
    }

    public int getStart_pos() {
        return start_pos;
    }

    public void setStart_pos(int start_pos) {
        this.start_pos = start_pos;
    }

    int buy_amount_price;

    int start_pos;
    String operator_id;
    String operator_name;
    String buy_date;

    public String getBook_from() {
        return book_from;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public int getCatalog_id() {
        return catalog_id;
    }

    @Override
    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public void setBook_from(String bookfrom) {
        this.book_from = bookfrom;
    }

    public int getBook_buy_price() {
        return book_buy_price;
    }

    public void setBook_buy_price(int book_buy_price) {
        this.book_buy_price = book_buy_price;
    }

    public int getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(int buy_num) {
        this.buy_num = buy_num;
    }

    public int getBuy_amount_price() {
        return buy_amount_price;
    }

    public void setBuy_amount_price(int buy_amount_price) {
        this.buy_amount_price = buy_amount_price;
    }


    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(String buy_date) {
        this.buy_date = buy_date;
    }
}
