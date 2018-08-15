package com.example.chhan.spendingtracker.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CategoryExpenseDao {
    //**tableCategoryExpense**
    @Insert
    void insertCateEx(CategoryExEntry categoryExEntry);
    @Delete
    void deleteCateEx(CategoryExEntry categoryExEntry);
    @Query("SELECT * FROM tbCategoryExpense")
    LiveData<List<CategoryExEntry>> getAllCateEx();
}
