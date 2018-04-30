package com.example.maksim_zakharenka.autoshop.model;

public final class CategoryModel {

    private final String mCategory;

    public CategoryModel(final String pCategory) {
        mCategory = pCategory;
    }

    public String getCategory() {
        return mCategory;
    }

    public static class Model {

        public static final String TABLE = CategoryModel.class.getSimpleName();

        public static final String ID = "_id";

        public static final String CATEGORY = "category";
    }
}