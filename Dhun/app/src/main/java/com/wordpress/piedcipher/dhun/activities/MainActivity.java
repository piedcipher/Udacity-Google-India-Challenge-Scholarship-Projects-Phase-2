package com.wordpress.piedcipher.dhun.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.adapters.DhunPagerAdapter;
import com.wordpress.piedcipher.dhun.models.Song;
import com.wordpress.piedcipher.dhun.utils.DhunDataUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> mSongsArrayList;

    private LinearLayout mNowPlayingBarExpanded;

    private TextView mNowPlayingSongTitle;
    private TextView mNowPlayingSongArtist;
    private TextView mNowPlayingSongDuration;
    private ImageView mNowPlayingSongAlbumArt;
    private ImageView mNowPlayingSongState;
    private MenuItem mEasterEgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.easter_egg, menu);
        mEasterEgg = menu.findItem(R.id.easter_egg);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.easter_egg:
                startActivity(new Intent(MainActivity.this, EasterEggActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateNowPlayingUI();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateNowPlayingUI();
    }

    /**
     * This method initializes all the Views, Widgets, Variables and Listeners.
     */
    private void initializer() {
        Resources resources = getResources();
        mSongsArrayList = new DhunDataUtil().getPopulatedSongsArrayList(MainActivity.this);

        LinearLayout nowPlayingBar = findViewById(R.id.now_playing_bar);
        ImageView nowPlayingFastForwardNextSong = findViewById(R.id.now_playing_fast_forward_next_song);
        ImageView nowPlayingRewindPreviousSong = findViewById(R.id.now_playing_rewind_previous_song);
        mNowPlayingBarExpanded = findViewById(R.id.now_playing_bar_expanded);
        mNowPlayingSongTitle = findViewById(R.id.song_now_playing_title);
        mNowPlayingSongArtist = findViewById(R.id.song_now_playing_artist);
        mNowPlayingSongAlbumArt = findViewById(R.id.song_now_playing_album_art);
        mNowPlayingSongDuration = findViewById(R.id.now_playing_song_duration);
        mNowPlayingSongDuration.setText(DhunDataUtil.getNowPlayingSongDuration());
        mNowPlayingSongState = findViewById(R.id.song_now_playing_state);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white));
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        DhunPagerAdapter dhunPagerAdapter = new DhunPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(dhunPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        mNowPlayingSongState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNowPlayingState();
            }
        });

        nowPlayingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNowPlayingBarState();
            }
        });

        nowPlayingFastForwardNextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = 0, nextIndex = 0;
                for (; index < mSongsArrayList.size(); index++) {
                    if(mSongsArrayList.get(index).getTitle().equals(mNowPlayingSongTitle.getText().toString())) {
                        break;
                    }
                }

                if(index < mSongsArrayList.size() - 1) {
                    nextIndex = ++index;
                }

                DhunDataUtil.setNowPlayingSongData(
                        mSongsArrayList.get(nextIndex).getTitle(),
                        mSongsArrayList.get(nextIndex).getArtist(),
                        mSongsArrayList.get(nextIndex).getAlbumArt(),
                        mSongsArrayList.get(nextIndex).getDuration(),
                        true,
                        false
                );

                updateNowPlayingUI();
            }
        });

        nowPlayingRewindPreviousSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = mSongsArrayList.size() - 1, previousIndex = mSongsArrayList.size() - 1;
                for (; index >= 0; index--) {
                    if(mSongsArrayList.get(index).getTitle().equals(mNowPlayingSongTitle.getText().toString())) {
                        break;
                    }
                }

                if(index > 0) {
                    previousIndex = --index;
                }

                DhunDataUtil.setNowPlayingSongData(
                        mSongsArrayList.get(previousIndex).getTitle(),
                        mSongsArrayList.get(previousIndex).getArtist(),
                        mSongsArrayList.get(previousIndex).getAlbumArt(),
                        mSongsArrayList.get(previousIndex).getDuration(),
                        true,
                        false
                );

                updateNowPlayingUI();
            }
        });
    }

    /**
     * This method updates UI elements of Now-playing bar based on User-Input.
     */
    public void updateNowPlayingUI() {
        mNowPlayingSongTitle.setText(DhunDataUtil.getNowPlayingSongTitle());
        mNowPlayingSongArtist.setText(DhunDataUtil.getNowPlayingSongArtist());
        mNowPlayingSongAlbumArt.setImageResource(DhunDataUtil.getNowPlayingSongAlbumArt());
        mNowPlayingSongDuration.setText(DhunDataUtil.getNowPlayingSongDuration());

        if(mEasterEgg != null) {
            if(mNowPlayingSongTitle.getText().toString().equals(getString(R.string.song_pokemon_theme))) {
                mEasterEgg.setVisible(true);
            } else {
                mEasterEgg.setVisible(false);
            }
        }
    }

    /**
     * This method controls state of Play/Pause in Now-playing bar.
     */
    private void updateNowPlayingState() {
        if(DhunDataUtil.getNowPlayingSongState()) {
            DhunDataUtil.setNowPlayingSongState(false);
            mNowPlayingSongState.setImageResource(R.drawable.pause);
        } else {
            DhunDataUtil.setNowPlayingSongState(true);
            mNowPlayingSongState.setImageResource(R.drawable.play);
        }
    }

    /**
     * This method controls visibility of Expandable Now-Playing bar which contains fast-forward, rewind and seek-bar.
     */
    private void updateNowPlayingBarState() {
        if(DhunDataUtil.getNowPlayingBarState()) {
            DhunDataUtil.setNowPlayingBarState(false);
            mNowPlayingBarExpanded.setVisibility(View.GONE);
        } else {
            DhunDataUtil.setNowPlayingBarState(true);
            mNowPlayingBarExpanded.setVisibility(View.VISIBLE);
        }
    }
}