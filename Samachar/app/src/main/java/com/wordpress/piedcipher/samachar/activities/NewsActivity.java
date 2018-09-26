package com.wordpress.piedcipher.samachar.activities;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.wordpress.piedcipher.samachar.R;
import com.wordpress.piedcipher.samachar.adapters.NewsAdapter;
import com.wordpress.piedcipher.samachar.loaders.NewsLoader;
import com.wordpress.piedcipher.samachar.models.News;
import com.wordpress.piedcipher.samachar.utils.Utils;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private TextView customEmptyView;
    private ListView newsListView;
    private ProgressBar progressBar;

    private int listItemLayoutResource = R.layout.layout_news_list_item_card;
    private MenuItem currentLayoutMenuItem;
    private MenuItem refreshMenuItem;
    private MenuItem settingsMenuItem;

    private NewsAdapter newsAdapter;
    private List<News> newsList;

    private BottomSheetDialog bottomSheetDialog;
    private TextView cardLayoutTextViewBottomSheetDialog;
    private TextView defaultLayoutTextViewBottomSheetDialog;
    private int[] drawableRightTextViewBottomSheetDialog = {0, R.drawable.check};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initializer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        currentLayoutMenuItem = menu.findItem(R.id.current_layout);
        refreshMenuItem = menu.findItem(R.id.refresh);
        settingsMenuItem = menu.findItem(R.id.settings);

        if (!Utils.checkNetworkStatus(this)) {
            refreshMenuItem.setVisible(true);
        }

        if (listItemLayoutResource == R.layout.layout_news_list_item_card) {
            currentLayoutMenuItem.setIcon(R.drawable.view_agenda);
            currentLayoutMenuItem.setTitle(R.string.news_view_layout_2);
        } else {
            currentLayoutMenuItem.setIcon(R.drawable.view_sequential);
            currentLayoutMenuItem.setTitle(R.string.news_view_layout_1);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.current_layout:
                bottomSheetDialog.show();
                return true;

            case R.id.refresh:
                progressBar.setVisibility(View.VISIBLE);
                initializer();
                return true;

            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ListItemLayoutResource", listItemLayoutResource);
        if (listItemLayoutResource == R.layout.layout_news_list_item_card) {
            outState.putInt("MenuItemIcon", R.drawable.view_agenda);
            outState.putString("MenuItemText", getString(R.string.news_view_layout_1));
        } else {
            outState.putInt("MenuItemIcon", R.drawable.view_sequential);
            outState.putString("MenuItemText", getString(R.string.news_view_layout_2));
        }

        outState.putInt("drawableRightTextViewBottomSheetDialog0", drawableRightTextViewBottomSheetDialog[0]);
        outState.putInt("drawableRightTextViewBottomSheetDialog1", drawableRightTextViewBottomSheetDialog[1]);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listItemLayoutResource = savedInstanceState.getInt("ListItemLayoutResource");
        if (currentLayoutMenuItem != null) {
            currentLayoutMenuItem.setIcon(savedInstanceState.getInt("MenuItemIcon"));
            currentLayoutMenuItem.setTitle(savedInstanceState.getString("MenuItemText"));
        }

        drawableRightTextViewBottomSheetDialog[0] = savedInstanceState.getInt("drawableRightTextViewBottomSheetDialog0");
        drawableRightTextViewBottomSheetDialog[1] = savedInstanceState.getInt("drawableRightTextViewBottomSheetDialog1");

        if (drawableRightTextViewBottomSheetDialog[0] == R.drawable.check) {
            defaultLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            cardLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else {
            defaultLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            cardLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
        }
    }

    /**
     * Initializes views, widgets, event-listeners.
     */
    private void initializer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_dialog, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);
        LinearLayout defaultLayoutBottomSheetDialogItem = bottomSheetDialogView.findViewById(R.id.render_default_layout);
        LinearLayout cardLayoutBottomSheetDialogItem = bottomSheetDialogView.findViewById(R.id.render_card_layout);
        cardLayoutTextViewBottomSheetDialog = bottomSheetDialogView.findViewById(R.id.card_layout_text);
        defaultLayoutTextViewBottomSheetDialog = bottomSheetDialogView.findViewById(R.id.default_layout_text);

        customEmptyView = findViewById(R.id.custom_empty_view);
        newsListView = findViewById(R.id.news_list_view);
        progressBar = findViewById(R.id.progress_bar);
        newsListView.setEmptyView(customEmptyView);

        if (Utils.checkNetworkStatus(this)) {
            if (refreshMenuItem != null) {
                customEmptyView.setText("");
                refreshMenuItem.setVisible(false);
            }
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            progressBar.setVisibility(View.GONE);
            customEmptyView.setText(R.string.error_message_1);
        }

        defaultLayoutBottomSheetDialogItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listItemLayoutResource != R.layout.layout_news_list_item_default) {
                    drawableRightTextViewBottomSheetDialog[0] = R.drawable.check;
                    drawableRightTextViewBottomSheetDialog[1] = 0;
                    cardLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    defaultLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
                    listItemLayoutResource = R.layout.layout_news_list_item_default;
                    currentLayoutMenuItem.setIcon(R.drawable.view_sequential);
                    currentLayoutMenuItem.setTitle(R.string.menu_item_1);
                    newsAdapter = new NewsAdapter(getApplicationContext(), newsList, listItemLayoutResource);
                    newsListView.setAdapter(newsAdapter);
                }
                bottomSheetDialog.dismiss();
            }
        });

        cardLayoutBottomSheetDialogItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listItemLayoutResource != R.layout.layout_news_list_item_card) {
                    drawableRightTextViewBottomSheetDialog[0] = 0;
                    drawableRightTextViewBottomSheetDialog[1] = R.drawable.check;
                    cardLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
                    defaultLayoutTextViewBottomSheetDialog.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    listItemLayoutResource = R.layout.layout_news_list_item_card;
                    currentLayoutMenuItem.setIcon(R.drawable.view_agenda);
                    currentLayoutMenuItem.setTitle(R.string.menu_item_2);
                    newsAdapter = new NewsAdapter(getApplicationContext(), newsList, listItemLayoutResource);
                    newsListView.setAdapter(newsAdapter);
                }
                bottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String searchTerms = sharedPreferences.getString(getString(R.string.settings_search_terms_key), getString(R.string.settings_search_terms_value));
        String orderBy = sharedPreferences.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_value));

        String url = Uri.parse(Utils.API_BASE_URL)
                .buildUpon()
                .appendQueryParameter("q", searchTerms)
                .appendQueryParameter("order-by", orderBy)
                .toString();

        return new NewsLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, final List<News> newsList) {
        progressBar.setVisibility(View.GONE);
        this.newsList = newsList;

        if (newsList != null) {
            if (newsList.isEmpty()) {
                customEmptyView.setText(R.string.error_message_2);
                if (currentLayoutMenuItem != null) {
                    currentLayoutMenuItem.setVisible(false);
                }
                if (settingsMenuItem != null) {
                    settingsMenuItem.setVisible(true);
                }
                return;
            }

            if (currentLayoutMenuItem != null) {
                currentLayoutMenuItem.setVisible(true);
                if (settingsMenuItem != null) {
                    settingsMenuItem.setVisible(true);
                }
            }

            newsAdapter = new NewsAdapter(getApplicationContext(), newsList, listItemLayoutResource);
            newsListView.setAdapter(newsAdapter);
            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(i).getUrl())));
                }
            });
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        if (newsAdapter != null) {
            newsAdapter.clear();
        }
    }
}