package com.example.maksim_zakharenka.autoshop.model;

public final class TrashModel {

    private final String mClient;
    private final String mNumber;
    private final int mCount;

    public TrashModel(final String pClient, final String pNumber, final int pCount) {
        mClient = pClient;
        mNumber = pNumber;
        mCount = pCount;
    }

    public int getCount() {
        return mCount;
    }

    public String getClient() {
        return mClient;
    }

    public String getNumber() {
        return mNumber;
    }

    public static class Model {

        public static final String TABLE = TrashModel.class.getSimpleName();

        public static final String ID = "_id";

        public static final String COUNT = "count";

        public static final String CLIENT = "client";

        public static final String NUMBER = "number";
    }
}