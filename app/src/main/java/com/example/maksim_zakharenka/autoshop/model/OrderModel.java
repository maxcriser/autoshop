package com.example.maksim_zakharenka.autoshop.model;

public final class OrderModel {

    private final String mClient;
    private final String mAddress;
    private final String mIndex;
    private final String mPhone;

    public OrderModel(final String pClient, final String pAddress, final String pIndex, final String pPhone) {
        mClient = pClient;
        mAddress = pAddress;
        mIndex = pIndex;
        mPhone = pPhone;
    }

    public String getClient() {
        return mClient;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getIndex() {
        return mIndex;
    }

    public String getPhone() {
        return mPhone;
    }

    public static class Model {

        public static final String TABLE = OrderModel.class.getSimpleName();

        public static final String ID = "_id";

        public static final String INDEX = "order_index";

        public static final String CLIENT = "order_client";

        public static final String PHONE = "order_phone";

        public static final String ADDRESS = "order_address";
    }
}