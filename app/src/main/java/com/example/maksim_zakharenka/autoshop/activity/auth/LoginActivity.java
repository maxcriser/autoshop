package com.example.maksim_zakharenka.autoshop.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.maksim_zakharenka.autoshop.ActivityUtils;
import com.example.maksim_zakharenka.autoshop.DialogUtils;
import com.example.maksim_zakharenka.autoshop.R;
import com.example.maksim_zakharenka.autoshop.StringUtils;
import com.example.maksim_zakharenka.autoshop.callback.IConfirmation;
import com.example.maksim_zakharenka.autoshop.executable.check.CheckAccountCredentialsExecutable;
import com.example.maksim_zakharenka.autoshop.model.AccountModel;

public class LoginActivity extends AppCompatActivity {

    private EditText mPassword;
    private EditText mLogin;
    private View mLoginButton;
    private View mRegistrationButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        mPassword = findViewById(R.id.password_edittext);
        mLogin = findViewById(R.id.login_edittext);
        mLoginButton = findViewById(R.id.login_button);
        mRegistrationButton = findViewById(R.id.registration_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                processLogin();
            }
        });

        mRegistrationButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void processLogin() {
        final String password = mPassword.getText().toString();
        final String login = mLogin.getText().toString();

        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(login)) {
            DialogUtils.showErrorDialog(this, "Ошибка авторизации", "Проверьте правильность ввода логина и пароля, а затем повторите попытку.");

            return;
        }

        final String adminLogin = getString(R.string.ADMIN_LOGIN);
        final String adminPassword = getString(R.string.ADMIN_PASSWORD);

        if (password.equals(adminPassword) && adminLogin.equals(login)) {
            DialogUtils.showConfirmDialog(this, "Вы будите авторизированы как администратор с возможностью добавления и удаления товара.", new IConfirmation() {

                @Override
                public void onConfirm() {
                    ActivityUtils.openApp(LoginActivity.this, true);
                }

                @Override
                public void onDismiss() {
                    // empty
                }
            });

            return;
        }

        final AccountModel account = new CheckAccountCredentialsExecutable(login, password).execute();

        if (account != null) {
            ActivityUtils.openApp(this, false);
        } else {
            DialogUtils.showErrorDialog(this, "Ошибка авторизации", "Неверный логин или пароль.");
        }
    }
}