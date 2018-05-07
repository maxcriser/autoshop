package com.example.maksim_zakharenka.autoshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;

import java.util.List;

public class MyTrashAdapter extends RecyclerView.Adapter<MyTrashAdapter.AdminProductsViewHolder> {

    private final List<ProductModel> mList;

    class AdminProductsViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mCategory;
        TextView mDescription;
        TextView mNumber;
        TextView mPrice;

        AdminProductsViewHolder(final View view) {
            super(view);

            mName = view.findViewById(R.id.name);
            mCategory = view.findViewById(R.id.category);
            mDescription = view.findViewById(R.id.description);
            mNumber = view.findViewById(R.id.number);
            mPrice = view.findViewById(R.id.price);
        }
    }

    public MyTrashAdapter(final List<ProductModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdminProductsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_trash, parent, false);

        return new AdminProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminProductsViewHolder holder, final int position) {
        final ProductModel item = mList.get(position);

        holder.mName.setText(item.getName());
        holder.mCategory.setText(item.getCategory());
        holder.mDescription.setText(item.getDescription());
        holder.mNumber.setText(item.getNumber());
        holder.mPrice.setText(String.valueOf(item.getPrice() * item.getCount()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}