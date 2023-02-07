package com.ancafra.controledeprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FormProductActivity extends AppCompatActivity {

    private ProductDAO productDAO;

    private TextView textTitle;
    private ImageButton ibAdd;
    private EditText edtProduct;
    private EditText edtQuantity;
    private EditText edtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        productDAO = new ProductDAO(this);

        initComponents();
    }

    private void initComponents() {
        textTitle = findViewById(R.id.textTitle);
        textTitle.setText("Products registration");
        ibAdd = findViewById(R.id.ibBack);
        ibAdd.setOnClickListener(v -> finish());
        edtProduct = findViewById(R.id.edtProduct);
        edtQuantity = findViewById(R.id.edtQuantity);
        edtValue = findViewById(R.id.edtValue);
    }

    public void saveProduct(View view) {
        String name = edtProduct.getText().toString();
        String quantity = edtQuantity.getText().toString();
        String value = edtValue.getText().toString();

        if (!name.isEmpty()) {
            if (!quantity.isEmpty()) {
                int qtt = Integer.parseInt(quantity);
                if (qtt >= 1) {
                    if (!value.isEmpty()) {
                        double vl = Double.parseDouble(value);
                        if(vl > 0) {

                            hideKeyboard();
                            Product product = new Product();
                            product.setName(name);
                            product.setQuantity(Integer.parseInt(quantity));
                            product.setValue(Double.parseDouble(value));

                            productDAO.saveProduct(product);

                        }else {
                            edtValue.requestFocus();
                            edtValue.setError("Invalid value");
                        }

                    } else {
                        edtValue.requestFocus();
                        edtValue.setError("Required field");
                    }

                } else {
                    edtQuantity.requestFocus();
                    edtQuantity.setError("Invalid quantity");
                }

            } else {
                edtQuantity.requestFocus();
                edtQuantity.setError("Required field");
            }

        } else {
            edtProduct.requestFocus();
            edtProduct.setError("Required field");
        }
    }

    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                edtProduct.getWindowToken(), 0
        );
    }
}