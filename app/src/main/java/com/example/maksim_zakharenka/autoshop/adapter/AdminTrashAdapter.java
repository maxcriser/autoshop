package com.example.maksim_zakharenka.autoshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.model.TrashModel;

import java.util.List;

public class AdminTrashAdapter extends RecyclerView.Adapter<AdminTrashAdapter.AdminProductsViewHolder> {

    private final List<TrashModel> mList;

    class AdminProductsViewHolder extends RecyclerView.ViewHolder {

        TextView mClient;
        TextView mNumber;
        TextView mCount;

        AdminProductsViewHolder(final View view) {
            super(view);

            mClient = view.findViewById(R.id.client);
            mNumber = view.findViewById(R.id.number);
            mCount = view.findViewById(R.id.count);
        }
    }

    public AdminTrashAdapter(final List<TrashModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdminProductsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_admin_trash, parent, false);

        return new AdminProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminProductsViewHolder holder, final int position) {
        final TrashModel item = mList.get(position);

        holder.mClient.setText(item.getClient());
        holder.mNumber.setText(item.getNumber());
        holder.mCount.setText(String.valueOf(item.getCount()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}