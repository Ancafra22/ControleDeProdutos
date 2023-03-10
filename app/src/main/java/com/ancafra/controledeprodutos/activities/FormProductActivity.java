package com.ancafra.controledeprodutos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ancafra.controledeprodutos.model.Product;
import com.ancafra.controledeprodutos.ProductDAO;
import com.ancafra.controledeprodutos.R;

public class FormProductActivity extends AppCompatActivity {

    private ProductDAO productDAO;
    private Product product;

    private TextView textTitle;
    private ImageButton ibAdd;
    private EditText edtProduct;
    private EditText edtQuantity;
    private EditText edtValue;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_produto);

        productDAO = new ProductDAO(this);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            product = (Product) bundle.getSerializable("product");
            editProduct();
        }
    }

    private void editProduct() {
        edtProduct.setText(product.getName());
        edtQuantity.setText(String.valueOf(product.getQuantity()));
        edtValue.setText(String.valueOf(product.getValue()));
    }

    private void initComponents() {
        textTitle = findViewById(R.id.textTitle);
        textTitle.setText("Products registration");
        ibAdd = findViewById(R.id.ibBack);
        ibAdd.setOnClickListener(v -> finish());
        edtProduct = findViewById(R.id.edtProduct);
        edtQuantity = findViewById(R.id.edtQuantity);
        edtValue = findViewById(R.id.edtValue);
        progressBar = findViewById(R.id.progressBar);
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
                        if (vl > 0) {

                            hideKeyboard();
                            if (product == null) product = new Product();
                            product.setName(name);
                            product.setQuantity(Integer.parseInt(quantity));
                            product.setValue(Double.parseDouble(value));

                            if (product.getId() != 0) {
                                productDAO.updateProduct(product);
                            } else {
                                productDAO.saveProduct(product);
                            }

                            finish();

                        } else {
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