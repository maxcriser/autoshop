package com.example.maksim_zakharenka.autoshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class AdminProductsAdapter extends RecyclerView.Adapter<AdminProductsAdapter.AdminProductsViewHolder> {

    private final List<ProductModel> mList;

    class AdminProductsViewHolder extends RecyclerView.ViewHolder {

        ImageView mPhoto;
        TextView mName;
        TextView mCategory;
        TextView mDescription;
        TextView mNumber;
        TextView mPrice;
        TextView mCount;

        AdminProductsViewHolder(final View view) {
            super(view);

            mName = (TextView) view.findViewById(R.id.name);
            mCategory = (TextView) view.findViewById(R.id.category);
            mDescription = (TextView) view.findViewById(R.id.description);
            mNumber = (TextView) view.findViewById(R.id.number);
            mCount = (TextView) view.findViewById(R.id.count);
            mPrice = (TextView) view.findViewById(R.id.price);
            mPhoto = (ImageView) view.findViewById(R.id.photo);
        }
    }

    public AdminProductsAdapter(final List<ProductModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdminProductsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_admin_products, parent, false);

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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}