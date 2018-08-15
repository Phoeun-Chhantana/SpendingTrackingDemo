package com.example.chhan.spendingtracker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.chhan.spendingtracker.adapter.ListSpinnerInAdapter;
import com.example.chhan.spendingtracker.database.AppDatabase;
import com.example.chhan.spendingtracker.database.CategoryInEntry;
import com.example.chhan.spendingtracker.database.IncomeEntry;
import com.example.chhan.spendingtracker.databinding.AddIncomeLayoutBinding;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class AddIncome extends AppCompatActivity implements
        View.OnClickListener,DatePickerDialog.OnDateSetListener,LoadData{

    private static String mItem;
    private static final String TAG = MainActivity.class.getSimpleName();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    private Date date = new Date();
    private String mName;
    private float mAmount;
    private String mNote;
    private String mDate;
    private int mCateId;
    private String mCateName;

    ListSpinnerInAdapter mAdapter;
    AddIncomeLayoutBinding mBinding;

    AppDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.add_income_layout);

        setSupportActionBar(mBinding.toolBarAddIncome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBinding.textViewDateIncome.setOnClickListener(this);
        mBinding.spinnerCateIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCateId = (int) mAdapter.getItemId(position);
                mCateName = mAdapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.getCurrentDate();

        mDb = AppDatabase.getInstance(getApplicationContext());
        retrieveData();
    }

    public static void setItem(String item){
        mItem = item;
    }

    public static String getItem() {
        return mItem;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            onActionSaveIncome();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onActionSaveIncome(){
        mName = mBinding.editTextNameIn.getText().toString();
        mAmount = Float.parseFloat(mBinding.editTextAmountIn.getText().toString());
        mNote = mBinding.editTextNoteIn.getText().toString();
        mDate = mBinding.textViewDateIncome.getText().toString() + " " + timeFormat.format(date);
        final IncomeEntry incomeEntry = new IncomeEntry(mName,mAmount,mDate,mNote,mCateId,mCateName);
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.incomeDao().insertIncome(incomeEntry);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mBinding.textViewDateIncome.getId()){

            SpinnerDatePickerDialogBuilder builder = new SpinnerDatePickerDialogBuilder();
            builder.showDaySpinner(true)
                    .showTitle(true)
                    .context(this)
                    .defaultDate(LocalDateTime.now().getYear(),
                            LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth())
                    .callback(this)
                    .build().show();
        }
    }

    private void getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String month = dateFormat.getDateFormatSymbols().getMonths()[LocalDate.now().getMonthValue()];
        mDate = month + " , " +
                LocalDateTime.now().getDayOfMonth() + " , " +
                LocalDateTime.now().getYear();
        mBinding.textViewDateIncome.setText(mDate);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String month = dateFormat.getDateFormatSymbols().getMonths()[monthOfYear-1];
        mDate = month + " , " + dayOfMonth + " , " + year;
        mBinding.textViewDateIncome.setText(mDate);
    }

    @Override
    public void retrieveData() {
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        final LiveData<List<CategoryInEntry>> categoryIn = mDb.categoryIncomeDao().getAllCateIn();
        categoryIn.observe(this, new Observer<List<CategoryInEntry>>() {
            @Override
            public void onChanged(@Nullable List<CategoryInEntry> categoryInEntries) {
                Log.d(TAG,"Receiving database update from LiveData");
                mAdapter = new ListSpinnerInAdapter(categoryInEntries);
                mBinding.spinnerCateIncome.setAdapter(mAdapter);
                categoryIn.removeObserver(this);
            }
        });
    }
}
