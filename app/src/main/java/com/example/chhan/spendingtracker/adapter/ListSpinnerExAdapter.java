package com.example.chhan.spendingtracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chhan.spendingtracker.R;
import com.example.chhan.spendingtracker.database.CategoryExEntry;

import java.util.List;

public class ListSpinnerExAdapter extends BaseAdapter {

    TextView mTextViewCateEx;
    List<CategoryExEntry> mListItems;

    public ListSpinnerExAdapter(List<CategoryExEntry> items){
        this.mListItems = items;
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_expense,parent,false);

        mTextViewCateEx = rootView.findViewById(R.id.textViewCateExpense);
        mTextViewCateEx.setText(mListItems.get(position).getCateExName());

        return rootView;
    }
}
