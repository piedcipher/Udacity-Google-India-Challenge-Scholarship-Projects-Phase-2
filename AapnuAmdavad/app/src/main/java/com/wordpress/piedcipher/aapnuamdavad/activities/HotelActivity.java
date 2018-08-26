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
import com.wordpress.piedcipher.aapnuamdavad.models.Hotel;

public class HotelActivity extends AppCompatActivity {

    private String mHotelName;
    private String mHotelURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
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
                Intent openInGoogleMaps = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("geo:0,0?q=" + mHotelName + ", Ahmedabad"));
                if (openInGoogleMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(openInGoogleMaps);
                }
                break;
            case R.id.view_more_details:
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(mHotelURL)));
                break;
        }
        return true;
    }

    /**
     * This method initializes all the Views, Widgets, Variables and Listeners.
     */
    private void initializer() {
        ImageView hotelsHotelPhotoImageView = findViewById(R.id.hotels_hotel_photo_image_view);
        TextView hotelsHotelDescriptionTextView = findViewById(R.id.hotels_hotel_description_text_view);
        TextView hotelsHotelContactNumberTextView = findViewById(R.id.hotels_hotel_contact_number_text_view);
        TextView hotelsHotelRatingTextView = findViewById(R.id.hotels_hotel_rating_text_view);
        RatingBar hotelsHotelReviewRatingBar = findViewById(R.id.hotels_hotel_review_rating_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        Bundle bundle = getIntent().getExtras();
        Hotel hotel = null;

        if (bundle != null) {
            hotel = bundle.getParcelable("Hotel");
        }

        if (hotel != null) {
            mHotelName = hotel.getHotelName();
            setTitle(mHotelName);
            mHotelURL = hotel.getHotelURL();
            hotelsHotelPhotoImageView.setImageResource(hotel.getHotelPhoto());
            hotelsHotelRatingTextView.setText(hotel.getHotelRatings());
            hotelsHotelReviewRatingBar.setRating(Float.parseFloat(hotel.getHotelReview()));
            hotelsHotelDescriptionTextView.setText(hotel.getHotelDescription());
            hotelsHotelContactNumberTextView.setText(hotel.getHotelContactNumber());
        }
    }
}