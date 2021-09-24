package com.example.glimmerthreads;

public class Discount {

    int discountID;
    String DiscountTitle;
    int amount;
    int iCode;


    public Discount(String discountTitle, int amount, int iCode) {
        DiscountTitle = discountTitle;
        this.amount = amount;
        this.iCode = iCode;
    }

    public Discount(int discountID, String discountTitle, int amount, int iCode) {
        this.discountID = discountID;
        DiscountTitle = discountTitle;
        this.amount = amount;
        this.iCode = iCode;
    }

    public String getDiscountTitle() {
        return DiscountTitle;
    }

    public int getAmount() {
        return amount;
    }

    public int getiCode() {
        return iCode;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public void setDiscountTitle(String discountTitle) {
        DiscountTitle = discountTitle;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setiCode(int iCode) {
        this.iCode = iCode;
    }
}
