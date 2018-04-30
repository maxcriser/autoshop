package com.example.maksim_zakharenka.autoshop.executable.check;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.ManufacturerModel;

public class CheckManufacturerExistExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;
    private final String mName;

    public CheckManufacturerExistExecutable(final String pName) {
        mAppDataBaseHelper = DatabaseHolder.get();
        mName = pName;
    }

    public ManufacturerModel execute() {
        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                ManufacturerModel.Model.ID,
                ManufacturerModel.Model.NAME,
                ManufacturerModel.Model.COUNTRY,
                ManufacturerModel.Model.SITE
        };

        final String selection = ManufacturerModel.Model.NAME + " = ?";
        final String[] selectionArgs = new String[]{mName};

        final Cursor cursor = db.query(
                ManufacturerModel.Model.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String name = cursor.getString(cursor.getColumnIndexOrThrow(ManufacturerModel.Model.NAME));
            final String country = cursor.getString(cursor.getColumnIndexOrThrow(ManufacturerModel.Model.COUNTRY));
            final String site = cursor.getString(cursor.getColumnIndexOrThrow(ManufacturerModel.Model.SITE));

            return new ManufacturerModel(name, country, site);
        }

        cursor.close();
        db.close();

        return null;
    }
}