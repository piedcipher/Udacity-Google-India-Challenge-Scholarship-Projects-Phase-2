package com.wordpress.piedcipher.inventry.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wordpress.piedcipher.inventry.R;
import com.wordpress.piedcipher.inventry.data.InventryContract.ProductEntry;
import com.wordpress.piedcipher.inventry.data.InventryDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private InventryDatabaseHelper inventryDatabaseHelper;
    private static final String LOG_TAG = MainActivity.class.getSimpleName() + "Info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
    }

    private void initializer() {
        inventryDatabaseHelper = new InventryDatabaseHelper(this);
        readProducts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_product:
                addProduct();
                break;
        }
        return true;
    }

    private void addProduct() {
        SQLiteDatabase sqLiteDatabase = inventryDatabaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductEntry.COLUMN_PRODUCT_NAME, "Mi A1");
        contentValues.put(ProductEntry.COLUMN_PRICE, 15000);
        contentValues.put(ProductEntry.COLUMN_QUANTITY, 6);
        contentValues.put(ProductEntry.COLUMN_SUPPLIER_NAME, "Mi");
        contentValues.put(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "1234567890");

        long id = sqLiteDatabase.insert(ProductEntry.TABLE_NAME, null, contentValues);
        if (id != -1) {
            Toast.makeText(this, R.string.toast_success_message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toast_error_message, Toast.LENGTH_SHORT).show();
        }

        readProducts();
    }

    private void readProducts() {
        SQLiteDatabase sqLiteDatabase = inventryDatabaseHelper.getReadableDatabase();

        int counter = 0;
        Cursor cursor = sqLiteDatabase.query(ProductEntry.TABLE_NAME, null, null, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(ProductEntry.ID));
                String productName = cursor.getString(cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME));
                int price = cursor.getInt(cursor.getColumnIndex(ProductEntry.COLUMN_PRICE));
                int quantity = cursor.getInt(cursor.getColumnIndex(ProductEntry.COLUMN_QUANTITY));
                String supplierName = cursor.getString(cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_NAME));
                String supplierPhoneNumber = cursor.getString(cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER));

                Log.i(LOG_TAG, id + " " + productName + " " + quantity + " " + price + " " + supplierName + " " + supplierPhoneNumber);
                counter++;
            }
        } finally {
            cursor.close();
            Log.i(LOG_TAG, "-----");
            if (counter == 0) {
                Log.i(LOG_TAG, getString(R.string.empty_view_text));
                Toast.makeText(this, R.string.empty_view_text, Toast.LENGTH_SHORT).show();
            }
        }
    }
}