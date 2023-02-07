package com.ancafra.controledeprodutos.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ancafra.controledeprodutos.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editPassword;
    private TextView textRetrieve;
    private TextView textCreateAccount;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        configClick();
    }

    private void configClick() {
        textCreateAccount.setOnClickListener(v -> startActivity(new Intent(this, CreateAccountActivity.class)));
    }

    private void validateData() {

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (!email.isEmpty()) {
            if (!password.isEmpty()) {

            } else {
                editPassword.requestFocus();
                editPassword.setError("required focus");
            }

        } else {
            editEmail.requestFocus();
            editEmail.setError("Required focus");
        }
    }

    private void initComponents() {
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        textCreateAccount = findViewById(R.id.textCreateAccount);
        textRetrieve = findViewById(R.id.textRetrieve);
        progressBar = findViewById(R.id.progressBar);
    }
}