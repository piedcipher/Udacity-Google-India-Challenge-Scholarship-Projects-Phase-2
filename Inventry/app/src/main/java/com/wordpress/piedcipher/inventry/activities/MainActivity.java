package com.wordpress.piedcipher.inventry.activities;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wordpress.piedcipher.inventry.R;
import com.wordpress.piedcipher.inventry.adapter.InventryCursorAdapter;
import com.wordpress.piedcipher.inventry.data.InventryContract.ProductEntry;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private InventryCursorAdapter inventryCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
    }

    private void initializer() {
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        ListView productListView = findViewById(R.id.products_list);
        TextView emptyTextView = findViewById(R.id.empty_text_view);
        productListView.setEmptyView(emptyTextView);

        inventryCursorAdapter = new InventryCursorAdapter(this, null);
        productListView.setAdapter(inventryCursorAdapter);
        getLoaderManager().initLoader(1, null, this);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(), EditorActivity.class)
                        .setData(ContentUris.withAppendedId(ProductEntry.CONTENT_URI, l)));
            }
        });
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String[] projection = {
                ProductEntry.ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRICE,
                ProductEntry.COLUMN_QUANTITY,
                ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER
        };

        return new CursorLoader(
                this,
                ProductEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        inventryCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        inventryCursorAdapter.swapCursor(null);
    }
}