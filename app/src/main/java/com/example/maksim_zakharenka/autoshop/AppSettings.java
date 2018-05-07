package com.example.maksim_zakharenka.autoshop;

import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public final class AppSettings {

    private static final String SP_IS_ADMIN = "SP_IS_ADMIN";
    private static final String SP_USERNAME = "SP_USERNAME";
    private static final String SP_PASSWORD = "SP_PASSWORD";

    public static void setAdmin(final boolean pIsAdmin) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();

        editor.putBoolean(SP_IS_ADMIN, pIsAdmin);
        editor.apply();
    }

    public static void setUserName(final String pUsername) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();

        editor.putString(SP_USERNAME, pUsername);
        editor.apply();
    }

    public static void setPassword(final String pPassword) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();

        editor.putString(SP_PASSWORD, pPassword);
        editor.apply();
    }

    public static boolean isAdmin() {
        return getSharedPreferences().getBoolean(SP_IS_ADMIN, true);
    }

    public static String getUserName() {
        return getSharedPreferences().getString(SP_USERNAME, "");
    }

    public static String getPassword() {
        return getSharedPreferences().getString(SP_PASSWORD, "");
    }

    private static SharedPreferences getSharedPreferences() {
        return ContextHolder.get().getSharedPreferences("auto_shop_shared_preferences", MODE_PRIVATE);
    }
}