package com.example.chhan.spendingtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

public class DateIncome extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    DatePicker mDatePickerIncome;
    Toolbar mToolbarDateIncome;
    TextView mTextViewDisplayDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_income);

        mDatePickerIncome = findViewById(R.id.datePickerIncome);
        mTextViewDisplayDate = findViewById(R.id.textViewDisplayDateIncome);

        mToolbarDateIncome = findViewById(R.id.toolBarDateIncome);
        setSupportActionBar(mToolbarDateIncome);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDatePickerIncome.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });
    }

    @Override
    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}
