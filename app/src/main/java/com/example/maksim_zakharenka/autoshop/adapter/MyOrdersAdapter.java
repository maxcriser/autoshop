package com.example.maksim_zakharenka.autoshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.model.OrderModel;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.AdminProductsViewHolder> {

    private final List<OrderModel> mList;

    class AdminProductsViewHolder extends RecyclerView.ViewHolder {

        TextView mPhone;
        TextView mIndex;
        TextView mAddress;

        AdminProductsViewHolder(final View view) {
            super(view);

            mPhone = view.findViewById(R.id.phone);
            mIndex = view.findViewById(R.id.index);
            mAddress = view.findViewById(R.id.address);
        }
    }

    public MyOrdersAdapter(final List<OrderModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdminProductsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_orders, parent, false);

        return new AdminProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminProductsViewHolder holder, final int position) {
        final OrderModel item = mList.get(position);

        holder.mPhone.setText(item.getPhone());
        holder.mIndex.setText(item.getIndex());
        holder.mAddress.setText(item.getAddress());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}