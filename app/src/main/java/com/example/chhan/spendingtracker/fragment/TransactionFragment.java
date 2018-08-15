package com.example.chhan.spendingtracker.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuLayout;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuView;
import com.example.chhan.spendingtracker.LoadData;
import com.example.chhan.spendingtracker.R;
import com.example.chhan.spendingtracker.adapter.ListTransactionAdapter;
import com.example.chhan.spendingtracker.database.AppDatabase;
import com.example.chhan.spendingtracker.database.IncomeEntry;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment implements LoadData {

    ExpandableListView mExpandableListView;
    ListTransactionAdapter mAdapter;

    AppDatabase mDb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.details_layout,container,false);

        mExpandableListView = rootView.findViewById(R.id.expandableListView);

        mDb = AppDatabase.getInstance(getContext());
        retrieveData();

        return rootView;
    }

    @Override
    public void retrieveData() {
        LiveData<List<IncomeEntry>> listLiveData = mDb.incomeDao().getAllIncome();
        listLiveData.observe(this, new Observer<List<IncomeEntry>>() {
            @Override
            public void onChanged(@Nullable List<IncomeEntry> incomeEntries) {
                mAdapter = new ListTransactionAdapter(incomeEntries);
                mExpandableListView.setAdapter(mAdapter);
            }
        });
    }
}
