package com.example.maksim_zakharenka.autoshop.activity.auth;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.maksim_zakharenka.autoshop.ActivityUtils;
import com.example.maksim_zakharenka.autoshop.DialogUtils;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.StringUtils;
import com.example.maksim_zakharenka.autoshop.database.DatabaseHolder;
import com.example.maksim_zakharenka.autoshop.executable.AccountsExecutable;
import com.example.maksim_zakharenka.autoshop.model.AccountModel;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mLogin;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private EditText mCountry;
    private EditText mFullName;
    private View mRegButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initViews();
    }

    private void initViews() {
        mLogin = findViewById(R.id.login_edittext);
        mPassword = findViewById(R.id.password_edittext);
        mPasswordConfirm = findViewById(R.id.password_confirm_edittext);
        mCountry = findViewById(R.id.country_edittext);
        mFullName = findViewById(R.id.full_name_edittext);
        mRegButton = findViewById(R.id.reg_button);

        mRegButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                processRegistration();
            }
        });
    }

    private void processRegistration() {
        final String login = mLogin.getText().toString();
        final String password = mPassword.getText().toString();
        final String passwordConfirm = mPasswordConfirm.getText().toString();
        final String country = mCountry.getText().toString();
        final String fullName = mFullName.getText().toString();

        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordConfirm) || StringUtils.isEmpty(fullName) || StringUtils.isEmpty(country)) {
            DialogUtils.showErrorDialog(this, "Ошибка регистрации", "Пожалуйста, заполните все необходимые поля для продолжения регистрации.");

            return;
        }

        if (!password.equals(passwordConfirm)) {
            DialogUtils.showErrorDialog(this, "Ошибка регистрации", "Пароли не совпадают.");

            return;
        }

        final List<AccountModel> accountModels = new AccountsExecutable().execute();

        for (final AccountModel accountModel : accountModels) {
            if (accountModel.getLogin().equals(login)) {
                DialogUtils.showErrorDialog(this, "Ошибка регистрации", "Пользователь с таким логином уже существует.");

                return;
            }
        }

        addAccount(login, password, fullName, country);
        ActivityUtils.openApp(this, false, login, password);
    }

    private void addAccount(final String pLogin, final String pPassword, final String pFullName, final String pCountry) {
        final SQLiteDatabase mDatabase = DatabaseHolder.get().getWritableDatabase();
        final ContentValues newAccount = new ContentValues();

        newAccount.put(AccountModel.Model.ID, (Integer) null);
        newAccount.put(AccountModel.Model.LOGIN, pLogin);
        newAccount.put(AccountModel.Model.PASSWORD, pPassword);
        newAccount.put(AccountModel.Model.FULL_NAME, pFullName);
        newAccount.put(AccountModel.Model.COUNTRY, pCountry);

        mDatabase.insert(AccountModel.Model.TABLE, null, newAccount);
        mDatabase.close();
    }
}
