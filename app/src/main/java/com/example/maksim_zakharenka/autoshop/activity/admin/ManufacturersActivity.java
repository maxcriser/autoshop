package com.example.maksim_zakharenka.autoshop.activity.admin;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.DialogUtils;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.StringUtils;
import com.example.maksim_zakharenka.autoshop.Toolbar;
import com.example.maksim_zakharenka.autoshop.adapter.ManufacturersAdapter;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.executable.check.CheckManufacturerExistExecutable;
import com.example.maksim_zakharenka.autoshop.executable.ManufacturersExecutable;
import com.example.maksim_zakharenka.autoshop.model.ManufacturerModel;

public class ManufacturersActivity extends AppCompatActivity {

    public void onAddManufacturer(final View view) {
        final String name = ((TextView) findViewById(R.id.name_edittext)).getText().toString();
        final String country = ((TextView) findViewById(R.id.country_edittext)).getText().toString();
        final String site = ((TextView) findViewById(R.id.site_edittext)).getText().toString();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(country) || StringUtils.isEmpty(site)) {
            DialogUtils.showErrorDialog(this, "Ошибка добавления производителя", "Заполните все поля и попытайтесь снова");

            return;
        }

        final ManufacturerModel manufacturerModel = new CheckManufacturerExistExecutable(name).execute();

        if (manufacturerModel != null) {
            DialogUtils.showErrorDialog(this, "Ошибка добавления производителя", "Данный производитель уже существует");

            return;
        }

        addItem(name, country, site);
        updateRecyclerView();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);
        ((Toolbar)findViewById(R.id.toolbar)).showBackView();

        updateRecyclerView();
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.manufacturers_recycler_view);

        final ManufacturersAdapter mAdapter = new ManufacturersAdapter(new ManufacturersExecutable().execute());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void addItem(final String pName, final String pCountry, final String pSite) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues newManufacturer = new ContentValues();

        newManufacturer.put(ManufacturerModel.Model.ID, (Integer) null);
        newManufacturer.put(ManufacturerModel.Model.NAME, pName);
        newManufacturer.put(ManufacturerModel.Model.COUNTRY, pCountry);
        newManufacturer.put(ManufacturerModel.Model.SITE, pSite);

        mDatabase.insert(ManufacturerModel.Model.TABLE, null, newManufacturer);
        mDatabase.close();
    }
}