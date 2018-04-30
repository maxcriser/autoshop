package com.example.maksim_zakharenka.autoshop.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.adapter.ClientsAdapter;
import com.example.maksim_zakharenka.autoshop.executable.AccountsExecutable;
import com.example.maksim_zakharenka.autoshop.model.AccountModel;

import java.util.List;

public class ClientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.clients_recycler_view);

        final List<AccountModel> accountModelList = new AccountsExecutable().execute();

        if (accountModelList != null && accountModelList.isEmpty()) {
            showEmptyView();
        } else {
            hideEmptyView();
        }

        final ClientsAdapter mAdapter = new ClientsAdapter(new AccountsExecutable().execute());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void showEmptyView() {
        findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
    }

    private void hideEmptyView() {
        findViewById(R.id.empty_view).setVisibility(View.GONE);
    }
}