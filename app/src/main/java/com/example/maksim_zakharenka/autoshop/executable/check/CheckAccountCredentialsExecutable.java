package com.example.maksim_zakharenka.autoshop.executable.check;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.AccountModel;

public class CheckAccountCredentialsExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;
    private final String mLogin;
    private final String mPassword;

    public CheckAccountCredentialsExecutable(final String pLogin, final String pPassword) {
        mAppDataBaseHelper = DatabaseHolder.get();
        mLogin = pLogin;
        mPassword = pPassword;
    }

    public AccountModel execute() {
        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String[] projection = {
                AccountModel.Model.ID,
                AccountModel.Model.LOGIN,
                AccountModel.Model.PASSWORD,
                AccountModel.Model.COUNTRY,
                AccountModel.Model.FULL_NAME
        };

        final String selection = AccountModel.Model.LOGIN + " = ? AND " + AccountModel.Model.PASSWORD + " = ?";
        final String[] selectionArgs = new String[]{mLogin, mPassword};

        final Cursor cursor = db.query(
                AccountModel.Model.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            final String login = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.LOGIN));
            final String password = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.PASSWORD));
            final String country = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.COUNTRY));
            final String fullName = cursor.getString(cursor.getColumnIndexOrThrow(AccountModel.Model.FULL_NAME));

            return new AccountModel(login, password, fullName, country);
        }

        cursor.close();
        db.close();

        return null;
    }
}