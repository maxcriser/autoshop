package com.example.maksim_zakharenka.autoshop.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.adapter.AdminTrashAdapter;
import com.example.maksim_zakharenka.autoshop.executable.AdminTrashExecutable;
import com.example.maksim_zakharenka.autoshop.model.TrashModel;

import java.util.List;

public class AdminTrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_trash);

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.admin_trash_recycler_view);

        final List<TrashModel> trashModelList = new AdminTrashExecutable().execute();

        if (trashModelList != null && trashModelList.isEmpty()) {
            showEmptyView();

            return;
        } else {
            hideEmptyView();
        }

        final AdminTrashAdapter mAdapter = new AdminTrashAdapter(trashModelList);
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