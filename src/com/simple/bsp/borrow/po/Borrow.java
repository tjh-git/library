package com.simple.bsp.borrow.po;

/**
 * Created by 17854 on 2016/6/4.
 */
public class Borrow
{
    public static final int BOOK_IN=0;//书籍未被借出
    public static final int BOOK_OUT=1;//书籍已经借出

    public static final int HAS_RETUEN=1;//书籍已还
    public static final int NOT_RETUEN=2;//书籍未还
    public static final int OVERDUE=3;//书籍已经逾期
    public static final int NOT_FIND_BOOK=-8;//没有找到该书籍
//    public static final int MAX_BORROW=10;//最大的书籍借阅数量

    public static final int READ_CARD_OVER_DEADLINE=0;//借阅证过期
    public static final int OVER_MAX_BORROW=1;//超过最大的借阅数量
    public static final int HAS_OVERDUE=2;//存在超期未还的书籍
    public static final int CAN_BORROW=3;
    public static final int NO_USER=4;//没有此用户
    public static final int CANNOT_BORROW=5;
//    public static final long duration=90*24*3600;
    /////////////////////////////////////////////////////////
    private String bookName="";//书名
    private String bookCode="";//书的编号
    private String isOut="";//是否外借
    private int borrowNum=0;//借阅次数
    private String borrowerName="";//借阅人
    private String borrowerCode="";//借阅人借阅号
    private String returnTime="";//归还时间
    private String borrowTime="";//借阅时间
    private String cardType="";//会员卡类型
    private String needReturn="";//应还时间
    private String money="";//预存金额
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getNeedReturn() {
        return needReturn;
    }

    public void setNeedReturn(String needReturn) {
        this.needReturn = needReturn;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public int getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(int borrowNum) {
        this.borrowNum = borrowNum;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerCode() {
        return borrowerCode;
    }

    public void setBorrowerCode(String borrowerCode) {
        this.borrowerCode = borrowerCode;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
}
