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
import android.widget.RatingBar;
import android.widget.TextView;

import com.wordpress.piedcipher.aapnuamdavad.R;
import com.wordpress.piedcipher.aapnuamdavad.models.Restaurant;

public class RestaurantActivity extends AppCompatActivity {

    private String mRestaurantName;
    private String mRestaurantURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        initializer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_events_restaurants_hotels, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.open_in_google_maps:
                Intent openInGoogleMaps = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("geo:0,0?q=" + mRestaurantName + ", Ahmedabad"));
                if (openInGoogleMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(openInGoogleMaps);
                }
                break;
            case R.id.view_more_details:
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(mRestaurantURL)));
                break;
        }
        return true;
    }

    /**
     * This method initializes all the Views, Widgets, Variables and Listeners.
     */
    private void initializer() {
        ImageView restaurantsRestaurantPhotoImageView = findViewById(R.id.restaurants_restaurant_photo_image_view);
        TextView restaurantsRestaurantDescriptionTextView = findViewById(R.id.restaurants_restaurant_description_text_view);
        TextView restaurantsRestaurantContactNumberTextView = findViewById(R.id.restaurants_restaurant_contact_number_text_view);
        TextView restaurantsRestaurantTimingsTextView = findViewById(R.id.restaurants_restaurant_timings_text_view);
        RatingBar restaurantsRestaurantReviewRatingBar = findViewById(R.id.restaurants_restaurant_review_rating_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        Bundle bundle = getIntent().getExtras();
        Restaurant restaurant = null;

        if (bundle != null) {
            restaurant = bundle.getParcelable("Restaurant");
        }

        if (restaurant != null) {
            mRestaurantName = restaurant.getRestaurantName();
            setTitle(mRestaurantName);
            mRestaurantURL = restaurant.getRestaurantURL();
            restaurantsRestaurantPhotoImageView.setImageResource(restaurant.getRestaurantPhoto());
            restaurantsRestaurantReviewRatingBar.setRating(Float.parseFloat(restaurant.getRestaurantReview()));
            restaurantsRestaurantDescriptionTextView.setText(restaurant.getRestaurantDescription());
            restaurantsRestaurantContactNumberTextView.setText(restaurant.getRestaurantContactNumber());
            restaurantsRestaurantTimingsTextView.setText(restaurant.getRestaurantTimings());
        }
    }
}
