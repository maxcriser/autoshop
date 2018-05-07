package com.example.maksim_zakharenka.autoshop;

import android.content.Context;
import android.content.Intent;

public final class LogOutCallback {

    public static void onLogOut(final Context pContext) {
        AppSettings.setUserName("");
        AppSettings.setPassword("");

        final Intent i = pContext.getPackageManager().getLaunchIntentForPackage(pContext.getPackageName());

        if (i != null) {
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        pContext.startActivity(i);
    }
}
