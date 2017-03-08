package com.simple.bsp.borrow.po;

/**
 * Created by 17854 on 2016/6/6.
 */
public class Urge
{
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_readCard() {
        return user_readCard;
    }

    public void setUser_readCard(String user_readCard) {
        this.user_readCard = user_readCard;
    }

    public String getUser_fine() {
        return user_fine;
    }

    public void setUser_fine(String user_fine) {
        this.user_fine = user_fine;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_code() {
        return book_code;
    }

    public void setBook_code(String book_code) {
        this.book_code = book_code;
    }
    public String getUser_cardType() {
        return user_cardType;
    }

    public void setUser_cardType(String user_cardType) {
        this.user_cardType = user_cardType;
    }

    private String user_name="";//借阅人
    private String user_readCard="";//借阅人借阅卡号
    private String user_cardType="";//借阅人会员卡类型
    private String user_fine="";//借阅人超期罚款
    private String borrowTime="";//借阅人借阅时间
    private String deadLine="";//借阅应还时间
    private String book_name="";//书籍名称
    private String book_code="";//书籍编码

}
