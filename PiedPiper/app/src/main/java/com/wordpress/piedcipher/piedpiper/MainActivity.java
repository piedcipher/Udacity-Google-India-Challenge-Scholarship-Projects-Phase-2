package com.wordpress.piedcipher.piedpiper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when website or map-address of business is clicked
     * @param view This is the view which was clicked by user
     */
    public void contactUs(View view) {
        switch (view.getId()) {
            case R.id.business_website:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.piedpiper.com")));
                break;

            case R.id.business_address:
                Intent openBusinessOnMaps = new Intent(Intent.ACTION_VIEW);
                openBusinessOnMaps.setData(Uri.parse("geo:0, 0?q=5230+Penfield+Ave,+Woodland+Hills,+CA+91364,+USA"));

                if (openBusinessOnMaps.resolveActivity(getPackageManager()) != null)
                    startActivity(openBusinessOnMaps);
                break;
        }
    }
}