package com.example.dell.myapplication14.Model.Object;

public class Trademark {
    private int TRADEMARK_ID;
    private int COUNT_BUY;
    private String Image,TRADEMARK_NAME;

    public String getTRADEMARK_NAME() {
        return TRADEMARK_NAME;
    }

    public void setTRADEMARK_NAME(String TRADEMARK_NAME) {
        this.TRADEMARK_NAME = TRADEMARK_NAME;
    }

    public int getCOUNT_BUY() {
        return COUNT_BUY;
    }

    public void setCOUNT_BUY(int COUNT_BUY) {
        this.COUNT_BUY = COUNT_BUY;
    }


    public int getTRADEMARK_ID() {
        return TRADEMARK_ID;
    }

    public void setTRADEMARK_ID(int TRADEMARK_ID) {
        this.TRADEMARK_ID = TRADEMARK_ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
