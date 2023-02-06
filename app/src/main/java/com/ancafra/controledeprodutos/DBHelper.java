package com.ancafra.controledeprodutos;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import javax.security.auth.login.LoginException;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME_DB = "DB_APP";
    private static final String TB_PRODUCT = "TB_PRODUCT";

    public DBHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TB_PRODUCT
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,  "
                + " name TEXT NOT NULL, "
                + " stock INTEGER NOT NULL, "
                + " VALUE DOUBLE NOT NULL); ";

        try {
            db.execSQL(sql);
        }catch (Exception e) {
            Log.i("ERROR", "Error creating database table!" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
