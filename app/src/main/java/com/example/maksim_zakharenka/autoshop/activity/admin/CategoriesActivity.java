package com.example.maksim_zakharenka.autoshop.activity.admin;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.DialogUtils;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.StringUtils;
import com.example.maksim_zakharenka.autoshop.Toolbar;
import com.example.maksim_zakharenka.autoshop.adapter.CategoryAdapter;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.executable.CategoriesExecutable;
import com.example.maksim_zakharenka.autoshop.executable.check.CheckCategoryExistExecutable;
import com.example.maksim_zakharenka.autoshop.model.CategoryModel;

public class CategoriesActivity extends AppCompatActivity {

    public void onAddManufacturer(final View view) {
        final String category = ((TextView) findViewById(R.id.categiry_edittext)).getText().toString();

        if (StringUtils.isEmpty(category)) {
            DialogUtils.showErrorDialog(this, "Ошибка добавления категории", "Заполните все поля и попытайтесь снова");

            return;
        }

        final CategoryModel categoryModel = new CheckCategoryExistExecutable(category).execute();

        if (categoryModel != null) {
            DialogUtils.showErrorDialog(this, "Ошибка добавления категории", "Данная категория уже существует");

            return;
        }

        addItem(category);
        updateRecyclerView();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ((Toolbar)findViewById(R.id.toolbar)).showBackView();

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.categories_recycler_view);

        final CategoryAdapter mAdapter = new CategoryAdapter(new CategoriesExecutable().execute());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void addItem(final String pCategory) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues newCategory = new ContentValues();

        newCategory.put(CategoryModel.Model.ID, (Integer) null);
        newCategory.put(CategoryModel.Model.CATEGORY, pCategory);

        mDatabase.insert(CategoryModel.Model.TABLE, null, newCategory);
        mDatabase.close();
    }
}