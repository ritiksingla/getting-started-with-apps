package com.example.todoapp;

public class Word {
    private String mItemName;
    private double mPrice;
    private int mImageId;
    public Word(String Itemname,double price,int imageId)
    {
        mItemName=Itemname;
        mPrice=price;
        mImageId=imageId;
    }
    public String getmItemName() {
        return mItemName;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }
}
