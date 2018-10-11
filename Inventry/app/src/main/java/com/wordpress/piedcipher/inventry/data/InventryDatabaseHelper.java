package com.wordpress.piedcipher.inventry.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wordpress.piedcipher.inventry.data.InventryContract.ProductEntry;

public class InventryDatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = InventryDatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "inventry.db";
    private static final int DATABASE_VERSION = 1;

    InventryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_PRODUCT_TABLE =
                "CREATE TABLE " + ProductEntry.TABLE_NAME + "("
                        + ProductEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                        + ProductEntry.COLUMN_PRICE + " INTEGER NOT NULL DEFAULT 0, "
                        + ProductEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                        + ProductEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                        + ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL)";

        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
        Log.d(LOG_TAG, "Products Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String DELETE_PRODUCT_TABLE =
                "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;
        sqLiteDatabase.execSQL(DELETE_PRODUCT_TABLE);
        onCreate(sqLiteDatabase);
    }
}