package com.example.chhan.spendingtracker.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface CategoryIncomeDao {
    //**tableCategoryIncome**
    @Insert
    void insertCateIn(CategoryInEntry category);
    @Delete
    void deleteCateIn(CategoryInEntry category);
    @Query("SELECT * FROM tbCategoryIncome")
    LiveData<List<CategoryInEntry>> getAllCateIn();
}
