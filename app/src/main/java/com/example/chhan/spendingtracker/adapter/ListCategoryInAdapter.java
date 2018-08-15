package com.example.chhan.spendingtracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chhan.spendingtracker.R;
import com.example.chhan.spendingtracker.database.CategoryInEntry;

import java.util.List;

public class ListCategoryInAdapter extends RecyclerView.Adapter<ListCategoryInAdapter.ListCategoryInViewHolder>{

    private List<CategoryInEntry> mListItems;

    @Override
    public ListCategoryInViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_income,parent,false);

        return new ListCategoryInViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListCategoryInViewHolder holder, int position) {
        holder.mTextViewCateIn.setText(mListItems.get(position).getCateInName());
    }

    @Override
    public int getItemCount() {
        if(mListItems == null){
            return 0;
        }else{
            return mListItems.size();
        }
    }

    public void setItems(List<CategoryInEntry> list){
        mListItems = list;
        notifyDataSetChanged();
    }

    public List<CategoryInEntry> getListItems() {
        return mListItems;
    }

    class ListCategoryInViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTextViewCateIn;

        public ListCategoryInViewHolder(View itemView) {
            super(itemView);

            mTextViewCateIn = itemView.findViewById(R.id.textViewCateIncome);
            mTextViewCateIn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
