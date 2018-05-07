package com.example.maksim_zakharenka.autoshop.executable.check;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;

public class CheckProductExistExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;
    private final String mNumber;

    public CheckProductExistExecutable(final String pNumber) {
        mAppDataBaseHelper = DatabaseHolder.get();
        mNumber = pNumber;
    }

    public ProductModel execute() {
        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                ProductModel.Model.ID,
                ProductModel.Model.NUMBER
        };

        final String selection = ProductModel.Model.NUMBER + " = ?";
        final String[] selectionArgs = new String[]{mNumber};

        final Cursor cursor = db.query(
                ProductModel.Model.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String number = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.NUMBER));

            return new ProductModel(null, null, null, number, 0, 0);
        }

        cursor.close();
        db.close();

        return null;
    }
}