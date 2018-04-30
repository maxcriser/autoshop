package com.example.maksim_zakharenka.autoshop;

import android.content.Context;

import com.example.maksim_zakharenka.autoshop.R;

import java.util.Arrays;
import java.util.List;

public final class ResourcesUtils {

    public static List<String> getCategories(final Context pContext) {
        return Arrays.asList(pContext.getResources().getStringArray(R.array.product_categories));
    }
}
