package com.example.maksim_zakharenka.autoshop.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.AppSettings;
import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.TrashModel;

import java.util.ArrayList;
import java.util.List;

public class TrashBySavedUsernameExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public TrashBySavedUsernameExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public List<TrashModel> execute() {
        final List<TrashModel> list = new ArrayList<>();

        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                TrashModel.Model.ID,
                TrashModel.Model.COUNT,
                TrashModel.Model.NUMBER,
                TrashModel.Model.CLIENT
        };

        final String selection = TrashModel.Model.CLIENT + " = ?";
        final String[] selectionArgs = new String[]{AppSettings.getUserName()};

        final Cursor cursor = db.query(
                TrashModel.Model.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String client = cursor.getString(cursor.getColumnIndexOrThrow(TrashModel.Model.CLIENT));
            final String number = cursor.getString(cursor.getColumnIndexOrThrow(TrashModel.Model.NUMBER));
            final int count = cursor.getInt(cursor.getColumnIndexOrThrow(TrashModel.Model.COUNT));

            list.add(new TrashModel(client, number, count));
        }

        cursor.close();
        db.close();

        return list;
    }
}