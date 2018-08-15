package com.example.chhan.spendingtracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chhan.spendingtracker.AddCateExpense;
import com.example.chhan.spendingtracker.AddCateIncome;
import com.example.chhan.spendingtracker.R;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    TextView mTextViewAddInCate;
    TextView mTextViewAddExCate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.settings_layout,container,false);

        mTextViewAddInCate = rootView.findViewById(R.id.textViewAddInCate);
        mTextViewAddInCate.setOnClickListener(this);

        mTextViewAddExCate = rootView.findViewById(R.id.textViewAddInEx);
        mTextViewAddExCate.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textViewAddInCate){
            Intent addInCateActivity = new Intent(getContext(), AddCateIncome.class);
            startActivity(addInCateActivity);
        }else{
            Intent addExCateActivity = new Intent(getContext(), AddCateExpense.class);
            startActivity(addExCateActivity);
        }
    }
}
