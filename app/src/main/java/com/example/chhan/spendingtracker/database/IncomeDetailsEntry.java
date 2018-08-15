package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Room;

@Entity(tableName = "tbIncomeDetails",primaryKeys = {"inDetailId","inCateDetailId"},
    foreignKeys = { @ForeignKey(entity = IncomeEntry.class,parentColumns = "id",childColumns = "inDetailId"),
                @ForeignKey(entity = CategoryInEntry.class,parentColumns = "inCateId",childColumns = "inCateDetailId")},
                indices = {@Index(value = "inCateDetailId"),@Index(value = "inDetailId")})
public class IncomeDetailsEntry extends Room {

    private int inDetailId;
    private String inDetailName;
    private float inDetailAmount;
    private String inDetailDate;
    private int inCateDetailId;
    private String inCateDetailName;

    public IncomeDetailsEntry(int inDetailId,String inDetailName,float inDetailAmount,String inDetailDate,int inCateDetailId,String inCateDetailName){
        this.inDetailId = inDetailId;
        this.inDetailName = inDetailName;
        this.inDetailAmount = inDetailAmount;
        this.inDetailDate = inDetailDate;
        this.inCateDetailId = inCateDetailId;
        this.inCateDetailName = inCateDetailName;
    }

    public void setInCateDetailId(int inCateDetailId) {
        this.inCateDetailId = inCateDetailId;
    }

    public void setInCateDetailName(String inCateDetailName) {
        this.inCateDetailName = inCateDetailName;
    }

    public void setInDetailAmount(float inDetailAmount) {
        this.inDetailAmount = inDetailAmount;
    }

    public void setInDetailDate(String inDetailDate) {
        this.inDetailDate = inDetailDate;
    }

    public void setInDetailId(int inDetailId) {
        this.inDetailId = inDetailId;
    }

    public void setInDetailName(String inDetailName) {
        this.inDetailName = inDetailName;
    }

    public float getInDetailAmount() {
        return inDetailAmount;
    }

    public int getInCateDetailId() {
        return inCateDetailId;
    }

    public int getInDetailId() {
        return inDetailId;
    }

    public String getInCateDetailName() {
        return inCateDetailName;
    }

    public String getInDetailDate() {
        return inDetailDate;
    }

    public String getInDetailName() {
        return inDetailName;
    }
}
