package com.example.dell.myapplication14.Model.Object;

import java.util.List;

public class Bill {
    private int BILL_ID,TRANSFER;
    private String DAY_OF_PURCHASE,DELIVERY_DATE,STATUS,NAME,PHONE,ADDRESS;
    List<Bill_Detail> bill_detailList;

    public List<Bill_Detail> getBill_detailList() {
        return bill_detailList;
    }

    public void setBill_detailList(List<Bill_Detail> bill_detailList) {
        this.bill_detailList = bill_detailList;
    }

    public int getTRANSFER() {
        return TRANSFER;
    }

    public void setTRANSFER(int TRANSFER) {
        this.TRANSFER = TRANSFER;
    }

    public int getBILL_ID() {
        return BILL_ID;
    }

    public void setBILL_ID(int BILL_ID) {
        this.BILL_ID = BILL_ID;
    }

    public String getDAY_OF_PURCHASE() {
        return DAY_OF_PURCHASE;
    }

    public void setDAY_OF_PURCHASE(String DAY_OF_PURCHASE) {
        this.DAY_OF_PURCHASE = DAY_OF_PURCHASE;
    }

    public String getDELIVERY_DATE() {
        return DELIVERY_DATE;
    }

    public void setDELIVERY_DATE(String DELIVERY_DATE) {
        this.DELIVERY_DATE = DELIVERY_DATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }
}
