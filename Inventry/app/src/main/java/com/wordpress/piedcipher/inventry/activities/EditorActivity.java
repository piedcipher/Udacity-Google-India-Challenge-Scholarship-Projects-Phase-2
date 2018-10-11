package com.wordpress.piedcipher.inventry.activities;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wordpress.piedcipher.inventry.R;
import com.wordpress.piedcipher.inventry.data.InventryContract.ProductEntry;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Uri currentUri;

    private String currentProductName;
    private int currentProductQuantity;
    private int currentProductPrice;
    private String currentSupplierName;
    private String currentSupplierPhone;

    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productQuantityEditText;
    private EditText productSupplierNameEditText;
    private EditText productSupplierPhoneEditText;

    private boolean isProductChanged = false;
    private boolean shouldProceed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        initializer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem addUpdateMenuItem = menu.findItem(R.id.add_update_product);

        if (currentUri == null) {
            MenuItem deleteMenuItem = menu.findItem(R.id.delete_product);
            deleteMenuItem.setVisible(false);
            addUpdateMenuItem.setTitle(R.string.menu_item_add_product);
        } else {
            addUpdateMenuItem.setTitle(R.string.menu_item_update_product);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_update_product:
                addProduct();
                if (shouldProceed) finish();
                break;

            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.delete_product:
                showDeleteConfirmationDialog();
                break;
        }
        return true;
    }

    private void initializer() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button increaseQuantityButton = findViewById(R.id.increase_quantity);
        Button decreaseQuantityButton = findViewById(R.id.decrease_quantity);
        Button orderButton = findViewById(R.id.contact_supplier);
        currentUri = getIntent().getData();

        if (currentUri == null) {
            setTitle(R.string.add_product_title);
            invalidateOptionsMenu();
            orderButton.setVisibility(View.GONE);
        } else {
            setTitle(R.string.edit_product_title);
            getLoaderManager().initLoader(1, null, this);
        }

        productNameEditText = findViewById(R.id.product_name_edit_text);
        productPriceEditText = findViewById(R.id.product_price_edit_text);
        productQuantityEditText = findViewById(R.id.product_quantity_edit_text);
        productSupplierNameEditText = findViewById(R.id.product_supplier_name_edit_text);
        productSupplierPhoneEditText = findViewById(R.id.product_supplier_phone_edit_text);
        productQuantityEditText.setText("0");

        increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(productQuantityEditText.getText())) {
                    productQuantityEditText.setText(String.valueOf(Integer.parseInt(productQuantityEditText.getText().toString()) + 1));
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.product_quantity_toast), Toast.LENGTH_SHORT).show();
                }
            }
        });

        decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!productQuantityEditText.getText().toString().equals("0") && !TextUtils.isEmpty(productQuantityEditText.getText())) {
                    productQuantityEditText.setText(String.valueOf(Integer.parseInt(productQuantityEditText.getText().toString()) - 1));
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.product_quantity_toast), Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currentSupplierPhone)));
            }
        });

        productNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (currentUri != null) {
                    isProductChanged = (!currentProductName.equals(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        productPriceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (currentUri != null) {
                    isProductChanged = (!String.valueOf(currentProductPrice).equals(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        productQuantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (currentUri != null) {
                    isProductChanged = (!String.valueOf(currentProductQuantity).equals(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        productSupplierNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (currentUri != null) {
                    isProductChanged = (!currentSupplierName.equals(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        productSupplierPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (currentUri != null) {
                    isProductChanged = (!currentSupplierPhone.equals(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ProductEntry.ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRICE,
                ProductEntry.COLUMN_QUANTITY,
                ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
        };

        return new CursorLoader(this, currentUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            currentProductName = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_NAME));
            currentProductPrice = cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRICE));
            currentProductQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_QUANTITY));
            currentSupplierName = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_SUPPLIER_NAME));
            currentSupplierPhone = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER));

            productNameEditText.setText(currentProductName);
            productPriceEditText.setText(String.valueOf(currentProductPrice));
            productQuantityEditText.setText(String.valueOf(currentProductQuantity));
            productSupplierNameEditText.setText(currentSupplierName);
            productSupplierPhoneEditText.setText(currentSupplierPhone);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        productNameEditText.setText("");
        productPriceEditText.setText("");
        productQuantityEditText.setText("");
        productSupplierNameEditText.setText("");
        productSupplierPhoneEditText.setText("");
    }

    private void addProduct() {
        String productName = productNameEditText.getText().toString().trim();
        String productPrice = productPriceEditText.getText().toString().trim();
        String productQuantity = productQuantityEditText.getText().toString().trim();
        String supplierName = productSupplierNameEditText.getText().toString().trim();
        String supplierPhone = productSupplierPhoneEditText.getText().toString().trim();

        if (currentUri == null || TextUtils.isEmpty(productName) || TextUtils.isEmpty(productPrice) ||
                TextUtils.isEmpty(productQuantity) || TextUtils.isEmpty(supplierName) || TextUtils.isEmpty(supplierPhone) || supplierPhone.length() != 10) {
            if (TextUtils.isEmpty(productName)) {
                Toast.makeText(this, R.string.product_name_toast, Toast.LENGTH_SHORT).show();
                shouldProceed = false;
                return;
            } else {
                shouldProceed = true;
            }

            if (TextUtils.isEmpty(String.valueOf(productPrice))) {
                Toast.makeText(this, R.string.product_price_toast, Toast.LENGTH_SHORT).show();
                shouldProceed = false;
                return;
            } else {
                shouldProceed = true;
            }

            if (TextUtils.isEmpty(String.valueOf(productQuantity))) {
                Toast.makeText(this, R.string.product_quantity_toast, Toast.LENGTH_SHORT).show();
                shouldProceed = false;
                return;
            } else {
                shouldProceed = true;
            }


            if (TextUtils.isEmpty(supplierName)) {
                Toast.makeText(this, R.string.product_supplier_name_toast, Toast.LENGTH_SHORT).show();
                shouldProceed = false;
                return;
            } else {
                shouldProceed = true;
            }

            if (TextUtils.isEmpty(supplierPhone) || supplierPhone.length() != 10) {
                if (supplierPhone.length() != 10) {
                    Toast.makeText(this, R.string.product_supplier_phone_toast_2, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.product_supplier_phone_toast, Toast.LENGTH_SHORT).show();
                }
                shouldProceed = false;
                return;
            } else {
                shouldProceed = true;
            }
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductEntry.COLUMN_PRODUCT_NAME, productName);
        contentValues.put(ProductEntry.COLUMN_PRICE, Integer.parseInt(productPrice));
        contentValues.put(ProductEntry.COLUMN_QUANTITY, Integer.parseInt(productQuantity));
        contentValues.put(ProductEntry.COLUMN_SUPPLIER_NAME, supplierName);
        contentValues.put(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierPhone);

        if (currentUri == null) {
            Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, contentValues);
            if (newUri == null) {
                Toast.makeText(this, getString(R.string.toast_error_message), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.toast_success_message), Toast.LENGTH_SHORT).show();
            }
        } else {
            if (isProductChanged) {
                int rowsAffected = getContentResolver().update(currentUri, contentValues, null, null);
                if (rowsAffected == 0) {
                    Toast.makeText(this, getString(R.string.toast_error_message_edit), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.toast_success_message_edit), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, getString(R.string.toast_no_new_changes), Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    private void showUnsavedChangesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_unsave_changes);
        builder.setNegativeButton(R.string.dialog_response_keep_editing, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        });

        builder.setPositiveButton(R.string.dialog_response_discard, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                isProductChanged = false;
                finish();
            }
        });

        builder.create().show();
    }

    private void deleteProduct() {
        if (currentUri != null) {
            int rowsDeleted = getContentResolver().delete(currentUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, R.string.toast_error_message_delete, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.toast_success_message_delete, Toast.LENGTH_SHORT).show();
            }
        }

        finish();
    }

    private void showDeleteConfirmationDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_delete_product);
        builder.setPositiveButton(R.string.dialog_delete_product_response_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                deleteProduct();
            }
        });

        builder.setNegativeButton(R.string.dialog_delete_product_response_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (dialog != null)
                    dialog.dismiss();
            }
        });

        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        if (isProductChanged) {
            showUnsavedChangesDialog();
        } else {
            super.onBackPressed();
        }
    }
}