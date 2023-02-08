package com.ancafra.controledeprodutos.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ancafra.controledeprodutos.R;
import com.ancafra.controledeprodutos.activities.MainActivity;
import com.ancafra.controledeprodutos.helper.FirebaseHelper;
import com.ancafra.controledeprodutos.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editEmail;
    private EditText editPassword;
    private TextView textTitle;
    private ImageButton ibBack;
    private ProgressBar progressBar;


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
                    progressBar.setVisibility(View.VISIBLE);
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);

                    saveRegistration(user);

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

    private void saveRegistration(User user) {
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String id = task.getResult().getUser().getUid();
                user.setId(id);
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }
        });
    }

    private void initComponents() {
        textTitle = findViewById(R.id.textTitle);
        textTitle.setText("User Registration");
        ibBack = findViewById(R.id.ibBack);
        ibBack.setOnClickListener(v -> finish());
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        progressBar = findViewById(R.id.progressBar);
    }

    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                editName.getWindowToken(), 0
        );
    }
}