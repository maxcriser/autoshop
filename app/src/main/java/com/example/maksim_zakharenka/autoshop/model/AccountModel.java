package com.example.maksim_zakharenka.autoshop.model;

public final class AccountModel {

    private final String mLogin;
    private final String mPassword;
    private final String mFullName;
    private final String mCountry;

    public AccountModel(final String pLogin, final String pPassword, final String pFullName, final String pCountry) {
        mLogin = pLogin;
        mPassword = pPassword;
        mFullName = pFullName;
        mCountry = pCountry;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getCountry() {
        return mCountry;
    }

    public static class Model {

        public static final String TABLE = AccountModel.class.getSimpleName();

        public static final String ID = "_id";

        public static final String LOGIN = "login";

        public static final String PASSWORD = "password";

        public static final String FULL_NAME = "full_name";

        public static final String COUNTRY = "country";
    }
}