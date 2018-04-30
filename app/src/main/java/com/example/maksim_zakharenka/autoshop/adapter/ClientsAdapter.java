package com.example.maksim_zakharenka.autoshop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.model.AccountModel;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ManufacturersViewHolder> {

    private final List<AccountModel> mList;

    class ManufacturersViewHolder extends RecyclerView.ViewHolder {

        TextView password, login, country, fullName;

        ManufacturersViewHolder(final View view) {
            super(view);
            login = (TextView) view.findViewById(R.id.login);
            fullName = (TextView) view.findViewById(R.id.full_name);
            password = (TextView) view.findViewById(R.id.password);
            country = (TextView) view.findViewById(R.id.country);
        }
    }

    public ClientsAdapter(final List<AccountModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ManufacturersViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_clients, parent, false);

        return new ManufacturersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ManufacturersViewHolder holder, final int position) {
        final AccountModel item = mList.get(position);

        holder.login.setText(item.getLogin());
        holder.fullName.setText(item.getFullName());
        holder.password.setText(item.getPassword());
        holder.country.setText(item.getCountry());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}