package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;

@Entity(tableName = "tbCategoryExpense")
public class CategoryExEntry extends Room {
    @PrimaryKey(autoGenerate = true)
    private int exCateId;
    @ColumnInfo(name = "category_expense")
    String cateExName;

    public CategoryExEntry(String cateExName){
        this.cateExName = cateExName;
    }

    public void setExCateId(int exCateId) {
        this.exCateId = exCateId;
    }

    public void setCateExName(String cateExName) {
        this.cateExName = cateExName;
    }

    public int getId() {
        return exCateId;
    }

    public String getCateExName() {
        return cateExName;
    }

    public int getExCateId() {
        return exCateId;
    }
}
