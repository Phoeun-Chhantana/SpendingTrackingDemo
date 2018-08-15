package com.example.chhan.spendingtracker.adapter;

import android.icu.util.ULocale;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chhan.spendingtracker.R;
import com.example.chhan.spendingtracker.database.CategoryExEntry;

import java.util.List;

public class ListCategoryExAdapter extends RecyclerView.Adapter<ListCategoryExAdapter.ListCategoryExViewHolder>{

    List<CategoryExEntry> mListItems;

    public ListCategoryExAdapter(List<CategoryExEntry> list){
        this.mListItems = list;
        notifyDataSetChanged();
    }

    @Override
    public ListCategoryExViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_expense,parent,false);

        return new ListCategoryExViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListCategoryExViewHolder holder, int position) {
        holder.mTextViewCateEx.setText(mListItems.get(position).getCateExName());
    }

    @Override
    public int getItemCount() {
        if(mListItems == null){
            return 0;
        }else{
            return mListItems.size();
        }
    }

    class ListCategoryExViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mTextViewCateEx;

        public ListCategoryExViewHolder(View itemView) {
            super(itemView);

            mTextViewCateEx = itemView.findViewById(R.id.textViewCateExpense);
            mTextViewCateEx.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
