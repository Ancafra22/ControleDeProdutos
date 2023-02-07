package com.ancafra.controledeprodutos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
            //write.close();
        }catch (Exception e) {
            Log.i("ERROR", "Error saving product!" + e.getMessage());
        }
    }
}
