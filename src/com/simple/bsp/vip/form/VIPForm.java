package com.simple.bsp.vip.form;

/**
 * Created by 17854 on 2016/6/3.
 */
public class VIPForm
{
    private String name=null;
    private String readCard=null;
    private String cardType=null;
    private String type="";
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReadCard() {
        return readCard;
    }

    public void setReadCard(String readCard) {
        this.readCard = readCard;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
