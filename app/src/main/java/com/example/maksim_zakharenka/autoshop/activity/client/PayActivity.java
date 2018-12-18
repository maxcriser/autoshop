package com.example.maksim_zakharenka.autoshop.activity.client;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.maksim_zakharenka.autoshop.AppSettings;
import com.example.maksim_zakharenka.autoshop.DialogUtils;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.StringUtils;
import com.example.maksim_zakharenka.autoshop.Toolbar;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.executable.RemoveTrashBySavedUsernameExecutable;
import com.example.maksim_zakharenka.autoshop.model.OrderModel;

public class PayActivity extends AppCompatActivity {

    public void onPayClick(final View view) {
        final String addres = ((TextView) findViewById(R.id.address)).getText().toString();
        final String index = ((TextView) findViewById(R.id.index)).getText().toString();
        final String phone = ((TextView) findViewById(R.id.phone)).getText().toString();

        if (StringUtils.isEmpty(addres) || StringUtils.isEmpty(index) || StringUtils.isEmpty(phone)) {
            DialogUtils.showErrorDialog(this, "Ошибка добавления товара", "Заполните все поля корректно и попытайтесь снова");

            return;
        }

        addItem(addres, index, phone);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ((Toolbar) findViewById(R.id.toolbar)).showBackView();
    }

    private void addItem(final String address, final String index, final String phone) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues newProduct = new ContentValues();

        newProduct.put(OrderModel.Model.ID, (Integer) null);
        newProduct.put(OrderModel.Model.CLIENT, AppSettings.getUserName());
        newProduct.put(OrderModel.Model.ADDRESS, address);
        newProduct.put(OrderModel.Model.INDEX, index);
        newProduct.put(OrderModel.Model.PHONE, phone);

        mDatabase.insert(OrderModel.Model.TABLE, null, newProduct);
        mDatabase.close();

        new RemoveTrashBySavedUsernameExecutable().execute();

        finish();
    }
}