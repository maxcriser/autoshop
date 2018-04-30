package com.example.maksim_zakharenka.autoshop.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.maksim_zakharenka.autoshop.R;

public class AdminActivity extends AppCompatActivity {

    public void onManufacturersClick(final View view) {
        startActivity(new Intent(this, ManufacturersActivity.class));
    }

    public void onCategoriesClick(final View view) {
        startActivity(new Intent(this, CategoriesActivity.class));
    }

    public void onClientsClick(final View view) {
        startActivity(new Intent(this, ClientsActivity.class));
    }

    public void onProductsClick(final View view) {
        startActivity(new Intent(this, AdminProductsActivity.class));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
}