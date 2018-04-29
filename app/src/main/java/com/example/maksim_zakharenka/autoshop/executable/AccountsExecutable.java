package com.example.maksim_zakharenka.autoshop.executable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountsExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public AccountsExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public List<AccountModel> execute() {
        final List<AccountModel> list = new ArrayList<>();

        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                AccountModel.Model.ID,
                AccountModel.Model.LOGIN,
                AccountModel.Model.PASSWORD,
                AccountModel.Model.COUNTRY,
                AccountModel.Model.FULL_NAME
        };

        final Cursor cursor = db.query(
                AccountModel.Model.TABLE,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String name = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.LOGIN));
            final String password = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.PASSWORD));
            final String country = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.COUNTRY));
            final String fullName = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.FULL_NAME));

            list.add(new AccountModel(name, password, fullName, country));
        }

        cursor.close();
        db.close();

        return list;
    }
}