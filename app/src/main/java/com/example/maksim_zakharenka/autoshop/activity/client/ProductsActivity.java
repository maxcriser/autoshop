package com.example.maksim_zakharenka.autoshop.activity.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.adapter.ProductsAdapter;
import com.example.maksim_zakharenka.autoshop.callback.IUpdate;
import com.example.maksim_zakharenka.autoshop.executable.ProductsExecutable;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;

import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.products_recycler_view);

        final List<ProductModel> productModelList = new ProductsExecutable().execute();

        if (productModelList != null && productModelList.isEmpty()) {
            showEmptyView();

            return;
        } else {
            hideEmptyView();
        }

        final ProductsAdapter mAdapter = new ProductsAdapter(productModelList, new IUpdate() {

            @Override
            public void onUpdate() {
                updateRecyclerView();
            }
        });

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