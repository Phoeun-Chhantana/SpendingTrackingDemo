package com.example.chhan.spendingtracker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chhan.spendingtracker.adapter.ListCategoryExAdapter;
import com.example.chhan.spendingtracker.data.CategoryData;
import com.example.chhan.spendingtracker.database.AppDatabase;
import com.example.chhan.spendingtracker.database.CategoryExEntry;

import java.util.List;

public class AddCateExpense extends AppCompatActivity implements View.OnClickListener,LoadData {

    Toolbar mToolbarExCate;
    RecyclerView mRecyclerViewExCate;
    ListCategoryExAdapter mAdapter;
    Button mButtonAdd;
    EditText mEditTextAddCateName;

    AppDatabase mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ex_category);

        mButtonAdd = findViewById(R.id.btnAddCateEx);
        mButtonAdd.setOnClickListener(this);
        mEditTextAddCateName = findViewById(R.id.editTextAddExCate);

        mToolbarExCate = findViewById(R.id.toolBarExpenseCate);
        setSupportActionBar(mToolbarExCate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerViewExCate = findViewById(R.id.rvAddExCate);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewExCate.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        mRecyclerViewExCate.addItemDecoration(divider);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        helper.attachToRecyclerView(mRecyclerViewExCate);

        mDb = AppDatabase.getInstance(getApplicationContext());
        retrieveData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            NavUtils.navigateUpTo(this,getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String name = mEditTextAddCateName.getText().toString();
        final CategoryExEntry categoryExEntry = new CategoryExEntry(name);
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.categoryExpenseDao().insertCateEx(categoryExEntry);
            }
        });
    }

    @Override
    public void retrieveData() {
        final LiveData<List<CategoryExEntry>> categoryEx = mDb.categoryExpenseDao().getAllCateEx();
        categoryEx.observe(this, new Observer<List<CategoryExEntry>>() {
            @Override
            public void onChanged(@Nullable List<CategoryExEntry> categoryExEntries) {
                mAdapter = new ListCategoryExAdapter(categoryExEntries);
                mRecyclerViewExCate.setAdapter(mAdapter);
                categoryEx.removeObserver(this);
            }
        });
    }
}
