package com.example.maksim_zakharenka.autoshop.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public ProductsExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public List<ProductModel> execute() {
        final List<ProductModel> list = new ArrayList<>();

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

        final String selection = ProductModel.Model.COUNT + " > ?";
        final String[] selectionArgs = new String[]{"0"};

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
            final String photo = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.PHOTO));
            final String name = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.NAME));
            final String category = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.CATEGORY));
            final String description = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.DESCRIPTION));
            final String number = cursor.getString(cursor.getColumnIndexOrThrow(ProductModel.Model.NUMBER));
            final int count = cursor.getInt(cursor.getColumnIndexOrThrow(ProductModel.Model.COUNT));
            final int price = cursor.getInt(cursor.getColumnIndexOrThrow(ProductModel.Model.PRICE));

            list.add(new ProductModel(name, category, description, number, count, price, photo));
        }

        cursor.close();
        db.close();

        return list;
    }
}