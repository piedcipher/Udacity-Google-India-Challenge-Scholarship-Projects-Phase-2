package com.wordpress.piedcipher.dhun.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.models.Song;
import com.wordpress.piedcipher.dhun.utils.DhunDataUtil;
import com.wordpress.piedcipher.dhun.adapters.SongAdapter;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private LinearLayout mNowPlayingBarExpanded;

    private TextView mNowPlayingSongTitle;
    private TextView mNowPlayingSongArtist;
    private TextView mNowPlayingSongDuration;
    private ImageView mNowPlayingSongAlbumArt;
    private ImageView mNowPlayingSongState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initializer();
    }

    protected void onResume() {
        super.onResume();
        updateNowPlayingUI();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateNowPlayingUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeAsUp:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method initializes all the Views, Widgets, Variables and Listeners.
     */
    private void initializer() {
        final ArrayList<Song> songsArrayList = new DhunDataUtil().getPopulatedSongsArrayList(DetailsActivity.this);
        final ArrayList<Song> albumSongsArrayList = new ArrayList<>();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        String albumName, albumArtist, artistName;
        int albumArt;

        ListView albumSongsListView = findViewById(R.id.album_songs_list_view);
        ImageView nowPlayingFastForwardNextSong = findViewById(R.id.now_playing_fast_forward_next_song);
        ImageView nowPlayingRewindPreviousSong = findViewById(R.id.now_playing_rewind_previous_song);
        LinearLayout nowPlayingBar = findViewById(R.id.now_playing_bar);
        mNowPlayingBarExpanded = findViewById(R.id.now_playing_bar_expanded);
        mNowPlayingSongTitle = findViewById(R.id.song_now_playing_title);
        mNowPlayingSongArtist = findViewById(R.id.song_now_playing_artist);
        mNowPlayingSongAlbumArt = findViewById(R.id.song_now_playing_album_art);
        mNowPlayingSongDuration = findViewById(R.id.now_playing_song_duration);
        mNowPlayingSongDuration.setText(DhunDataUtil.getNowPlayingSongDuration());
        mNowPlayingSongState = findViewById(R.id.song_now_playing_state);

        if (bundle != null) {
            albumArtist = bundle.getString("AlbumArtist");
            albumName = bundle.getString("AlbumName");
            albumArt = bundle.getInt("AlbumArt");
            artistName = bundle.getString("ArtistName");

            if (albumArtist != null) {
                for (int i = 0; i < songsArrayList.size(); i++) {
                    if (songsArrayList.get(i).getAlbumArt() == albumArt) {
                        albumSongsArrayList.add(songsArrayList.get(i));
                    }
                }
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(albumName + " by " + albumArtist);
                }
            } else {
                for (int i = 0; i < songsArrayList.size(); i++) {
                    if (songsArrayList.get(i).getArtist().equals(artistName)) {
                        albumSongsArrayList.add(songsArrayList.get(i));
                    }
                }
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Songs by " + artistName);
                }
            }
        }

        SongAdapter songAdapter = new SongAdapter(DetailsActivity.this, albumSongsArrayList);
        SongAdapter.setIsArtistSpecific(true);
        albumSongsListView.setAdapter(songAdapter);

        albumSongsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DhunDataUtil.setNowPlayingSongData(
                        albumSongsArrayList.get(i).getTitle(),
                        albumSongsArrayList.get(i).getArtist(),
                        albumSongsArrayList.get(i).getAlbumArt(),
                        albumSongsArrayList.get(i).getDuration(),
                        true,
                        false);

                Toast.makeText(DetailsActivity.this,
                        "Playing " + DhunDataUtil.getNowPlayingSongTitle(),
                        Toast.LENGTH_SHORT).show();

                updateNowPlayingUI();
            }
        });

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
                for (; index < songsArrayList.size(); index++) {
                    if (songsArrayList.get(index).getTitle().equals(mNowPlayingSongTitle.getText().toString())) {
                        break;
                    }
                }

                if (index < songsArrayList.size() - 1) {
                    nextIndex = ++index;
                }

                DhunDataUtil.setNowPlayingSongData(
                        songsArrayList.get(nextIndex).getTitle(),
                        songsArrayList.get(nextIndex).getArtist(),
                        songsArrayList.get(nextIndex).getAlbumArt(),
                        songsArrayList.get(nextIndex).getDuration(),
                        true,
                        false
                );

                updateNowPlayingUI();
            }
        });

        nowPlayingRewindPreviousSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = songsArrayList.size() - 1, previousIndex = songsArrayList.size() - 1;
                for (; index >= 0; index--) {
                    if (songsArrayList.get(index).getTitle().equals(mNowPlayingSongTitle.getText().toString())) {
                        break;
                    }
                }

                if (index > 0) {
                    previousIndex = --index;
                }

                DhunDataUtil.setNowPlayingSongData(
                        songsArrayList.get(previousIndex).getTitle(),
                        songsArrayList.get(previousIndex).getArtist(),
                        songsArrayList.get(previousIndex).getAlbumArt(),
                        songsArrayList.get(previousIndex).getDuration(),
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
    }

    /**
     * This method controls state of Play/Pause in Now-playing bar.
     */
    public void updateNowPlayingState() {
        if (DhunDataUtil.getNowPlayingSongState()) {
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
    public void updateNowPlayingBarState() {
        if (DhunDataUtil.getNowPlayingBarState()) {
            DhunDataUtil.setNowPlayingBarState(false);
            mNowPlayingBarExpanded.setVisibility(View.GONE);
        } else {
            DhunDataUtil.setNowPlayingBarState(true);
            mNowPlayingBarExpanded.setVisibility(View.VISIBLE);
        }
    }
}