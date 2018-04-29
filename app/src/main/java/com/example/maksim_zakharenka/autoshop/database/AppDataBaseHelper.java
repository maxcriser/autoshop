package com.example.maksim_zakharenka.autoshop.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDataBaseHelper extends SQLiteOpenHelper {

    public AppDataBaseHelper(final Context context) {
        super(context, DatabaseConstants.Info.DATABASE_NAME, null, DatabaseConstants.Info.DATABASE_VERSION);
    }

    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(DatabaseConstants.SQL.SQL_CREATE_ACCOUNT_ENTRIES);
    }

    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL(DatabaseConstants.SQL.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
