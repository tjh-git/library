package com.simple.BookManage.RequestBeans;

/**
 * Created by lovesyxfuffy on 2016/6/6.
 */
public class Book {
    int order_id ;
    int book_id;
    int borrow_status=0;
    int catalog_id;
    String book_position;
    String book_pos_code;
    int school_id;

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getBook_pos_code() {
        return book_pos_code;
    }

    public void setBook_pos_code(String book_pos_code) {
        this.book_pos_code = book_pos_code;
    }

    public int getBorrow_count() {
        return borrow_count;
    }

    public void setBorrow_count(int borrow_count) {
        this.borrow_count = borrow_count;
    }

    int borrow_count;
    public int getTeacher_touch() {
        return teacher_touch;
    }

    public void setTeacher_touch(int teacher_touch) {
        this.teacher_touch = teacher_touch;
    }

    public int getStudent_touch() {
        return student_touch;
    }

    public void setStudent_touch(int student_touch) {
        this.student_touch = student_touch;
    }

    int teacher_touch;
    int student_touch;


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

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getBook_position() {
        return book_position;
    }

    public void setBook_position(String book_position) {
        this.book_position = book_position;
    }
}
