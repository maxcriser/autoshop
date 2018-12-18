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
        db.execSQL(DatabaseConstants.SQL.SQL_CREATE_MANUFACTURER_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_CREATE_CATEGORY_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_CREATE_PRODUCT_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_CREATE_ORDER_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_CREATE_TRASH_ENTRIES);
    }

    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL(DatabaseConstants.SQL.SQL_DELETE_ACCOUNT_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_DELETE_MANUFACTURER_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_DELETE_CATEGORY_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_DELETE_PRODUCT_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_DELETE_ORDER_ENTRIES);
        db.execSQL(DatabaseConstants.SQL.SQL_DELETE_TRASH_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
