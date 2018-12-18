package com.example.maksim_zakharenka.autoshop.adapter;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.AppSettings;
import com.example.maksim_zakharenka.autoshop.ContextHolder;
import com.example.maksim_zakharenka.autoshop.DialogUtils;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.callback.IUpdate;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.executable.ProductByNumberExecutable;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;
import com.example.maksim_zakharenka.autoshop.model.TrashModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.AdminProductsViewHolder> {

    private final List<ProductModel> mList;
    private final IUpdate mIUpdate;

    class AdminProductsViewHolder extends RecyclerView.ViewHolder {

        ImageView mPhoto;
        TextView mName;
        TextView mCategory;
        TextView mDescription;
        TextView mNumber;
        TextView mPrice;
        TextView mCount;
        NumberPicker mSelectedCount;
        Button mAddButton;

        AdminProductsViewHolder(final View view) {
            super(view);

            mName = view.findViewById(R.id.name);
            mCategory = view.findViewById(R.id.category);
            mDescription = view.findViewById(R.id.description);
            mNumber = view.findViewById(R.id.number);
            mCount = view.findViewById(R.id.count);
            mPrice = view.findViewById(R.id.price);
            mSelectedCount = view.findViewById(R.id.number_picker);
            mAddButton = view.findViewById(R.id.add_to_trash);
            mPhoto = view.findViewById(R.id.photo);
        }
    }

    public ProductsAdapter(final List<ProductModel> mList, final IUpdate pIUpdate) {
        this.mList = mList;
        this.mIUpdate = pIUpdate;
    }

    @NonNull
    @Override
    public AdminProductsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_products, parent, false);

        return new AdminProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminProductsViewHolder holder, final int position) {
        final ProductModel item = mList.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(new File(item.getPhotoPath()))
                .into(holder.mPhoto);

        holder.mName.setText(item.getName());
        holder.mCategory.setText(item.getCategory());
        holder.mDescription.setText(item.getDescription());
        holder.mNumber.setText(item.getNumber());
        holder.mCount.setText(String.valueOf(item.getCount()));
        holder.mPrice.setText(String.valueOf(item.getPrice()));
        holder.mSelectedCount.setMinValue(1);
        holder.mSelectedCount.setMaxValue(item.getCount());

        holder.mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                final int selectedCount = holder.mSelectedCount.getValue();
                addToMyTrash(selectedCount, holder.mNumber.getText().toString());
                updateProductInfo(selectedCount, holder.mNumber.getText().toString());

                mIUpdate.onUpdate();
            }
        });
    }

    private void updateProductInfo(final int pCount, final String pNumber) {
        final ProductModel productModel = new ProductByNumberExecutable(pNumber).execute();

        if (productModel == null) {
            DialogUtils.showErrorDialog(ContextHolder.get(), "Ошибка...", "Ошибка при выборке элемента при добпалении в корзину.");

            return;
        }

        updateItem(productModel, pCount);
    }

    private void updateItem(final ProductModel pProductModel, final int pMinusCount) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues updatedProduct = new ContentValues();

        updatedProduct.put(ProductModel.Model.PHOTO, pProductModel.getPhotoPath());
        updatedProduct.put(ProductModel.Model.NAME, pProductModel.getName());
        updatedProduct.put(ProductModel.Model.PRICE, pProductModel.getPrice());
        updatedProduct.put(ProductModel.Model.CATEGORY, pProductModel.getCategory());
        updatedProduct.put(ProductModel.Model.DESCRIPTION, pProductModel.getDescription());
        updatedProduct.put(ProductModel.Model.COUNT, pProductModel.getCount() - pMinusCount);
        updatedProduct.put(ProductModel.Model.NUMBER, pProductModel.getNumber());

        mDatabase.update(ProductModel.Model.TABLE, updatedProduct, ProductModel.Model.NUMBER + " = ?", new String[]{pProductModel.getNumber()});
        mDatabase.close();
    }

    private void addToMyTrash(final int pCount, final String pNumber) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues newTrashProduct = new ContentValues();

        newTrashProduct.put(TrashModel.Model.ID, (Integer) null);
        newTrashProduct.put(TrashModel.Model.COUNT, pCount);
        newTrashProduct.put(TrashModel.Model.CLIENT, AppSettings.getUserName());
        newTrashProduct.put(TrashModel.Model.NUMBER, pNumber);

        mDatabase.insert(TrashModel.Model.TABLE, null, newTrashProduct);
        mDatabase.close();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}