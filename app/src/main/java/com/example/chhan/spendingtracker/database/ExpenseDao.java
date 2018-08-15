package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {

    //**tableExpense**
    @Insert
    void insertExpense(ExpenseEntry expenseEntry);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateExpense(ExpenseEntry expenseEntry);
    @Query("SELECT * FROM tbExpense")
    List<ExpenseEntry> getAllExpense();
}
