package com.ancafra.controledeprodutos.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ancafra.controledeprodutos.R;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editEmail;
    private EditText editPassword;
    private TextView textTitle;
    private ImageButton ibBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initComponents();

    }

    private void validateData(View view) {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (!name.isEmpty()) {
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

        } else {
            editName.requestFocus();
            editName.setError("Required focus");
        }
    }

    private void initComponents() {
        textTitle = findViewById(R.id.textTitle);
        textTitle.setText("User Registration");
        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(v -> finish());
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
    }
}