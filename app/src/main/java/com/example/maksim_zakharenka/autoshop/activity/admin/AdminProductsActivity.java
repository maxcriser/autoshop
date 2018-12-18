package com.example.maksim_zakharenka.autoshop.activity.admin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidhiddencamera.CameraConfig;
import com.androidhiddencamera.CameraError;
import com.androidhiddencamera.HiddenCameraActivity;
import com.androidhiddencamera.config.CameraFacing;
import com.androidhiddencamera.config.CameraImageFormat;
import com.androidhiddencamera.config.CameraResolution;
import com.androidhiddencamera.config.CameraRotation;
import com.example.maksim_zakharenka.autoshop.DialogUtils;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.StringUtils;
import com.example.maksim_zakharenka.autoshop.Toolbar;
import com.example.maksim_zakharenka.autoshop.adapter.AdminProductsAdapter;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.executable.CategoriesExecutable;
import com.example.maksim_zakharenka.autoshop.executable.ProductsExecutable;
import com.example.maksim_zakharenka.autoshop.executable.check.CheckProductExistExecutable;
import com.example.maksim_zakharenka.autoshop.model.CategoryModel;
import com.example.maksim_zakharenka.autoshop.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdminProductsActivity extends HiddenCameraActivity {

    private String mPhoto = "";

    public void onAddProduct(final View view) {
        final String name = ((TextView) findViewById(R.id.name_edittext)).getText().toString();
        final String category = ((Spinner) findViewById(R.id.category_spinner)).getSelectedItem().toString();
        final String description = ((TextView) findViewById(R.id.description_edittext)).getText().toString();
        final String number = ((TextView) findViewById(R.id.number_edittext)).getText().toString();
        final String stringCount = ((TextView) findViewById(R.id.count_edittext)).getText().toString();
        final String stringPrice = ((TextView) findViewById(R.id.price_edittext)).getText().toString();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(category) || StringUtils.isEmpty(description) || StringUtils.isEmpty(number) || StringUtils.isEmpty(stringCount) || StringUtils.isEmpty(stringPrice)) {
            DialogUtils.showErrorDialog(this, "Ошибка добавления товара", "Заполните все поля корректно и попытайтесь снова");

            return;
        }

        final int count = Integer.parseInt(stringCount);
        final int price = Integer.parseInt(stringPrice);

        final ProductModel productModel = new CheckProductExistExecutable(number).execute();

        if (productModel != null) {
            DialogUtils.showErrorDialog(this, "Ошибка добавления товара", "Данный товар уже существует");

            return;
        }

        addItem(name, category, description, number, count, price, mPhoto);
        updateRecyclerView();
    }

    @Override
    public void onImageCapture(@NonNull final File imageFile) {
        Toast.makeText(this, "Фото сделано", Toast.LENGTH_LONG).show();

        mPhoto = imageFile.getAbsolutePath();

        Picasso.with(this)
                .load(imageFile)
                .into(((ImageView) findViewById(R.id.photo)));
    }

    @Override
    public void onCameraError(final int errorCode) {
        switch (errorCode) {
            case CameraError.ERROR_CAMERA_OPEN_FAILED:
                //Camera open failed. Probably because another application
                //is using the camera
                Toast.makeText(this, "Не удается запустить камеру", Toast.LENGTH_LONG).show();

                break;
            case CameraError.ERROR_IMAGE_WRITE_FAILED:
                //Image write failed. Please check if you have provided WRITE_EXTERNAL_STORAGE permission
                Toast.makeText(this, "Не удается сохранить фото", Toast.LENGTH_LONG).show();

                break;
            case CameraError.ERROR_CAMERA_PERMISSION_NOT_AVAILABLE:
                //camera permission is not available
                //Ask for the camera permission before initializing it.
                Toast.makeText(this, "Нет доступа на камеру", Toast.LENGTH_LONG).show();

                break;
            case CameraError.ERROR_DOES_NOT_HAVE_OVERDRAW_PERMISSION:
                //Display information dialog to the user with steps to grant "Draw over other app"
                //permission for the app.
                //                HiddenCameraUtils.openDrawOverPermissionSetting(this);

                Toast.makeText(this, "Нет возможности делать фото поверх приложений", Toast.LENGTH_LONG).show();

                break;
            case CameraError.ERROR_DOES_NOT_HAVE_FRONT_CAMERA:
                Toast.makeText(this, "Нет передней камеры", Toast.LENGTH_LONG).show();

                break;
        }

    }

    @SuppressLint("MissingPermission")
    public void onTakePhoto(final View view) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                takePicture();
            }
        }, 1000);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_products);

        startCamera(new CameraConfig()
                .getBuilder(this)
                .setCameraFacing(CameraFacing.FRONT_FACING_CAMERA)
                .setCameraResolution(CameraResolution.MEDIUM_RESOLUTION)
                .setImageFormat(CameraImageFormat.FORMAT_JPEG)
                .setImageRotation(CameraRotation.ROTATION_270)
                .build());

        ((Toolbar) findViewById(R.id.toolbar)).showBackView();

        initSpinner();

        updateRecyclerView();
    }

    private void initSpinner() {
        final Spinner category = findViewById(R.id.category_spinner);
        final List<CategoryModel> categoryModelList = new CategoriesExecutable().execute();

        final List<String> stringCategories = new ArrayList<>();

        for (final CategoryModel categoryModel : categoryModelList) {
            stringCategories.add(categoryModel.getCategory());
        }

        final SpinnerAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stringCategories);
        category.setAdapter(adapter);
    }

    private void updateRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.products_recycler_view);

        final AdminProductsAdapter mAdapter = new AdminProductsAdapter(new ProductsExecutable().execute());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void addItem(final String name, final String category, final String description, final String number, final int count, final int price, final String photo) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues newProduct = new ContentValues();

        newProduct.put(ProductModel.Model.ID, (Integer) null);
        newProduct.put(ProductModel.Model.NAME, name);
        newProduct.put(ProductModel.Model.PHOTO, photo);
        newProduct.put(ProductModel.Model.PRICE, price);
        newProduct.put(ProductModel.Model.CATEGORY, category);
        newProduct.put(ProductModel.Model.DESCRIPTION, description);
        newProduct.put(ProductModel.Model.COUNT, count);
        newProduct.put(ProductModel.Model.NUMBER, number);

        mDatabase.insert(ProductModel.Model.TABLE, null, newProduct);
        mDatabase.close();
    }
}