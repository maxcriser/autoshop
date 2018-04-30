package com.example.maksim_zakharenka.autoshop;

import android.content.Context;
import android.content.Intent;

import com.example.maksim_zakharenka.autoshop.activity.admin.AdminActivity;
import com.example.maksim_zakharenka.autoshop.activity.client.MainActivity;

public final class ActivityUtils {

    public static void openApp(final Context pContext, final boolean pAsAdmin) {
        AppSettings.setAdmin(pAsAdmin);

        if (pAsAdmin) {
            pContext.startActivity(new Intent(pContext, AdminActivity.class));
        } else {
            pContext.startActivity(new Intent(pContext, MainActivity.class));
        }
        }
}
