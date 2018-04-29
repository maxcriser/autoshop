package com.example.maksim_zakharenka.autoshop.database;

public enum DatabaseHolder {

    INSTANCE;

    private AppDataBaseHelper mAppDataBaseHelper;

    public static AppDataBaseHelper get() {
        return INSTANCE.mAppDataBaseHelper;
    }

    public static void set(final AppDataBaseHelper pAppDataBaseHelper) {
        INSTANCE.mAppDataBaseHelper = pAppDataBaseHelper;
    }

}