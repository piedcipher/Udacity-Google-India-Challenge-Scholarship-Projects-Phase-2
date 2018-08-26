package com.wordpress.piedcipher.aapnuamdavad.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpress.piedcipher.aapnuamdavad.R;
import com.wordpress.piedcipher.aapnuamdavad.models.Place;

public class PlaceActivity extends AppCompatActivity {

    private String mPlaceName;
    private String mPlaceWikiTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        initializer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_places, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open_in_google_maps:
                Intent openInGoogleMaps = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("geo:0,0?q=" + mPlaceName));
                if (openInGoogleMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(openInGoogleMaps);
                }
                break;
            case R.id.view_on_wikipedia:
                String wikipediaBaseURL = "https://en.wikipedia.org/wiki/";
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(wikipediaBaseURL + mPlaceWikiTitle)));
                break;
        }
        return true;
    }

    /**
     * This method initializes all the Views, Widgets, Variables and Listeners.
     */
    private void initializer() {
        ImageView placesPlacePhotoImageView = findViewById(R.id.places_place_photo_image_view);
        TextView placesPlaceDescriptionTextView = findViewById(R.id.places_place_description_text_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.WHITE);

        Bundle bundle = getIntent().getExtras();
        Place place = null;

        if (bundle != null) {
            place = bundle.getParcelable("Place");
        }

        if (place != null) {
            setTitle(place.getPlaceName());
            mPlaceName = place.getPlaceName();
            mPlaceWikiTitle = place.getPlaceWikiTitle();
            placesPlacePhotoImageView.setImageResource(place.getPlacePhoto());
            placesPlaceDescriptionTextView.setText(place.getPlaceDescription());
        }
    }
}