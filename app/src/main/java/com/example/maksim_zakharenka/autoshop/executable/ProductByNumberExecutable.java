package com.example.maksim_zakharenka.autoshop.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;

public class ProductByNumberExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;
    private final String mNumber;

    public ProductByNumberExecutable(final String pNumber) {
        mAppDataBaseHelper = DatabaseHolder.get();
        mNumber = pNumber;
    }

    public ProductModel execute() {
        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                ProductModel.Model.ID,
                ProductModel.Model.COUNT,
                ProductModel.Model.NUMBER,
                ProductModel.Model.NAME,
                ProductModel.Model.PHOTO,
                ProductModel.Model.PRICE,
                ProductModel.Model.DESCRIPTION,
                ProductModel.Model.CATEGORY
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
            final String name = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.NAME));
            final String photo = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.PHOTO));
            final String category = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.CATEGORY));
            final String description = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.DESCRIPTION));
            final String number = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.NUMBER));
            final int count = cursor.getInt(cursor.getColumnIndexOrThrow(ProductModel.Model.COUNT));
            final int price = cursor.getInt(cursor.getColumnIndexOrThrow(ProductModel.Model.PRICE));

            return new ProductModel(name, category, description, number, count, price, photo);
        }

        cursor.close();
        db.close();

        return null;
    }
}