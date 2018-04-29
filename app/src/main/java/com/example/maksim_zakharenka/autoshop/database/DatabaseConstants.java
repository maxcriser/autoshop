package com.example.maksim_zakharenka.autoshop.database;

import com.example.maksim_zakharenka.autoshop.model.AccountModel;

public class DatabaseConstants {

    public interface Info {

        int DATABASE_VERSION = 1;

        String DATABASE_NAME = "auto.shop.db";
    }

    public interface SQL {

        String SQL_CREATE_ACCOUNT_ENTRIES =
                "CREATE TABLE " + AccountModel.Model.TABLE + " (" +
                        AccountModel.Model.ID + " INTEGER PRIMARY KEY," +
                        AccountModel.Model.LOGIN + " TEXT," +
                        AccountModel.Model.PASSWORD + " TEXT," +
                        AccountModel.Model.FULL_NAME + " TEXT," +
                        AccountModel.Model.COUNTRY + " TEXT)";

        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + AccountModel.Model.TABLE;
    }
}