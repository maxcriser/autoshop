package com.example.maksim_zakharenka.autoshop.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.AppSettings;
import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderBySavedUsernameExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public OrderBySavedUsernameExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public List<OrderModel> execute() {
        final List<OrderModel> list = new ArrayList<>();

        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                OrderModel.Model.ID,
                OrderModel.Model.CLIENT,
                OrderModel.Model.ADDRESS,
                OrderModel.Model.INDEX,
                OrderModel.Model.PHONE
        };

        final String selection = OrderModel.Model.CLIENT + " = ?";
        final String[] selectionArgs = new String[]{AppSettings.getUserName()};

        final Cursor cursor = db.query(
                OrderModel.Model.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String phone = cursor.getString(cursor.getColumnIndexOrThrow(OrderModel.Model.PHONE));
            final String client = cursor.getString(cursor.getColumnIndexOrThrow(OrderModel.Model.CLIENT));
            final String index = cursor.getString(cursor.getColumnIndexOrThrow(OrderModel.Model.ID));
            final String address = cursor.getString(cursor.getColumnIndexOrThrow(OrderModel.Model.ADDRESS));

            list.add(new OrderModel(client, address, index, phone));
        }

        cursor.close();
        db.close();

        return list;
    }
}