package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;

@Entity(tableName = "tbCategoryIncome")
public class CategoryInEntry extends Room {
    @PrimaryKey(autoGenerate = true)
    private int inCateId;
    @ColumnInfo(name = "category_income")
    String cateInName;

    public CategoryInEntry(String cateInName){
        this.cateInName = cateInName;
    }

    public void setInCateId(int inCateId) {
        this.inCateId = inCateId;
    }

    public void setCateInName(String cateInName) {
        this.cateInName = cateInName;
    }

    public int getId() {
        return inCateId;
    }

    public String getCateInName() {
        return cateInName;
    }

    public int getInCateId() {
        return inCateId;
    }
}
