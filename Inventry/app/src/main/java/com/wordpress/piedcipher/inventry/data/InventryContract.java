package com.wordpress.piedcipher.inventry.data;

import android.provider.BaseColumns;

public final class InventryContract {

    private InventryContract() {

    }

    public static final class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }
}