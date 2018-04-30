package com.example.maksim_zakharenka.autoshop.model;

public final class ManufacturerModel {

    private final String mName;
    private final String mCountry;
    private final String mSite;

    public ManufacturerModel(final String pName, final String pCountry, final String pSite) {
        mName = pName;
        mCountry = pCountry;
        mSite = pSite;
    }

    public String getName() {
        return mName;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getSite() {
        return mSite;
    }

    public static class Model {

        public static final String TABLE = ManufacturerModel.class.getSimpleName();

        public static final String ID = "_id";

        public static final String NAME = "name";

        public static final String COUNTRY = "country";

        public static final String SITE = "site";
    }
}