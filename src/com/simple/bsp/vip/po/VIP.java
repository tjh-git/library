package com.simple.bsp.vip.po;

/**
 * Created by 17854 on 2016/6/2.
 */
public class VIP {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    private String id;
    private String readCard = "";//借阅证卡号、
    private String name = "";//姓名、
    private String IDCard = "";// 身份证号、
    private String gender = "";// 性别、

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    private String operator="";//操作员

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    private String cardType = "";// 会员卡类型、
    private String addTime = "";// 加入时间、
    private String status = "";// 会员状态、
    private String stuID = "";// 学号、
    private String deadLine = "";// 借阅证有效期
    private String teachID = "";// 教职工号、
    private String teachClass = "";// 所教课程、
    private String tel = "";// 手机、
    private String qq = "";// qq、
    private String email = "";// 常用邮箱等。
    private int money=0;//预存金额

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public String getStatus() {
        return status;
    }

    public void setTeachID(String teachID) {
        this.teachID = teachID;
    }

    public String getTeachClass() {
        return teachClass;
    }

    public String getTeachID() {
        return teachID;
    }
    public String getReadCard() {
        return readCard;
    }

    public void setReadCard(String readCard) {
        this.readCard = readCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public void setTeachClass(String teachClass) {
        this.teachClass = teachClass;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}