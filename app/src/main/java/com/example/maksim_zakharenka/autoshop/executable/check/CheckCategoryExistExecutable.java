package com.example.maksim_zakharenka.autoshop.executable.check;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.CategoryModel;

public class CheckCategoryExistExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;
    private final String mName;

    public CheckCategoryExistExecutable(final String pName) {
        mAppDataBaseHelper = DatabaseHolder.get();
        mName = pName;
    }

    public CategoryModel execute() {
        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                CategoryModel.Model.ID,
                CategoryModel.Model.CATEGORY
        };

        final String selection = CategoryModel.Model.CATEGORY + " = ?";
        final String[] selectionArgs = new String[]{mName};

        final Cursor cursor = db.query(
                CategoryModel.Model.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String category = cursor.getString(cursor.getColumnIndexOrThrow(CategoryModel.Model.CATEGORY));

            return new CategoryModel(category);
        }

        cursor.close();
        db.close();

        return null;
    }
}