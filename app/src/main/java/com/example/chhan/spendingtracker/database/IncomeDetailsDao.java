package com.example.chhan.spendingtracker.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface IncomeDetailsDao {

    //**tableIncomeDetail
    /*@Query("SELECT * FROM tbIncomeDetails")
    List<IncomeDetailsEntry> getAllDetailsIncome();
    @Insert
    void insertIncomeDetailsEntry(IncomeDetailsEntry incomeDetailsEntry);*/
}
