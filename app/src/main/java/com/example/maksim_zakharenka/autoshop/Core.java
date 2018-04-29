package com.example.maksim_zakharenka.autoshop;

import android.app.Application;

import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;

public class Core extends Application {

    public Core() {
        ContextHolder.set(this);
        DatabaseHolder.set(new AppDataBaseHelper(this));
    }
}