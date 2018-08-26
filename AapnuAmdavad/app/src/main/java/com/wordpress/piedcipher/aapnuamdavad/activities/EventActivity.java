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
import com.wordpress.piedcipher.aapnuamdavad.models.Event;

public class EventActivity extends AppCompatActivity {

    private String mEventVenue;
    private String mEventURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
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
                Intent openInGoogleMaps = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("geo:0,0?q=" + mEventVenue));
                if (openInGoogleMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(openInGoogleMaps);
                }
                break;
            case R.id.view_more_details:
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(mEventURL)));
                break;
        }
        return true;
    }

    /**
     * This method initializes all the Views, Widgets, Variables and Listeners.
     */
    private void initializer() {
        ImageView eventsEventPhotoImageView = findViewById(R.id.events_event_photo_image_view);
        TextView eventsEventDescriptionTextView = findViewById(R.id.events_event_description_text_view);
        TextView eventsEventDateTextView = findViewById(R.id.events_event_date_text_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        Bundle bundle = getIntent().getExtras();
        Event event = null;

        if (bundle != null) {
            event = bundle.getParcelable("Event");
        }

        if (event != null) {
            setTitle(event.getEventTitle());
            mEventVenue = event.getEventVenue();
            mEventURL = event.getEventURL();
            eventsEventPhotoImageView.setImageResource(event.getEventPhoto());
            eventsEventDescriptionTextView.setText(event.getEventDescription());
            eventsEventDateTextView.setText(event.getEventDate());
        }
    }
}