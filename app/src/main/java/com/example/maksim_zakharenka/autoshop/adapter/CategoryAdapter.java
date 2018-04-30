package com.example.maksim_zakharenka.autoshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ManufacturersViewHolder> {

    private final List<CategoryModel> mList;

    class ManufacturersViewHolder extends RecyclerView.ViewHolder {

        TextView name, country, site;

        ManufacturersViewHolder(final View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            site = (TextView) view.findViewById(R.id.country);
            country = (TextView) view.findViewById(R.id.site);
        }
    }

    public CategoryAdapter(final List<CategoryModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ManufacturersViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category, parent, false);

        return new ManufacturersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ManufacturersViewHolder holder, final int position) {
        final CategoryModel item = mList.get(position);

        holder.name.setText(item.getCategory());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}