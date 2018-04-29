package com.example.maksim_zakharenka.autoshop;

import android.content.Context;
import android.content.Intent;

import com.example.maksim_zakharenka.autoshop.activity.MainActivity;

public final class ActivityUtils {

    public static void openApp(final Context pContext, final boolean pAsAdmin) {
        AppSettings.setAdmin(pAsAdmin);


        pContext.startActivity(new Intent(pContext, MainActivity.class));
    }
}
