package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Room;

@Entity(tableName = "tbExpenseDetails",primaryKeys = {"exId","exCateId"},
    foreignKeys = {@ForeignKey(entity = ExpenseEntry.class,parentColumns = "id",childColumns = "exId"),
                @ForeignKey(entity = CategoryExEntry.class,parentColumns = "exCateId",childColumns = "exCateId")},
indices = {@Index(value = "exCateId")})

public class ExpenseDetailEntry extends Room {

    private int exId;
    private String exName;
    private float exAmount;
    private String exDate;
    private int exCateId;
    private String exCateName;

    public ExpenseDetailEntry(int exId,String exName,float exAmount,String exDate,int exCateId,String exCateName){
        this.exId = exId;
        this.exName = exName;
        this.exAmount = exAmount;
        this.exDate = exDate;
        this.exCateId = exCateId;
        this.exCateName = exCateName;
    }

    public void setExCateId(int exCateId) {
        this.exCateId = exCateId;
    }

    public void setExAmount(float exAmount) {
        this.exAmount = exAmount;
    }

    public void setExCateName(String exCateName) {
        this.exCateName = exCateName;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public void setExId(int exId) {
        this.exId = exId;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public int getExId() {
        return exId;
    }

    public String getExName() {
        return exName;
    }

    public float getExAmount() {
        return exAmount;
    }

    public int getExCateId() {
        return exCateId;
    }

    public String getExCateName() {
        return exCateName;
    }

    public String getExDate() {
        return exDate;
    }
}
