package com.example.maksim_zakharenka.autoshop.activity.client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.Toolbar;
import com.example.maksim_zakharenka.autoshop.adapter.MyTrashAdapter;
import com.example.maksim_zakharenka.autoshop.executable.MyTrashExecutable;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;

import java.util.List;

public class MyTrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trash);
        ((Toolbar)findViewById(R.id.toolbar)).showBackView();

        updateRecyclerView();
    }

    @SuppressLint("SetTextI18n")
    private void updateFullPrice(final List<ProductModel> trashProductModelList) {
        final TextView fullPrice = findViewById(R.id.full_price);
        fullPrice.setText("Общая стоимость: " + String.valueOf(getFullPriceString(trashProductModelList)) + "$");
    }

    private long getFullPriceString(final List<ProductModel> trashProductModelList) {
        long price = 0;

        for (final ProductModel productModel : trashProductModelList) {
            price += productModel.getPrice() * productModel.getCount();
        }

        return price;
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.tra_recycler_view);

        final List<ProductModel> trashProductModelList = new MyTrashExecutable().execute();

        if (trashProductModelList != null && trashProductModelList.isEmpty()) {
            showEmptyView();

            return;
        } else {
            hideEmptyView();
        }

        updateFullPrice(trashProductModelList);

        final MyTrashAdapter mAdapter = new MyTrashAdapter(trashProductModelList);

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