package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "tbIncome",foreignKeys = @ForeignKey(entity = CategoryInEntry.class, parentColumns = "inCateId",
        childColumns = "inCateId",onDelete = CASCADE,onUpdate = CASCADE),
        indices = {@Index(value = { "inCateId" })})
public class IncomeEntry extends Room {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float amount;
    private String date;
    private String note;
    private int inCateId;
    private String inCateName;


    public IncomeEntry(String name,float amount,String date,String note,int inCateId,String inCateName){
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.inCateId = inCateId;
        this.inCateName = inCateName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setInCateId(int cateId){
        this.inCateId = cateId;
    }

    public void setInCateName(String inCateName) {
        this.inCateName = inCateName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public int getInCateId() {
        return inCateId;
    }

    public String getInCateName() {
        return inCateName;
    }
}
