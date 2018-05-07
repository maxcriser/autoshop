package com.example.maksim_zakharenka.autoshop.executable;

import com.example.maksim_zakharenka.autoshop.model.ProductModel;
import com.example.maksim_zakharenka.autoshop.model.TrashModel;

import java.util.ArrayList;
import java.util.List;

public class MyTrashExecutable {

    public MyTrashExecutable() {

    }

    public List<ProductModel> execute() {
        final List<TrashModel> trashModelList = new TrashBySavedUsernameExecutable().execute();
        final List<ProductModel> trashProducts = new ArrayList<>();

        for (final TrashModel trashModel : trashModelList) {
            trashProducts.add(new ProductByNumberExecutable(trashModel.getNumber()).execute());
        }

        return trashProducts;
    }
}