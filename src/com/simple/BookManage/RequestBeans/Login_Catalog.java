package com.simple.BookManage.RequestBeans;

/**
 * Created by lovesyxfuffy on 2016/6/6.
 */
public class Login_Catalog extends BookInfo {
    int order_id;
    String bookcode;//

    public String getBook_from() {
        return book_from;
    }

    String book_from;//
    int book_buy_price;//
    int buy_num;//
    int buy_amount_price;

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    String operator_id;
    String operator_name;
    String buy_date;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public String getBookcode() {
        return bookcode;
    }

    @Override
    public void setBookcode(String bookcode) {
        this.bookcode = bookcode;
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
