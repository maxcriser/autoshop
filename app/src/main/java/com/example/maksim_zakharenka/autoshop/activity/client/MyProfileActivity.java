package com.example.maksim_zakharenka.autoshop.activity.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.AppSettings;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.executable.AccountInfoExecutable;
import com.example.maksim_zakharenka.autoshop.model.AccountModel;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        final AccountModel accountModel = new AccountInfoExecutable(AppSettings.getUserName()).execute();

        final TextView login = findViewById(R.id.login);
        final TextView fullName = findViewById(R.id.full_name);
        final TextView country = findViewById(R.id.country);

        login.setText(accountModel.getLogin());
        fullName.setText(accountModel.getFullName());
        country.setText(accountModel.getCountry());
    }
}