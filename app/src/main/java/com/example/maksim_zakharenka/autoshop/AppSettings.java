package com.example.maksim_zakharenka.autoshop;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public final class AppSettings {

    public static String DIVIDER = "~DIVIDER~THECRISER~DIVIDER~";

    private static final String SP_IS_ADMIN = "SP_IS_ADMIN";

    public static void setAdmin(final boolean pIsAdmin) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();

        editor.putBoolean(SP_IS_ADMIN, pIsAdmin);
        editor.apply();
    }

    public static boolean isAdmin() {
        return getSharedPreferences().getBoolean(SP_IS_ADMIN, true);
    }

    private static SharedPreferences getSharedPreferences() {
        return ContextHolder.get().getSharedPreferences("auto_shop_shared_preferences", MODE_PRIVATE);
    }
}