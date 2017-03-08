package com.simple.BookManage.RequestBeans;

/**
 * Created by lovesyxfuffy on 2016/6/7.
 */
public class Book_Catalog extends BookInfo {
    int order_id ;
    int book_id;
    int borrow_status;
    int book_for;
    int book_pos_code;
    int school_id;
    int borrow_count;

    public int getBook_pos_code() {
        return book_pos_code;
    }

    public void setBook_pos_code(int book_pos_code) {
        this.book_pos_code = book_pos_code;
    }



    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getBorrow_count() {
        return borrow_count;
    }

    public void setBorrow_count(int borrow_count) {
        this.borrow_count = borrow_count;
    }



    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBorrow_status() {
        return borrow_status;
    }

    public void setBorrow_status(int borrow_status) {
        this.borrow_status = borrow_status;
    }

    public int getBook_for() {
        return book_for;
    }

    public void setBook_for(int book_for) {
        this.book_for = book_for;
    }


}
