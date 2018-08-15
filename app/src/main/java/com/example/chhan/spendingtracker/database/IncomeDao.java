package com.example.chhan.spendingtracker.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface IncomeDao {

    //**tableIncome**
    @Insert
    void insertIncome(IncomeEntry incomeEntry);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateIncome(IncomeEntry incomeEntry);
    @Query("SELECT * FROM tbIncome")
    LiveData<List<IncomeEntry>> getAllIncome();
    @Delete
    void deleteIncome(IncomeEntry incomeEntry);
}
