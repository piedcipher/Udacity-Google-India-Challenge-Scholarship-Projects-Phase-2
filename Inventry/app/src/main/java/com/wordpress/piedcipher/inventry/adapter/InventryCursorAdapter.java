package com.wordpress.piedcipher.inventry.adapter;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.piedcipher.inventry.R;
import com.wordpress.piedcipher.inventry.data.InventryContract.ProductEntry;

public class InventryCursorAdapter extends CursorAdapter {

    public InventryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.product_list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        final TextView productNameTextView = view.findViewById(R.id.product_name);
        TextView productPriceTextView = view.findViewById(R.id.product_price);
        TextView productQuantityTextView = view.findViewById(R.id.product_quantity);
        TextView productSupplierNameTextView = view.findViewById(R.id.product_supplier_name);

        Button sellButton = view.findViewById(R.id.sell_button);
        Button orderButton = view.findViewById(R.id.order_button);

        String productName = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_NAME));
        String productPrice = "$ " + String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRICE)));
        final String productQuantity = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_QUANTITY)));
        String productSupplierName = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_SUPPLIER_NAME));
        final String productSupplierPhone = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER));
        final int rowId = cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry.ID));

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri newUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, rowId);
                if (Integer.parseInt(productQuantity) > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ProductEntry.COLUMN_QUANTITY, Integer.parseInt(productQuantity) - 1);
                    context.getContentResolver().update(newUri, contentValues, null, null);
                } else {
                    Toast.makeText(context, R.string.last_product_sell_warning, Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + productSupplierPhone)));
            }
        });

        productNameTextView.setText(productName);
        productPriceTextView.setText(productPrice);
        String productQuantityText = productQuantity + " pcs";
        productQuantityTextView.setText(productQuantityText);
        productSupplierNameTextView.setText(productSupplierName);
    }
}