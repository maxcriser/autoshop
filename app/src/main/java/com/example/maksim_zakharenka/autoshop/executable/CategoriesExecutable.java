package com.example.maksim_zakharenka.autoshop.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public CategoriesExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public List<CategoryModel> execute() {
        final List<CategoryModel> list = new ArrayList<>();

        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                CategoryModel.Model.ID,
                CategoryModel.Model.CATEGORY
        };

        final Cursor cursor = db.query(
                CategoryModel.Model.TABLE,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String category = cursor.getString(cursor.getColumnIndexOrThrow(CategoryModel.Model.CATEGORY));

            list.add(new CategoryModel(category));
        }

        cursor.close();
        db.close();

        return list;
    }
}