package com.ancafra.controledeprodutos.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    public void validateData(View view) {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (!name.isEmpty()) {
            if (!email.isEmpty()) {
                if (!password.isEmpty()) {

                    hideKeyboard();
                    Toast.makeText(this, "It's ok for here", Toast.LENGTH_SHORT).show();
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

    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                editName.getWindowToken(), 0
        );
    }
}