package com.example.maksim_zakharenka.autoshop.activity.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.Toolbar;
import com.example.maksim_zakharenka.autoshop.adapter.MyOrdersAdapter;
import com.example.maksim_zakharenka.autoshop.executable.MyOrdersExecutable;
import com.example.maksim_zakharenka.autoshop.model.OrderModel;

import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        ((Toolbar) findViewById(R.id.toolbar)).showBackView();

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.tra_recycler_view);

        final List<OrderModel> trashOrderModelList = new MyOrdersExecutable().execute();

        if (trashOrderModelList != null && trashOrderModelList.isEmpty()) {
            showEmptyView();

            return;
        } else {
            hideEmptyView();
        }

        final MyOrdersAdapter mAdapter = new MyOrdersAdapter(trashOrderModelList);

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