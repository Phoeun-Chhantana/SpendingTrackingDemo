package com.example.chhan.spendingtracker.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

@Database(entities =
        {IncomeEntry.class,
                ExpenseEntry.class,
                CategoryInEntry.class,
                CategoryExEntry.class},
        version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "SpendingTracker";
    private static AppDatabase sInstance;
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract IncomeDao incomeDao();
    public abstract ExpenseDao expenseDao();
    public abstract CategoryIncomeDao categoryIncomeDao();
    public abstract CategoryExpenseDao categoryExpenseDao();
}
