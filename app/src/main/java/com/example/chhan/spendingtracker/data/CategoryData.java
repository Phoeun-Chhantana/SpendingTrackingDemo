package com.example.chhan.spendingtracker.data;

import java.util.ArrayList;
import java.util.List;

public class CategoryData {

    private static List<String> mListIncomeCate = new ArrayList<String>(){{
        add("General Income");
    }};

    private static List<String> mListExpenseCate = new ArrayList<String>(){{
        add("General Expense");
    }};

    public static List<String> getCateIncome(){ return mListIncomeCate;}

    public static List<String> getCateExpense() { return mListExpenseCate;}
}
