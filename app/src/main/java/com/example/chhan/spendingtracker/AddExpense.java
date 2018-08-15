package com.example.chhan.spendingtracker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chhan.spendingtracker.adapter.ListSpinnerExAdapter;
import com.example.chhan.spendingtracker.database.AppDatabase;
import com.example.chhan.spendingtracker.database.CategoryExEntry;
import com.example.chhan.spendingtracker.databinding.AddExpenseLayoutBinding;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.util.Date;
import java.util.List;

public class AddExpense extends AppCompatActivity
        implements View.OnClickListener,DatePickerDialog.OnDateSetListener,ConvertMonth,LoadData{

    AddExpenseLayoutBinding mBinding;

    AppDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.add_expense_layout);

        mBinding = DataBindingUtil.setContentView(this,R.layout.add_expense_layout);

        setSupportActionBar(mBinding.toolBarAddExpense);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBinding.textViewDateExpense.setOnClickListener(this);

        mDb = AppDatabase.getInstance(getApplicationContext());
        retrieveData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Date date = new Date();
        String yearString = DateFormat.format("yyyy",date.getTime()).toString();
        String monthString = DateFormat.format("MM",date.getTime()).toString();
        String dayString = DateFormat.format("dd",date.getTime()).toString();

        SpinnerDatePickerDialogBuilder builder = new SpinnerDatePickerDialogBuilder();
        builder.showDaySpinner(true)
                .showTitle(true)
                .context(this)
                .defaultDate(Integer.valueOf(yearString),Integer.valueOf(monthString) - 1,Integer.valueOf(dayString))
                .callback(this)
                .build().show();
    }

    /*private String convertMonthToString(int monthIndex){
        String monthToString = null;
        switch (monthIndex){
            case 0 : monthToString = "Jan"; break;
            case 1 : monthToString = "Feb"; break;
            case 2 : monthToString = "Mar"; break;
            case 3 : monthToString = "April"; break;
            case 4 : monthToString = "May"; break;
            case 5 : monthToString = "June"; break;
            case 6 : monthToString = "July"; break;
            case 7 : monthToString = "Aug"; break;
            case 8 : monthToString = "Sep"; break;
            case 9 : monthToString = "Oct"; break;
            case 10 : monthToString = "Nov"; break;
            case 11 : monthToString = "Dec"; break;
        }
        return monthToString;
    }*/

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String month = convertMonthToString(monthOfYear);
        String date = month + "  " + dayOfMonth + " , " + year;
        mBinding.textViewDateExpense.setText(date);
    }

    @Override
    public String convertMonthToString(int monthIndex) {
        String monthToString = null;
        switch (monthIndex){
            case 0 : monthToString = "Jan"; break;
            case 1 : monthToString = "Feb"; break;
            case 2 : monthToString = "Mar"; break;
            case 3 : monthToString = "April"; break;
            case 4 : monthToString = "May"; break;
            case 5 : monthToString = "June"; break;
            case 6 : monthToString = "July"; break;
            case 7 : monthToString = "Aug"; break;
            case 8 : monthToString = "Sep"; break;
            case 9 : monthToString = "Oct"; break;
            case 10 : monthToString = "Nov"; break;
            case 11 : monthToString = "Dec"; break;
        }
        return monthToString;
    }

    @Override
    public void retrieveData() {
        final LiveData<List<CategoryExEntry>> categoryEx = mDb.categoryExpenseDao().getAllCateEx();
        categoryEx.observe(this, new Observer<List<CategoryExEntry>>() {
            @Override
            public void onChanged(@Nullable List<CategoryExEntry> categoryExEntries) {
                ListSpinnerExAdapter adapter = new ListSpinnerExAdapter(categoryExEntries);
                mBinding.spinnerCateExpense.setAdapter(adapter);
                categoryEx.removeObserver(this);
            }
        });
    }
}
