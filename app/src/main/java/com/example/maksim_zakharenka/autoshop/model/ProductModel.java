package com.example.maksim_zakharenka.autoshop.model;

public final class ProductModel {

    private final String mName;
    private final String mCategory;
    private final String mDescription;
    private final String mNumber;
    private final String mPhotoPath;
    private final int mCount;
    private final int mPrice;

    public ProductModel(final String pName, final String pCategory, final String pDescription, final String pNumber, final int pCount, final int pPrice, final String pPhotoPath) {
        mName = pName;
        mCategory = pCategory;
        mDescription = pDescription;
        mNumber = pNumber;
        mCount = pCount;
        mPrice = pPrice;
        mPhotoPath = pPhotoPath;
    }

    public String getPhotoPath() {
        return mPhotoPath;
    }

    public int getCount() {
        return mCount;
    }

    public String getName() {
        return mName;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getNumber() {
        return mNumber;
    }

    public int getPrice() {
        return mPrice;
    }

    public static class Model {

        public static final String TABLE = ProductModel.class.getSimpleName();

        public static final String ID = "_id";

        public static final String COUNT = "count";

        public static final String NAME = "name";

        public static final String CATEGORY = "category";

        public static final String DESCRIPTION = "description";

        public static final String NUMBER = "number";

        public static final String PRICE = "price";

        public static final String PHOTO = "photo";
    }
}