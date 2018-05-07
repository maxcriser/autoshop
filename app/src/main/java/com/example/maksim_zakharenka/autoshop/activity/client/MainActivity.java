package com.example.maksim_zakharenka.autoshop.activity.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.maksim_zakharenka.autoshop.LogOutCallback;
import com.example.maksim_zakharenka.autoshop.R;

public class MainActivity extends AppCompatActivity {

    public void onProfileClick(final View view) {
        startActivity(new Intent(this, MyProfileActivity.class));
    }

    public void onMyTrashClick(final View view) {
        startActivity(new Intent(this, MyTrashActivity.class));
    }

    public void onProductsClick(final View view) {
        startActivity(new Intent(this, ProductsActivity.class));
    }

    public void onLogOut(final View view) {
        LogOutCallback.onLogOut(getBaseContext());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}