package com.wordpress.piedcipher.dhun.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wordpress.piedcipher.dhun.R;

public class EasterEggActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter_egg);

        setTitle(getString(R.string.easter_egg_info_0));

        ImageView dacingCharmanderImageView = findViewById(R.id.dacing_charmander);
        Glide.with(this).load(R.drawable.dancing_charmander).into(dacingCharmanderImageView);
    }

    public void openMusicVideoOnYoutube(View view) {
        String videoURL = "";

        switch (view.getId()) {
            case R.id.open_original_video:
                videoURL = "https://youtu.be/fCkeLBGSINs";
                break;
            case R.id.open_cover_video:
                videoURL = "https://youtu.be/GyQjVtIGQg8";
                break;
        }

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videoURL)));
    }
}
