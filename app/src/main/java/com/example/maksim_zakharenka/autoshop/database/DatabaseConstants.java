package com.example.maksim_zakharenka.autoshop.database;

import com.example.maksim_zakharenka.autoshop.model.AccountModel;
import com.example.maksim_zakharenka.autoshop.model.CategoryModel;
import com.example.maksim_zakharenka.autoshop.model.ManufacturerModel;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;
import com.example.maksim_zakharenka.autoshop.model.TrashModel;

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

        String SQL_DELETE_ACCOUNT_ENTRIES =
                "DROP TABLE IF EXISTS " + AccountModel.Model.TABLE;

        String SQL_CREATE_MANUFACTURER_ENTRIES =
                "CREATE TABLE " + ManufacturerModel.Model.TABLE + " (" +
                        ManufacturerModel.Model.ID + " INTEGER PRIMARY KEY," +
                        ManufacturerModel.Model.NAME + " TEXT," +
                        ManufacturerModel.Model.COUNTRY + " TEXT," +
                        ManufacturerModel.Model.SITE + " TEXT)";

        String SQL_DELETE_MANUFACTURER_ENTRIES =
                "DROP TABLE IF EXISTS " + ManufacturerModel.Model.TABLE;

        String SQL_CREATE_CATEGORY_ENTRIES =
                "CREATE TABLE " + CategoryModel.Model.TABLE + " (" +
                        CategoryModel.Model.ID + " INTEGER PRIMARY KEY," +
                        CategoryModel.Model.CATEGORY + " TEXT)";

        String SQL_DELETE_CATEGORY_ENTRIES =
                "DROP TABLE IF EXISTS " + CategoryModel.Model.TABLE;

        String SQL_CREATE_PRODUCT_ENTRIES =
                "CREATE TABLE " + ProductModel.Model.TABLE + " (" +
                        ProductModel.Model.ID + " INTEGER PRIMARY KEY," +
                        ProductModel.Model.COUNT + " INT," +
                        ProductModel.Model.NAME + " TEXT," +
                        ProductModel.Model.PRICE + " TEXT," +
                        ProductModel.Model.DESCRIPTION + " TEXT," +
                        ProductModel.Model.NUMBER + " TEXT," +
                        ProductModel.Model.CATEGORY + " TEXT)";

        String SQL_DELETE_PRODUCT_ENTRIES =
                "DROP TABLE IF EXISTS " + ProductModel.Model.TABLE;

        String SQL_CREATE_TRASH_ENTRIES =
                "CREATE TABLE " + TrashModel.Model.TABLE + " (" +
                        TrashModel.Model.ID + " INTEGER PRIMARY KEY," +
                        TrashModel.Model.COUNT + " INT," +
                        TrashModel.Model.CLIENT + " TEXT," +
                        ProductModel.Model.NUMBER + " TEXT)";

        String SQL_DELETE_TRASH_ENTRIES =
                "DROP TABLE IF EXISTS " + TrashModel.Model.TABLE;
    }
}