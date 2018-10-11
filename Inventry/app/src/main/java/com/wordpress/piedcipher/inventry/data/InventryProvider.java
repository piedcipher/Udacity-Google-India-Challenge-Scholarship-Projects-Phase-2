package com.wordpress.piedcipher.inventry.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.wordpress.piedcipher.inventry.R;
import com.wordpress.piedcipher.inventry.data.InventryContract.ProductEntry;

public class InventryProvider extends ContentProvider {

    private static final String LOG_TAG = InventryProvider.class.getSimpleName();

    private static final int PRODUCTS = 100;
    private static final int PRODUCT_ID = 101;

    private InventryDatabaseHelper inventryDatabaseHelper;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(InventryContract.CONTENT_AUTHORITY, InventryContract.PATH_PRODUCTS, PRODUCTS);
        uriMatcher.addURI(InventryContract.CONTENT_AUTHORITY, InventryContract.PATH_PRODUCTS + "/#", PRODUCT_ID);
    }

    @Override
    public boolean onCreate() {
        inventryDatabaseHelper = new InventryDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase = inventryDatabaseHelper.getReadableDatabase();
        Cursor cursor;
        int match = uriMatcher.match(uri);

        switch (match) {
            case PRODUCTS:
                cursor = sqLiteDatabase.query(ProductEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PRODUCT_ID:
                selection = ProductEntry.ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(ProductEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI - " + uri);
        }

        if (getContext() != null) cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return insertProduct(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for URI - " + uri);
        }
    }

    private Uri insertProduct(Uri uri, ContentValues contentValues) {
        if (getContext() != null) {
            String productName = contentValues.getAsString(ProductEntry.COLUMN_PRODUCT_NAME);
            if (productName == null || TextUtils.isEmpty(productName))
                throw new IllegalArgumentException(getContext().getString(R.string.product_name_toast));

            Integer productPrice = contentValues.getAsInteger(ProductEntry.COLUMN_PRICE);
            if (productPrice == null || productPrice < 0)
                throw new IllegalArgumentException(getContext().getString(R.string.product_price_toast));

            Integer productQuantity = contentValues.getAsInteger(ProductEntry.COLUMN_QUANTITY);
            if (productQuantity == null || productQuantity < 0)
                throw new IllegalArgumentException(getContext().getString(R.string.product_quantity_toast));

            String supplierName = contentValues.getAsString(ProductEntry.COLUMN_SUPPLIER_NAME);
            if (supplierName == null || TextUtils.isEmpty(supplierName))
                throw new IllegalArgumentException(getContext().getString(R.string.product_supplier_name_toast));

            String supplierContactNumber = contentValues.getAsString(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
            if (supplierContactNumber == null || TextUtils.isEmpty(supplierContactNumber))
                throw new IllegalArgumentException(getContext().getString(R.string.product_supplier_phone_toast));

            if (supplierContactNumber.length() != 10)
                throw new IllegalArgumentException(getContext().getString(R.string.product_supplier_phone_toast_2));
        }

        SQLiteDatabase sqLiteDatabase = inventryDatabaseHelper.getWritableDatabase();

        long rowId = sqLiteDatabase.insert(ProductEntry.TABLE_NAME, null, contentValues);
        if (rowId == -1) {
            Log.e(LOG_TAG, "Failed to Insert Row for URI - " + uri);
            return null;
        }

        if (getContext() != null)
            getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, rowId);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = inventryDatabaseHelper.getWritableDatabase();
        int rowsDeleted;
        int match = uriMatcher.match(uri);

        switch (match) {
            case PRODUCTS:
                rowsDeleted = sqLiteDatabase.delete(ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PRODUCT_ID:
                selection = ProductEntry.ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = sqLiteDatabase.delete(ProductEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Delete Operation isn\'t supported for URI - " + uri);
        }

        if (rowsDeleted != 0 && getContext() != null)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return updateProduct(uri, contentValues, selection, selectionArgs);

            case PRODUCT_ID:
                selection = ProductEntry.ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, contentValues, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Update Operation isn\'t supported for URI - " + uri);
        }
    }

    private int updateProduct(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        if (getContext() != null) {
            if (contentValues.containsKey(ProductEntry.COLUMN_PRODUCT_NAME)) {
                String productName = contentValues.getAsString(ProductEntry.COLUMN_PRODUCT_NAME);
                if (productName == null || TextUtils.isEmpty(productName))
                    throw new IllegalArgumentException(getContext().getString(R.string.product_name_toast));
            }

            if (contentValues.containsKey(ProductEntry.COLUMN_PRICE)) {
                Integer productPrice = contentValues.getAsInteger(ProductEntry.COLUMN_PRICE);
                if (productPrice == null || TextUtils.isEmpty(String.valueOf(productPrice)))
                    throw new IllegalArgumentException(getContext().getString(R.string.product_price_toast));
            }

            if (contentValues.containsKey(ProductEntry.COLUMN_QUANTITY)) {
                Integer productQuantity = contentValues.getAsInteger(ProductEntry.COLUMN_QUANTITY);
                if (productQuantity == null || TextUtils.isEmpty(String.valueOf(productQuantity)))
                    throw new IllegalArgumentException(getContext().getString(R.string.product_quantity_toast));
            }

            if (contentValues.containsKey(ProductEntry.COLUMN_SUPPLIER_NAME)) {
                String supplierName = contentValues.getAsString(ProductEntry.COLUMN_SUPPLIER_NAME);
                if (supplierName == null || TextUtils.isEmpty(supplierName))
                    throw new IllegalArgumentException(getContext().getString(R.string.product_supplier_name_toast));
            }

            if (contentValues.containsKey(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER)) {
                String supplierContactNumber = contentValues.getAsString(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
                if (supplierContactNumber == null || TextUtils.isEmpty(supplierContactNumber))
                    throw new IllegalArgumentException(getContext().getString(R.string.product_supplier_phone_toast));

                if (supplierContactNumber.length() != 10)
                    throw new IllegalArgumentException(getContext().getString(R.string.product_supplier_phone_toast_2));
            }
        }

        if (contentValues.size() == 0)
            return 0;

        SQLiteDatabase sqLiteDatabase = inventryDatabaseHelper.getWritableDatabase();
        int rowsUpdated = sqLiteDatabase.update(ProductEntry.TABLE_NAME, contentValues, selection, selectionArgs);
        if (rowsUpdated != 0 && getContext() != null)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                return ProductEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return ProductEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + uriMatcher.match(uri));
        }
    }
}