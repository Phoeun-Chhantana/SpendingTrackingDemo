package com.example.chhan.spendingtracker.adapter;


import android.content.Context;
import android.databinding.adapters.SpinnerBindingAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chhan.spendingtracker.R;
import com.example.chhan.spendingtracker.database.CategoryInEntry;

import java.util.List;

public class ListSpinnerInAdapter extends BaseAdapter {

    TextView mTextViewCateIn;
    List<CategoryInEntry> mListItems;

    public ListSpinnerInAdapter(List<CategoryInEntry> list){
        mListItems = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mListItems.get(position).getCateInName();
    }

    @Override
    public long getItemId(int position) {
        return mListItems.get(position).getId();
    }

    public void setItems(List<CategoryInEntry> list){
        mListItems = list;
        notifyDataSetChanged();
    }

    public List<CategoryInEntry> getListItems() {
        return mListItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_income,parent,false);

        mTextViewCateIn = rootView.findViewById(R.id.textViewCateIncome);
        mTextViewCateIn.setText(mListItems.get(position).getCateInName());

        return rootView;
    }
}
