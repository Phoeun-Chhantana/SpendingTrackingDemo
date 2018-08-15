package com.example.chhan.spendingtracker.adapter;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.chhan.spendingtracker.R;
import com.example.chhan.spendingtracker.database.ExpenseEntry;
import com.example.chhan.spendingtracker.database.IncomeEntry;

import org.w3c.dom.Text;

import java.util.List;

public class ListTransactionAdapter implements ExpandableListAdapter {

    TextView mTextViewGroupDis;
    TextView mTextViewTrans;
    TextView mTextViewTransCateName;
    TextView mTextViewTransDate;
    List<IncomeEntry> mListIncome;
    List<String> mListExpense;


    public ListTransactionAdapter(@NonNull List<IncomeEntry> listIncome, @Nullable List<String> listExpense){
        mListIncome = listIncome;
        mListExpense = listExpense;
    }

    public ListTransactionAdapter(@NonNull List<IncomeEntry> listIncome){
        mListIncome = listIncome;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        observer.onChanged();
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        observer.onInvalidated();
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch (groupPosition){
            case 0 :
                if(mListIncome != null){
                    return mListIncome.size();
                }
                else return 0;
            case 1 :
                if(mListExpense != null){
                    return mListExpense.size();
                }else return 0;
            default: return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_layout,parent,false);

        mTextViewGroupDis = rootView.findViewById(R.id.textViewGroupDisplay);

        if(groupPosition == 0){
            mTextViewGroupDis.setText(parent.getContext().getResources().getString(R.string.income));
            mTextViewGroupDis.setBackgroundColor(parent.getContext().getColor(R.color.colorDefTouchAddIncome));
        }
        if(groupPosition == 1){
            mTextViewGroupDis.setText(parent.getContext().getResources().getString(R.string.expense));
            mTextViewGroupDis.setBackgroundColor(parent.getContext().getColor(R.color.colorDefTouchAddExpense));
        }

        return rootView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_list_layout,parent,false);

        mTextViewTrans = rootView.findViewById(R.id.textViewTrans);
        mTextViewTransCateName = rootView.findViewById(R.id.textViewTransCateName);
        mTextViewTransDate = rootView.findViewById(R.id.textViewTransDate);

        if(groupPosition == 0 && mListIncome != null){
            mTextViewTransDate.setText(mListIncome.get(childPosition).getDate());
            mTextViewTransCateName.setText(mListIncome.get(childPosition).getInCateName());
            mTextViewTrans.setText(String.valueOf(mListIncome.get(childPosition).getAmount()));
        }
        if(groupPosition == 1 && mListExpense != null){
            mTextViewTransDate.setText(mListExpense.get(childPosition));
            mTextViewTransCateName.setText(mListExpense.get(childPosition));
            mTextViewTrans.setText(mListExpense.get(childPosition));
        }

        return rootView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
