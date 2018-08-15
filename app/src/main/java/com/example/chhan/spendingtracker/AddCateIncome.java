package com.example.chhan.spendingtracker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chhan.spendingtracker.adapter.ListCategoryInAdapter;
import com.example.chhan.spendingtracker.database.AppDatabase;
import com.example.chhan.spendingtracker.database.CategoryInEntry;

import java.util.List;

public class AddCateIncome extends AppCompatActivity implements View.OnClickListener,LoadData{

    Toolbar mToolbarIncomeCate;
    RecyclerView mRecyclerViewInCate;
    ListCategoryInAdapter mAdapter;
    Button mButtonAdd;
    EditText mEditTextAddCateName;

    AppDatabase mDb;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_in_category);

        mButtonAdd = findViewById(R.id.btnAddCateIn);
        mButtonAdd.setOnClickListener(this);
        mEditTextAddCateName = findViewById(R.id.editTextAddInCate);

        mToolbarIncomeCate = findViewById(R.id.toolBarIncomeCate);
        setSupportActionBar(mToolbarIncomeCate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerViewInCate = findViewById(R.id.rvAddInCate);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewInCate.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        mRecyclerViewInCate.addItemDecoration(divider);

        mAdapter = new ListCategoryInAdapter();
        mRecyclerViewInCate.setAdapter(mAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                String name = mAdapter.getListItems().get(viewHolder.getAdapterPosition()).getCateInName();
                if(name.equals("General Income")){
                    Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                /*AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<CategoryInEntry> categoryInEntries = mAdapter.getListItems();
                        mDb.categoryIncomeDao().deleteCateIn(categoryInEntries.get(position));
                    }
                });*/
            }
        });
        helper.attachToRecyclerView(mRecyclerViewInCate);

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
        String categoryName = mEditTextAddCateName.getText().toString();
        final CategoryInEntry categoryInEntry = new CategoryInEntry(categoryName);
        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.categoryIncomeDao().insertCateIn(categoryInEntry);
                retrieveData();
            }
        });
    }

    @Override
    public void retrieveData() {
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        final LiveData<List<CategoryInEntry>> categoryIn = mDb.categoryIncomeDao().getAllCateIn();
        categoryIn.observe(AddCateIncome.this, new Observer<List<CategoryInEntry>>() {
            @Override
            public void onChanged(@Nullable List<CategoryInEntry> categoryInEntries) {
                Log.d(TAG,"Receiving database update from LiveData");;
                mAdapter.setItems(categoryInEntries);
                categoryIn.removeObserver(this);
            }
        });
    }
}
