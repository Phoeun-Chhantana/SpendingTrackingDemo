package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "tbExpense",foreignKeys = @ForeignKey(
        parentColumns = "exCateId",childColumns = "exCateId",entity = CategoryExEntry.class,onUpdate = CASCADE,onDelete = CASCADE),
        indices = {@Index(value = "exCateId")})
public class ExpenseEntry extends Room {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float amount;
    private String date;
    private String note;
    private int exCateId;
    private String exCateName;

    public ExpenseEntry(String name,float amount,String date,String note,int exCateId,String exCateName){
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.exCateId = exCateId;
        this.exCateName = exCateName;
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

    public void setExCateId(int exCateId) {
        this.exCateId = exCateId;
    }

    public void setExCateName(String exCateName) {
        this.exCateName = exCateName;
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

    public int getExCateId() {
        return exCateId;
    }

    public String getExCateName() {
        return exCateName;
    }
}
