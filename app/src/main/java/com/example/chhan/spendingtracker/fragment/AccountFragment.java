package com.example.chhan.spendingtracker.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.icu.math.BigDecimal;
import android.icu.text.LocaleDisplayNames;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.icu.util.CurrencyAmount;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chhan.spendingtracker.AddExpense;
import com.example.chhan.spendingtracker.AddIncome;
import com.example.chhan.spendingtracker.AppExecutors;
import com.example.chhan.spendingtracker.LoadData;
import com.example.chhan.spendingtracker.R;
import com.example.chhan.spendingtracker.database.AppDatabase;
import com.example.chhan.spendingtracker.database.IncomeDetailsEntry;
import com.example.chhan.spendingtracker.database.IncomeEntry;
import com.example.chhan.spendingtracker.databinding.AccountLayoutBinding;

import java.util.List;
import java.util.Locale;

public class AccountFragment extends Fragment implements View.OnClickListener,LoadData {

    Button mButtonAddIncome;
    Button mButtonAddEx;
    TextView mTextViewAmountIn;

    AppDatabase mDb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_layout,container,false);

        mTextViewAmountIn = rootView.findViewById(R.id.textViewAmountIn);

        mButtonAddIncome = rootView.findViewById(R.id.btnAddIncome);
        mButtonAddIncome.setOnClickListener(this);

        mButtonAddEx = rootView.findViewById(R.id.btnAddExpense);
        mButtonAddEx.setOnClickListener(this);

        mDb = AppDatabase.getInstance(getContext());
        retrieveData();

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddIncome :
                Intent addIncomeActivity = new Intent(getContext(), AddIncome.class);
                startActivity(addIncomeActivity);
                break;
            case R.id.btnAddExpense :
                Intent addExpenseActivity = new Intent(getContext(), AddExpense.class);
                startActivity(addExpenseActivity);
                break;
        }
    }


    @Override
    public void retrieveData() {
        final LiveData<List<IncomeEntry>> listLiveData = mDb.incomeDao().getAllIncome();
        listLiveData.observe(this, new Observer<List<IncomeEntry>>() {
            List<IncomeEntry> list;
            @Override
            public void onChanged(@Nullable List<IncomeEntry> incomeEntries) {
                float amount = 0;
                for(IncomeEntry result:incomeEntries){
                    amount += result.getAmount();
                }
                Currency currency = Currency.getInstance("KHR");
                String format = currency.getSymbol() + amount;
                mTextViewAmountIn.setText(format);
            }
        });
    }
}
