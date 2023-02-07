package com.ancafra.controledeprodutos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public ProductDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write = dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void saveProduct(Product product) {
        ContentValues cv = new ContentValues();
        cv.put("name", product.getName());
        cv.put("stock", product.getQuantity());
        cv.put("value", product.getValue());

        try {
            write.insert(DBHelper.TB_PRODUCT, null, cv);
            write.close();
        }catch (Exception e) {
            Log.i("ERROR", "Error saving product!" + e.getMessage());
        }
    }

    public List<Product> getListProduct() {
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TB_PRODUCT + ";";
        Cursor c = read.rawQuery(sql, null);

        while(c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range")String name = c.getString(c.getColumnIndex("name"));
            @SuppressLint("Range") int stock = c.getInt(c.getColumnIndex("stock"));
            @SuppressLint("Range") double value = c.getDouble(c.getColumnIndex("value"));

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setQuantity(stock);
            product.setValue(value);
            productList.add(product);
        }

        return productList;
    }

    public void updateProduct(Product product) {
        ContentValues cv = new ContentValues();
        cv.put("name", product.getName());
        cv.put("stock", product.getQuantity());
        cv.put("value", product.getValue());

        String where = "id=?";
        String[] args = {String.valueOf(product.getId())};

        try {
            write.update(DBHelper.TB_PRODUCT, cv, where, args);
            write.close();
        }catch (Exception e) {
            Log.i("ERROR", "Error update product!" + e.getMessage());
        }
    }
}
