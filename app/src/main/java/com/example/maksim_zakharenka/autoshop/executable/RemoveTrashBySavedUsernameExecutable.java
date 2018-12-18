package com.example.maksim_zakharenka.autoshop.executable;

import android.database.sqlite.SQLiteDatabase;

import com.example.maksim_zakharenka.autoshop.AppSettings;
import com.example.maksim_zakharenka.autoshop.database.AppDataBaseHelper;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.model.TrashModel;

public class RemoveTrashBySavedUsernameExecutable {

    private final AppDataBaseHelper mAppDataBaseHelper;

    public RemoveTrashBySavedUsernameExecutable() {
        mAppDataBaseHelper = DatabaseHolder.get();
    }

    public void execute() {
        final SQLiteDatabase db = mAppDataBaseHelper.getReadableDatabase();

        final String selection = TrashModel.Model.CLIENT + " = ?";
        final String[] selectionArgs = new String[]{AppSettings.getUserName()};

        db.delete(
                TrashModel.Model.TABLE,
                selection,
                selectionArgs
        );

        db.close();
    }
}