package com.example.chhan.spendingtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.chhan.spendingtracker.fragment.AccountFragment;
import com.example.chhan.spendingtracker.fragment.SettingsFragment;
import com.example.chhan.spendingtracker.fragment.TransactionFragment;
import com.example.chhan.spendingtracker.helper.BottomNavigationViewHelper;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView mNavBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavBottom = findViewById(R.id.navBottombar);
        mNavBottom.setOnNavigationItemSelectedListener(this);
        mNavBottom.setSelectedItemId(R.id.action_account);

        BottomNavigationViewHelper.disableShiftMode(mNavBottom);
        //
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAddIncome){
            Intent addIncomeActivity = new Intent(this,AddIncome.class);
            startActivity(addIncomeActivity);
        }else{
            Intent addExActivity = new Intent(this,AddExpense.class);
            startActivity(addExActivity);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fm  = getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.action_account :
                AccountFragment fragment = new AccountFragment();
                fm.beginTransaction()
                        .replace(R.id.frameMain,fragment)
                        .commit();
                break;

            case R.id.action_settings :
                SettingsFragment fragment1 = new SettingsFragment();
                fm.beginTransaction()
                        .replace(R.id.frameMain,fragment1)
                        .commit();
                break;

            case R.id.action_trans :
                TransactionFragment fragment2 = new TransactionFragment();
                fm.beginTransaction()
                        .replace(R.id.frameMain,fragment2)
                        .commit();
                break;
        }
        return true;
    }
}
