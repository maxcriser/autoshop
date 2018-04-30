package com.example.maksim_zakharenka.autoshop.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.ManufacturerModel;

import java.util.ArrayList;
import java.util.List;

public class ManufacturersExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public ManufacturersExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public List<ManufacturerModel> execute() {
        final List<ManufacturerModel> list = new ArrayList<>();

        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                ManufacturerModel.Model.ID,
                ManufacturerModel.Model.NAME,
                ManufacturerModel.Model.COUNTRY,
                ManufacturerModel.Model.SITE
        };

        final Cursor cursor = db.query(
                ManufacturerModel.Model.TABLE,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String name = cursor.getString(cursor.getColumnIndexOrThrow(ManufacturerModel.Model.NAME));
            final String site = cursor.getString(cursor.getColumnIndexOrThrow(ManufacturerModel.Model.SITE));
            final String country = cursor.getString(cursor.getColumnIndexOrThrow(ManufacturerModel.Model.COUNTRY));

            list.add(new ManufacturerModel(name, country, site));
        }

        cursor.close();
        db.close();

        return list;
    }
}